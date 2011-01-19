#!/bin/sh

JAVA=/usr/local/versions/jdk-1.5.0_15/bin/java

SEED=54321 
MITR=8191
N=20000000

$JAVA MSAreaSeq $SEED $MITR $N
$JAVA -Dpj.nt=1 MSAreaSmp $SEED $MITR $N
$JAVA -Dpj.nt=2 MSAreaSmp $SEED $MITR $N
$JAVA -Dpj.nt=4 MSAreaSmp $SEED $MITR $N
$JAVA -Dpj.nt=8 MSAreaSmp $SEED $MITR $N
