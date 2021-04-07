#Author: Rishi Gupta
#Store JSON string in Redis
javac -d ../classes/ -cp .:../lib/redis/jedis-3.5.2.jar ../src/redisClients/RedisSetString.java
java -cp ../classes:../lib/redis/jedis-3.5.2.jar RedisSetString
