############################################################
#
# Author: Brian Gianforcaro - b.gianfo@gmail.com
#
############################################################
EX = java
CC = javac -Xlint:all
PJ = ../pj.jar:.

TARGET = PrimeTesterSmp

default: class

class: clean *.java
	$(CC) -cp $(PJ) *.java
   
zip: bundle

bundle:
	zip hw2.zip hw2-1.txt hw2-2.txt hw2-3.txt hw2-3.txt

clean:
	rm -rf *.class
	rm -rf *.zip
