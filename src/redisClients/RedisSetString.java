import redis.clients.jedis.Jedis; 

public class RedisSetString { 
   public static void main(String[] args) { 
      //Connecting to Redis server on localhost 
      Jedis jedis = new Jedis("localhost"); 
      System.out.println("Connection to server sucessfully"); 

      for(int key=1001;key<=2000;key++){
      	System.out.println("{\"Organization\": {\"OrgName\": \"XYZ_"+key+"\", \"OrgCode\": "+key+", \"SiteName\": \"AAA_"+key+"\"}, \"Tags\": {\"T1\": \"A\", \"T2\": \"B\"}}");
   		//set the data in redis string
   		jedis.set(String.valueOf(key), "{\"Organization\": {\"OrgName\": \"XYZ_"+key+"\", \"OrgCode\": "+key+", \"SiteName\": \"AAA_"+key+"\"}, \"Tags\": {\"T1\": \"A\", \"T2\": \"B\"}}"); 
      }
   } 
}

/*From Redis client: redis-cli
set 1111 '{"Organization": {"OrgName": "XYZ", "OrgCode": 123, "SiteName": "AAA"}, "Tags": {"T1": "A", "T2": "B"}}'

Jedis Jar downloaded from: https://jar-download.com/artifacts/redis.clients/jedis
*/
