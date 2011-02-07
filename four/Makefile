############################################################
#
#  Author: Brian Gianforcaro - b.gianfo@gmail.com
#  Homework: #4
#
############################################################
PJ = ../pj.jar:.
CC = javac -Xlint:all
JAVA = java -cp $(PJ)
#CC = /usr/jdk/jdk1.5.0_17/bin/javac -Xlint:all
#JAVA = /usr/jdk/jdk1.5.0_17/bin/java
TEST_ARGS="3000 3000 -0.75 0 1125 1000 0.4 ms.pjg"

default: class
class: clean *.java
	$(CC) -cp $(PJ) *.java

zip: bundle

test: class
	$(JAVA) -Dpj.np=4 ElementaryCAClu 30 200000 2000


bundle: hw3-1.txt hw4-2.txt MandelbrotSetClu2Overlap.java
	zip hw4.zip hw4-1.txt hw4-2.txt MandelbrotSetClu2Overlap.java

clean:
	rm -rf *.class
	rm -rf *.zip
