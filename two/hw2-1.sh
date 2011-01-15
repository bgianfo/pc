#!/bin/sh

java -Dpj.schedule=fixed -Dpj.nt=1 MandelbrotSetMethodSmp 3200 9600 -0.75 4 1200 2500 0.4 ms.pjg
rm ms.pjg
java -Dpj.schedule=fixed -Dpj.nt=2 MandelbrotSetMethodSmp 3200 9600 -0.75 4 1200 2500 0.4 ms.pjg
rm ms.pjg
java -Dpj.schedule=fixed -Dpj.nt=4 MandelbrotSetMethodSmp 3200 9600 -0.75 4 1200 2500 0.4 ms.pjg
rm ms.pjg
java -Dpj.schedule=fixed -Dpj.nt=8 MandelbrotSetMethodSmp 3200 9600 -0.75 4 1200 2500 0.4 ms.pjg
rm ms.pjg

java -Dpj.schedule=dynamic -Dpj.nt=1 MandelbrotSetMethodSmp 3200 9600 -0.75 4 1200 2500 0.4 ms.pjg
rm ms.pjg
java -Dpj.schedule=dynamic -Dpj.nt=2 MandelbrotSetMethodSmp 3200 9600 -0.75 4 1200 2500 0.4 ms.pjg
rm ms.pjg
java -Dpj.schedule=dynamic -Dpj.nt=4 MandelbrotSetMethodSmp 3200 9600 -0.75 4 1200 2500 0.4 ms.pjg
rm ms.pjg
java -Dpj.schedule=dynamic -Dpj.nt=8 MandelbrotSetMethodSmp 3200 9600 -0.75 4 1200 2500 0.4 ms.pjg
rm ms.pjg

java -Dpj.schedule="dynamic(300)" -Dpj.nt=1 MandelbrotSetMethodSmp 3200 9600 -0.75 4 1200 2500 0.4 ms.pjg
rm ms.pjg
java -Dpj.schedule="dynamic(300)" -Dpj.nt=2 MandelbrotSetMethodSmp 3200 9600 -0.75 4 1200 2500 0.4 ms.pjg
rm ms.pjg
java -Dpj.schedule="dynamic(300)" -Dpj.nt=4 MandelbrotSetMethodSmp 3200 9600 -0.75 4 1200 2500 0.4 ms.pjg
rm ms.pjg
java -Dpj.schedule="dynamic(300)" -Dpj.nt=8 MandelbrotSetMethodSmp 3200 9600 -0.75 4 1200 2500 0.4 ms.pjg
rm ms.pjg

java -Dpj.schedule="guided" -Dpj.nt=1 MandelbrotSetMethodSmp 3200 9600 -0.75 4 1200 2500 0.4 ms.pjg
rm ms.pjg
java -Dpj.schedule="guided" -Dpj.nt=2 MandelbrotSetMethodSmp 3200 9600 -0.75 4 1200 2500 0.4 ms.pjg
rm ms.pjg
java -Dpj.schedule="guided" -Dpj.nt=4 MandelbrotSetMethodSmp 3200 9600 -0.75 4 1200 2500 0.4 ms.pjg
rm ms.pjg
java -Dpj.schedule="guided" -Dpj.nt=8 MandelbrotSetMethodSmp 3200 9600 -0.75 4 1200 2500 0.4 ms.pjg
rm ms.pjg

java -Dpj.schedule="guided(300)" -Dpj.nt=1 MandelbrotSetMethodSmp 3200 9600 -0.75 4 1200 2500 0.4 ms.pjg
rm ms.pjg
java -Dpj.schedule="guided(300)" -Dpj.nt=2 MandelbrotSetMethodSmp 3200 9600 -0.75 4 1200 2500 0.4 ms.pjg
rm ms.pjg
java -Dpj.schedule="guided(300)" -Dpj.nt=4 MandelbrotSetMethodSmp 3200 9600 -0.75 4 1200 2500 0.4 ms.pjg
rm ms.pjg
java -Dpj.schedule="guided(300)" -Dpj.nt=8 MandelbrotSetMethodSmp 3200 9600 -0.75 4 1200 2500 0.4 ms.pjg
rm ms.pjg
