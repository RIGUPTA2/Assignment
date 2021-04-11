#!/bin/bash

endVal=$1
a=1
while [ $a -le $endVal ]    # this is loop1
do
   . 09_AggregatorStream.sh "$endVal"partition_"$endVal"consumer_log"$a" 99800 SourceTopic"$endVal" &
   a=`expr $a + 1`
done

b=1
while [ $b -le 10 ]    # this is loop1
do
	. 10_ProducerProgram.sh "$endVal"partition_"$endVal"consumer_log1 100000 SourceTopic"$endVal" &
	 b=`expr $b + 1`
done
