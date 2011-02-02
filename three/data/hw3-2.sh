#!/bin/sh

JAVA=/usr/jdk/jdk1.5.0_17/bin/java

SEQ=ElementaryCASeq 
CLU=ElementaryCAClu 

ARGS="30 20000000 200"
$JAVA $SEQ  $ARGS
$JAVA -Dpj.np=1  $CLU  $ARGS
$JAVA -Dpj.np=2  $CLU  $ARGS
$JAVA -Dpj.np=4  $CLU  $ARGS
$JAVA -Dpj.np=8  $CLU  $ARGS
$JAVA -Dpj.np=16 $CLU  $ARGS
 

ARGS="30 2000000 2000"
$JAVA $SEQ  $ARGS
$JAVA -Dpj.np=1  $CLU  $ARGS
$JAVA -Dpj.np=2  $CLU  $ARGS
$JAVA -Dpj.np=4  $CLU  $ARGS
$JAVA -Dpj.np=8  $CLU  $ARGS
$JAVA -Dpj.np=16 $CLU  $ARGS

ARGS="30 20000 200000"
$JAVA $SEQ  $ARGS
$JAVA -Dpj.np=1  $CLU  $ARGS
$JAVA -Dpj.np=2  $CLU  $ARGS
$JAVA -Dpj.np=4  $CLU  $ARGS
$JAVA -Dpj.np=8  $CLU  $ARGS
$JAVA -Dpj.np=16 $CLU  $ARGS
