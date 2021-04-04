Rem 1. Store Json string in Redis
javac -d classes\ -cp .;lib\redis\jedis-3.5.2.jar src\RedisSetString.java
java -cp classes;lib\redis\jedis-3.5.2.jar RedisSetString

