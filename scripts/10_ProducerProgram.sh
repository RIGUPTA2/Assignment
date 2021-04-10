# 0. Redis server is already running

# 1. Clean Project
rm -f ProducerProgram

# 2. Store JSON string in Redis
javac -d ../classes/ -cp .:../lib/kafka/kafka-clients-2.7.0.jar:../lib/kafka/slf4j-api-1.7.30.jar:../lib/kafka/lz4-java-1.7.1.jar:../lib/kafka/snappy-java-1.1.7.7.jar:../lib/kafka/zstd-jni-1.4.5-6.jar:../lib/kafka/slf4j-simple-1.7.30.jar ../src/kafkaClients/ProducerProgram.java


# 3. Publish message on kafka
java -cp ../classes:../lib/kafka/kafka-clients-2.7.0.jar:../lib/kafka/slf4j-api-1.7.30.jar:../lib/kafka/lz4-java-1.7.1.jar:../lib/kafka/snappy-java-1.1.7.7.jar:../lib/kafka/zstd-jni-1.4.5-6.jar:../lib/kafka/slf4j-simple-1.7.30.jar ProducerProgram $1
