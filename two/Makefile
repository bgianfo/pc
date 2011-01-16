############################################################
#
#  Author: Brian Gianforcaro - b.gianfo@gmail.com
#  Homework: #2
#
############################################################
CC = javac -Xlint:all
#CC = /usr/local/versions/jdk-1.5.0_15/bin/javac -Xlint:all
PJ = ../pj.jar:.
AESKEY=2236ad3b9d9d91a12cf9ef5052ce654f5b02c46c34a43d8d75c7a74bfed2ee9f

default: class

class: clean *.java
	$(CC) -cp $(PJ) *.java
   
zip: bundle

bundle:
	zip hw2.zip hw2-1.txt hw2-2.txt hw2-3.txt hw2-4.txt \
	    MSAreaSeq.java MSAreaSmp.java \
      	    EncryptFileSeq.java \
	    EncryptFileSmpNoOverlap.java \
	    EncryptFileSmpOverlap.java

test: clean class test-phoney clean

test-phoney:
	java -cp ../pj.jar:. EncryptFileSmpNoOverlap $(AESKEY) data/pg100.txt test-pg100.enc 20
	rm -rf test-pg100*
	java -cp ../pj.jar:. EncryptFileSmpOverlap $(AESKEY) data/pg100.txt test-pg100.enc 20
	hexdump test-pg100.enc > test-pg100.enc.hex
	diff -u data/pg100.enc.hex test-pg100.enc.hex

clean:
	rm -rf test-pg100*
	rm -rf *.class
	rm -rf *.zip
