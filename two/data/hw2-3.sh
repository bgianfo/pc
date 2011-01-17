#!/bin/sh

JAVA=/usr/local/versions/jdk-1.5.0_15/bin/java

SEED=54321 
MITR=8191
N=20000000

$JAVA MSAreaSeq $SEED $MITR $N
echo "fixed"
$JAVA -Dpj.schedule=fixed -Dpj.nt=1 MSAreaSmp $SEED $MITR $N
$JAVA -Dpj.schedule=fixed -Dpj.nt=2 MSAreaSmp $SEED $MITR $N
$JAVA -Dpj.schedule=fixed -Dpj.nt=4 MSAreaSmp $SEED $MITR $N
$JAVA -Dpj.schedule=fixed -Dpj.nt=8 MSAreaSmp $SEED $MITR $N
echo "dynamic"
$JAVA -Dpj.schedule=dynamic -Dpj.nt=1 MSAreaSmp $SEED $MITR $N
$JAVA -Dpj.schedule=dynamic -Dpj.nt=2 MSAreaSmp $SEED $MITR $N
$JAVA -Dpj.schedule=dynamic -Dpj.nt=4 MSAreaSmp $SEED $MITR $N
$JAVA -Dpj.schedule=dynamic -Dpj.nt=8 MSAreaSmp $SEED $MITR $N
echo "guided"
$JAVA -Dpj.schedule=guided -Dpj.nt=1 MSAreaSmp $SEED $MITR $N
$JAVA -Dpj.schedule=guided -Dpj.nt=2 MSAreaSmp $SEED $MITR $N
$JAVA -Dpj.schedule=guided -Dpj.nt=4 MSAreaSmp $SEED $MITR $N
$JAVA -Dpj.schedule=guided -Dpj.nt=8 MSAreaSmp $SEED $MITR $N
