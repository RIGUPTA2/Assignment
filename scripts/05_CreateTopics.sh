#Create topics
cd /home/ubuntu/kafka
./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 2 --topic SourceTopic2
./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 5 --topic SourceTopic5

./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 10 --topic SourceTopic10
./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 20 --topic SourceTopic20
#./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic EnrichedTopic

cd ../Assignment/scripts
