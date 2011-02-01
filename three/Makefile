############################################################
#
#  Author: Brian Gianforcaro - b.gianfo@gmail.com
#  Homework: #2
#
############################################################
#CC = javac -Xlint:all
CC = /usr/jdk/jdk1.5.0_17/bin/javac -Xlint:all
JAVA = /usr/jdk/jdk1.5.0_17/bin/java
PJ = ../pj.jar:.

default: class

class: clean *.java
	$(CC) -cp $(PJ) *.java
   
zip: bundle

test: class
	#$(JAVA) -Dpj.np=4 ElementaryCAClu 30 200000 2000
	$(JAVA) -Dpj.np=4 ElementaryCAClu 30 200000 200


bundle: hw3-1.txt ElementaryCASeq.java ElementaryCAClu.java hw3-2.txt
	zip hw3.zip hw3-1.txt ElementaryCASeq.java ElementaryCAClu.java hw3-2.txt

clean:
	rm -rf *.class
	rm -rf *.zip
