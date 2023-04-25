# Raccon Raiders
#
# @file
# @version 0.1


CC=mvn

all: build

build:
		cd ./RaccoonRaiders && $(CC) clean && $(CC) compile && $(CC) package

run:
		cd ./RaccoonRaiders && $(CC) clean && $(CC) compile && $(CC) package && cd ./target && java -classpath RaccoonRaiders.jar com.group21.Launcher

test:
		cd ./RaccoonRaiders && $(CC) clean && $(CC) test
