############################################################
#
# Author: Brian Gianforcaro - b.gianfo@gmail.com
#
############################################################
EX = java
CC = javac
PJ = ../pj.jar:.

TARGET = PrimeTesterSmp

default: class

run: class
	$(EX) -cp $(PJ) $(TARGET)

class: $(TARGET).java
	$(CC) -cp $(PJ) *.java
   
clean:
	rm -rf *.class

