#!/bin/bash
. 09_AggregatorStream.sh 2partition_2consumerOnly 100000 SourceTopic2 &
. 10_ProducerProgram.sh 2partition_2consumerOnly 100000 SourceTopic2 &

