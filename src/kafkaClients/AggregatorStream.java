import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Arrays;
import java.util.Properties;

import redis.clients.jedis.Jedis; 
import org.json.JSONObject;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class AggregatorStream {
  public static void main(final String[] args) throws Exception {
    //Connecting to Redis server on localhost 
    Jedis jedis = new Jedis("localhost"); 
    System.out.println("Connection to Redis Server Sucessful!"); 
    // Get the stored data and print it 
    String redisString=jedis.get("1111");
    System.out.println("Stored string in redis:: "+ redisString);
    JSONObject obj1=new JSONObject(str);
    System.out.println(obj1.get("Organization"));

    Properties props = new Properties();
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "JSON_Aggregator");
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

    StreamsBuilder builder = new StreamsBuilder();
    KStream<String, String> kStream = builder.stream("SourceTopic");
    //KStream<String, String> kStream =     builder.stream("SourceTopic", Consumed.with(stringSerde, stringSerde)).mapValues(String::toUpperCase).to("EnrichedTopic", Produced.with(stringSerde, stringSerde));

    Properties producerProp=new Properties();
    producerProp.put("bootstrap.servers","localhost:9092");
    producerProp.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
    producerProp.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
    KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(producerProp);

    kStream.foreach((k,v)-> {

      ProducerRecord<String, String> producerRecord=new ProducerRecord<String, String>("EnrichedTopic","InputData",(k+v+"Some check"));
      kafkaProducer.send(producerRecord);
      kafkaProducer.close();
      System.out.println(k);
    });


    /*KTable<String, Long> wordCounts = kStream
    .flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")))
    .groupBy((key, word) -> word)
    .count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"));
    wordCounts.toStream().to("EnrichedTopic", Produced.with(Serdes.String(), Serdes.Long()));
    */

    KafkaStreams streams = new KafkaStreams(builder.build(), props);
    streams.start();
    System.out.println("AggregatorStream Logic End!");
  }
}