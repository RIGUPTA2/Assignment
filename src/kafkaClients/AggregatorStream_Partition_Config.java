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
import org.apache.kafka.streams.state.StreamsMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Arrays;
import java.util.Properties;
import java.util.Set;
import java.util.Iterator;
import java.util.*;
import java.util.logging.*;
import java.io.*;

import redis.clients.jedis.Jedis; 
import org.json.JSONObject;
import org.json.JSONException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class AggregatorStream {
  static long perRecordStartTime;
  static long globalStartTime;
  static Collection<StreamsMetadata> s;
  public static void main(final String[] args) throws Exception {
    globalStartTime=System.nanoTime();

    //Connecting to Redis server on localhost 
    Jedis jedis = new Jedis("localhost"); 
    System.out.println("Connection to Redis Server Sucessful!"); 

    //Creating Stream object properties
    Properties props = new Properties();
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "JSON_Aggregator");
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");


    StreamsBuilder builder = new StreamsBuilder();
    KStream<String, String> kStream = builder.stream("SourceTopic");

    //Properties object for Producer
    Properties producerProp=new Properties();
    producerProp.put("bootstrap.servers","localhost:9092");
    producerProp.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
    producerProp.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
    KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(producerProp);

    Logger logger = Logger.getLogger("MyLog1");  
    FileHandler fh;  
    try {
      System.setProperty("java.util.logging.SimpleFormatter.format","[%1$tF %1$tT] [%4$-7s] %5$s %n");
      // This block configure the logger with handler, formatter and start time
      fh = new FileHandler("../logs/ConsumerLogFile_"+args[0]+".csv");  
      logger.addHandler(fh);
      SimpleFormatter formatter = new SimpleFormatter();  
      fh.setFormatter(formatter);
      System.out.println("-------------Source Topic Navigation Started-----------------");
      kStream.foreach((sourceTopicJSON_Key,sourceTopicMessageValue)-> {
        perRecordStartTime=System.nanoTime();
        System.out.println("Key: "+sourceTopicJSON_Key);
        System.out.println("Value: "+sourceTopicMessageValue);
        JSONObject sourceTopicValueJSON=new JSONObject(sourceTopicMessageValue);

        // Get the stored data and print it 
        String redisString=jedis.get(sourceTopicJSON_Key);
        if(redisString==""){
          System.out.println("--------->Blank");
        } else if (redisString == null){
          System.out.println("--------->NULL");
        } else if(redisString.isEmpty()){
          System.out.println("--------->Empty");
        } else {
          System.out.println("Stored string in redis:: "+ redisString);
          JSONObject redisSubObject=new JSONObject(redisString);

          JSONObject finalJSONObject=new JSONObject();
          finalJSONObject.put("time", sourceTopicValueJSON.get("time"));
          finalJSONObject.put("Reading", sourceTopicValueJSON.get("Reading"));
          finalJSONObject.put("Organization", redisSubObject.get("Organization"));
          finalJSONObject.put("Tags", redisSubObject.get("Tags"));
          String strFinal=finalJSONObject.toString();

          ProducerRecord<String, String> producerRecord=new ProducerRecord<String, String>("EnrichedTopic",sourceTopicJSON_Key,(strFinal));
          kafkaProducer.send(producerRecord);
          logger.info("ConsumerPerRecordAnalysis;"+sourceTopicJSON_Key+";"+((System.nanoTime() - perRecordStartTime)/1000)+";");
          if(Integer.parseInt(args[1])== Integer.parseInt(sourceTopicJSON_Key)){
            System.out.println("Checking logger");
            logger.info("ConsumerGlobalAnalysis;"+sourceTopicJSON_Key+";"+((System.nanoTime() - globalStartTime)/1000)+";");
for (StreamsMetadata m : s) {
    Set<TopicPartition> tp = m.topicPartitions();
    logger.info("Topic Partition;"+tp.toString());
}

          }
        }
      });
    } catch(JSONException je){
      System.out.println("Not a JSON object");
    } catch (SecurityException se) {  
       se.printStackTrace();  
    } catch (IOException ioe) {  
       ioe.printStackTrace();  
    } catch (Exception e) {  
       e.printStackTrace();  
    }

    KafkaStreams streams = new KafkaStreams(builder.build(), props);
    streams.cleanUp();

    streams.start();
    s = streams.allMetadata();
    //kafkaProducer.close();
    System.out.println("AggregatorStream Logic End!");
  }
}
    //KStream<String, String> kStream =     builder.stream("SourceTopic", Consumed.with(stringSerde, stringSerde)).mapValues(String::toUpperCase).to("EnrichedTopic", Produced.with(stringSerde, stringSerde));
    /*KTable<String, Long> wordCounts = kStream
    .flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")))
    .groupBy((key, word) -> word)
    .count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"));
    wordCounts.toStream().to("EnrichedTopic", Produced.with(Serdes.String(), Serdes.Long()));
    */