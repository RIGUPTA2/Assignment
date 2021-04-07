#Create topics
cd /home/ubuntu/kafka
./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic SourceTopic

./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic EnrichedTopic

cd ../Assignment/scripts
