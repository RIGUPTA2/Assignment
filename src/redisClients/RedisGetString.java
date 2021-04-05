import redis.clients.jedis.Jedis; 
import org.json.JSONObject;


public class RedisGetString { 
   public static void main(String[] args) { 
      //Connecting to Redis server on localhost 
      Jedis jedis = new Jedis("localhost"); 
      System.out.println("Connection to server sucessfully"); 
      // Get the stored data and print it 
      String str=jedis.get("1111");
      System.out.println("Stored string in redis:: "+ str);
      JSONObject obj1=new JSONObject(str);
      System.out.println(obj1.get("Organization"));
   } 
}

/*
From Redis client: redis-cli
set 1111 '{"Organization": {"OrgName": "XYZ", "OrgCode": 123, "SiteName": "AAA"}, "Tags": {"T1": "A", "T2": "B"}}'

Jedis Jar downloaded from: https://jar-download.com/artifacts/redis.clients/jedis

javac -d classes\ -cp classes;lib\jedis-3.5.2.jar;lib\json-20180813.jar src\RedisGetString.java
java -cp classes;lib\jedis-3.5.2.jar;lib\json-20180813.jar RedisGetString

https://www.tutorialspoint.com/org_json/org_json_quick_guide.htm
https://javadoc.io/doc/org.json/json/20180813/org/json/class-use/JSONObject.html
*/
