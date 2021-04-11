import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.*;
import java.util.logging.*;
import java.io.*;

public class ProducerProgram { 
   static long perRecordStartTime;
   static long globalStartTime;
   static int key;
   public static void main(String[] args) {
      globalStartTime=System.nanoTime();

      Properties prop=new Properties();
      prop.put("bootstrap.servers","localhost:9092");
      prop.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
      prop.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
      KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(prop);

      Logger logger = Logger.getLogger("MyLog1");  
      FileHandler fh;  
      try {
         System.setProperty("java.util.logging.SimpleFormatter.format","[%1$tF %1$tT] [%4$-7s] %5$s %n");
         // This block configure the logger with handler, formatter and start time
         fh = new FileHandler("../logs/ProducerLogFile_"+args[0]+".csv");  
         logger.addHandler(fh);
         SimpleFormatter formatter = new SimpleFormatter();  
         fh.setFormatter(formatter);
         System.out.println("Start of Producer");
         for(key=1001;key<=Integer.parseInt(args[1]);key++){
            //perRecordStartTime=System.nanoTime();
            String JSON_DataFeedFromEdgeNode="{\"time\": "+System.currentTimeMillis()+",\"Reading\": "+((int)((Math.random() * 40) + 1))+"}";
            ProducerRecord<String, String> producerRecord=new ProducerRecord<String, String>(args[2],String.valueOf(key), JSON_DataFeedFromEdgeNode);
            kafkaProducer.send(producerRecord);
            //logger.info("ProducerPerRecordAnalysis;"+String.valueOf(key)+";"+((System.nanoTime() - perRecordStartTime)/1000)+";");
         }
      } catch (SecurityException e) {  
         e.printStackTrace();  
      } catch (IOException e) {  
         e.printStackTrace();  
      }
      logger.info("ProducerGlobalAnalysis;"+String.valueOf(key)+";"+((System.nanoTime() - globalStartTime)/1000)+";");
      kafkaProducer.close();
      System.out.println("\nJSON Data feed from Edge node is submitted in Source Topic!");
   }
}

//Sample message
//String JSON_DataFeedFromEdgeNode="{\"1111\":{\"time\": 1617193254000,\"Reading\": 44},\"1112\": {\"time\": 1617193254000,\"Reading\": -1},\"1113\": {\"time\": 1617193254000,\"Reading\": 4}}";