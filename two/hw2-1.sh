#!/bin/sh

java -Dpj.schedule=fixed -Dpj.nt=1 MandelbrotSetMethodSmp 3200 9600 -0.75 4 1200 2500 0.4 ms.pjg
