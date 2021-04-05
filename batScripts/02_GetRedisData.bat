Rem 0. Redis server is already installed using Windows MSI file and is running on system start and could be stopped/started from services.msc 

Rem 1. Read JSON string from Redis
javac -d ..\classes\ -cp .;..\lib\redis\jedis-3.5.2.jar;..\lib\json\json-20180813.jar ..\src\redisClients\RedisGetString.java
java -cp ..\classes;..\lib\redis\jedis-3.5.2.jar;..\lib\json\json-20180813.jar RedisGetString
