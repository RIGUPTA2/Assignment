import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerProgram { 
   public static void main(String[] args) {
      String JSON_DataFeedFromEdgeNode="{\"1111\":{\"time\": 1617193254000,\"Reading\": 44},\"1112\": {\"time\": 1617193254000,\"Reading\": -1},\"1113\": {\"time\": 1617193254000,\"Reading\": 4}}";
      //System.out.println("Input from data source"+JSON_DataFeedFromEdgeNode);

      Properties prop=new Properties();
      prop.put("bootstrap.servers","localhost:9092");
      prop.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
      prop.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

      ProducerRecord<String, String> producerRecord=new ProducerRecord<String, String>("SourceTopic","InputData", JSON_DataFeedFromEdgeNode);

      KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(prop);
      kafkaProducer.send(producerRecord);
      kafkaProducer.close();

      System.out.println("\nJSON Data feed from Edge node is submitted in Source Topic!");
   }
}