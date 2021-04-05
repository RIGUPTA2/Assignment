Rem 0. Redis server is already installed using Windows MSI file and is running on system start and could be stopped/started from services.msc 

Rem 1. Store JSON string in Redis
javac -d ..\classes\ -cp .;..\lib\redis\jedis-3.5.2.jar ..\src\redisClients\RedisSetString.java
java -cp ..\classes;..\lib\redis\jedis-3.5.2.jar RedisSetString
