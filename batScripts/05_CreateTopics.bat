Rem 1. Start Zookeeper after setting log.dirs in server.properties and dataDir in zookeeper.properties
D:\
cd D:\ProgramFiles\kafka
Rem .\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic SourceTopic

.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic EnrichedTopic

cd ..\Assignment