#Author: Rishi Gupta
# 1. Read JSON string from Redis
javac -d ../classes/ -cp .:../lib/redis/jedis-3.5.2.jar:../lib/json/json-20180813.jar ../src/redisClients/RedisGetString.java
java -cp ../classes:../lib/redis/jedis-3.5.2.jar:../lib/json/json-20180813.jar RedisGetString $1
