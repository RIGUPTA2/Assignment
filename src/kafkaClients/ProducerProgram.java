import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.*;
import java.util.logging.*;
import java.io.*;

public class ProducerProgram { 
   static long startTime;
   public static void main(String[] args) {
      Properties prop=new Properties();
      prop.put("bootstrap.servers","localhost:9092");
      prop.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
      prop.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

      Logger logger = Logger.getLogger("MyLog");  
      FileHandler fh;  
      try {
         // This block configure the logger with handler, formatter and start time
         fh = new FileHandler("../logs/PerformanceAnalysisLogFile.csv");  
         logger.addHandler(fh);
         SimpleFormatter formatter = new SimpleFormatter();  
         fh.setFormatter(formatter);
         startTime=System.currentTimeMillis();
      } catch (SecurityException e) {  
         e.printStackTrace();  
      } catch (IOException e) {  
         e.printStackTrace();  
      }

      String JSON_DataFeedFromEdgeNode="{\"1111\":{\"time\": 1617193254000,\"Reading\": 44},\"1112\": {\"time\": 1617193254000,\"Reading\": -1},\"1113\": {\"time\": 1617193254000,\"Reading\": 4}}";
      //System.out.println("Input from data source"+JSON_DataFeedFromEdgeNode);

      ProducerRecord<String, String> producerRecord=new ProducerRecord<String, String>("SourceTopic","InputData", JSON_DataFeedFromEdgeNode);

      KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(prop);
      kafkaProducer.send(producerRecord);
      logger.info("EndAnalysisRecord;"+(System.currentTimeMillis() - startTime)+";");

      kafkaProducer.close();

      System.out.println("\nJSON Data feed from Edge node is submitted in Source Topic!");
   }
}