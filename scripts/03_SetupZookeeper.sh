# 1. Start Zookeeper after setting log.dirs in server.properties and dataDir in zookeeper.properties
cd /home/ubuntu/kafka
./bin/zookeeper-server-start.sh ./config/zookeeper.properties
