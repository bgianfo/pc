############################################################
#
#  Author: Brian Gianforcaro - b.gianfo@gmail.com
#  Homework: #2
#
############################################################
CC = javac -Xlint:all
#CC = /usr/local/versions/jdk-1.5.0_15/bin/javac -Xlint:all
PJ = ../pj.jar:.

default: class

class: clean *.java
	$(CC) -cp $(PJ) *.java
   
zip: bundle

bundle: hw3-1.txt ElementaryCASeq.java ElementaryCAClu.java hw3-2.txt
	zip hw3.zip hw3-1.txt ElementaryCASeq.java ElementaryCAClu.java hw3-2.txt

clean:
	rm -rf *.class
	rm -rf *.zip
