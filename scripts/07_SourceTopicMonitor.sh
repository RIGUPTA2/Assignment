#Author: Rishi
#Source Topic Monitor
cd /home/ubuntu/kafka
./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic SourceTopic --from-beginning
cd ../Assignment/scripts
