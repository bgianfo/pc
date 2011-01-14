############################################################
#
# Author: Brian Gianforcaro - b.gianfo@gmail.com
#
############################################################
CC = javac -Xlint:all
#CC = /usr/local/versions/jdk-1.5.0_15/bin/javac -Xlint:all
PJ = ../pj.jar:.

TARGET = PrimeTesterSmp

default: class

class: clean *.java
	$(CC) -cp $(PJ) *.java
   
zip: bundle

bundle:
	zip hw2.zip hw2-1.txt hw2-2.txt hw2-3.txt hw2-4.txt MSAreaSeq.java MSAreaSmp.java EncryptFileSeq.java EncryptFileSmpNoOverlap.java EncryptFileSmpNoOverlap.java

clean:
	rm -rf *.class
	rm -rf *.zip
