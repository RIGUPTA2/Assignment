Rem 1. Start Zookeeper after setting log.dirs in server.properties and dataDir in zookeeper.properties
D:\
cd D:\ProgramFiles\kafka
.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 2 --topic SourceTopic2

cd ..\Assignment