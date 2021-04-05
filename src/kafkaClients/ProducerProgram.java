import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerProgram { 
   public static void main(String[] args) {
      String JSON_DataFeedFromEdgeNode="{\"1111\":{\"time\": 1617193254000,\"Reading\": 44},\"1112\": {\"time\": 1617193254000,\"Reading\": -1},\"1113\": {\"time\": 1617193254000,\"Reading\": 4}}";
      //System.out.println("Input from data source"+JSON_DataFeedFromEdgeNode);

      Properties prop=new Properties();
      prop.put(k: "bootstrap.servers", v: "localhost:9092");
      prop.put(k: "key.serializer", v: "org.apache.kafka.common.serialization.StringSerializer");
      prop.put(k: "value.serializer",v: "org.apache.kafka.common.serialization.StringSerializer");

      ProducerRecord producerRecord=new ProducerRecord(topic: "SourceTopic", key: "InputData", value: JSON_DataFeedFromEdgeNode);

      KafkaProducer kafkaProducer = new KafkaProducer(prop);
      kafkaProducer.send(producerRecord);
      kafkaProducer.close();

      System.out.println("Stored string in redis: ");
   } 
}