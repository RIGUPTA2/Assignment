#!/bin/bash
. 09_AggregatorStream.sh 1partition_1consumerOnly 100000 SourceTopic &
. 10_ProducerProgram.sh 1partition_1consumerOnly 100000 SourceTopic &

