import redis.clients.jedis.Jedis; 

public class RedisSetString { 
   public static void main(String[] args) { 
      //Connecting to Redis server on localhost 
      Jedis jedis = new Jedis("localhost"); 
      System.out.println("Connection to server sucessfully"); 
      //set the data in redis string 
      jedis.set("1111", "{\"Organization\": {\"OrgName\": \"XYZ\", \"OrgCode\": 123, \"SiteName\": \"AAA\"}, \"Tags\": {\"T1\": \"A\", \"T2\": \"B\"}}"); 
   } 
}

/*From Redis client: redis-cli
set 1111 '{"Organization": {"OrgName": "XYZ", "OrgCode": 123, "SiteName": "AAA"}, "Tags": {"T1": "A", "T2": "B"}}'

Jedis Jar downloaded from: https://jar-download.com/artifacts/redis.clients/jedis
*/
