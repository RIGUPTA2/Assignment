# 1. Start Zookeeper after setting log.dirs in server.properties and dataDir in zookeeper.properties
cd /home/ubuntu/kafka
./bin/kafka-topics.sh --list --zookeeper localhost:2181
cd ../Assignment/scripts
