#Author: Rishi
# 1. Redis server is already installed using Windows MSI file and is running on system start and could be stopped/started from services.msc 
# 2. Producer has posted message in the source topic

# 3. Clean project
rm -f ../classes/AggregatorStream.class

# 4. Compile Aggregator program
javac -Xdiags:verbose -d ../classes/ -cp .:../lib/kafka/connect-api-2.7.0.jar:../lib/kafka/connect-json-2.7.0.jar:../lib/kafka/jackson-annotations-2.10.5.jar:../lib/kafka/jackson-core-2.10.5.jar:../lib/kafka/jackson-databind-2.10.5.1.jar:../lib/kafka/jackson-datatype-jdk8-2.10.5.jar:../lib/kafka/kafka-clients-2.7.0.jar:../lib/kafka/kafka-streams-2.7.0.jar:../lib/kafka/lz4-java-1.7.1.jar:../lib/kafka/rocksdbjni-5.18.4.jar:../lib/kafka/slf4j-api-1.7.30.jar:../lib/kafka/snappy-java-1.1.7.7.jar:../lib/kafka/zstd-jni-1.4.5-6.jar:../lib/kafka/slf4j-simple-1.7.30.jar:../lib/redis/jedis-3.5.2.jar:../lib/json/json-20180813.jar ../src/kafkaClients/AggregatorStream.java

# 5. Read message from Kafka stream and from redis db
java -cp ../classes:../lib/kafka/connect-api-2.7.0.jar:../lib/kafka/connect-json-2.7.0.jar:../lib/kafka/jackson-annotations-2.10.5.jar:../lib/kafka/jackson-core-2.10.5.jar:../lib/kafka/jackson-databind-2.10.5.1.jar:../lib/kafka/jackson-datatype-jdk8-2.10.5.jar:../lib/kafka/kafka-clients-2.7.0.jar:../lib/kafka/kafka-streams-2.7.0.jar:../lib/kafka/lz4-java-1.7.1.jar:../lib/kafka/rocksdbjni-5.18.4.jar:../lib/kafka/slf4j-api-1.7.30.jar:../lib/kafka/snappy-java-1.1.7.7.jar:../lib/kafka/zstd-jni-1.4.5-6.jar:../lib/kafka/slf4j-simple-1.7.30.jar:../lib/redis/jedis-3.5.2.jar:../lib/json/json-20180813.jar AggregatorStream $1 $2 $3
