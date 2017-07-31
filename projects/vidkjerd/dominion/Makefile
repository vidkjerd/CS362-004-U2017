CFLAGS = -Wall -fpic -coverage -lm

rngs.o: rngs.h rngs.c
	gcc -c rngs.c -g  $(CFLAGS)

dominion.o: dominion.h dominion.c rngs.o
	gcc -c dominion.c -g  $(CFLAGS)

playdom: dominion.o playdom.c
	gcc -o playdom playdom.c -g dominion.o rngs.o $(CFLAGS)
#To run playdom you need to entere: ./playdom <any integer number> like ./playdom 10*/
testDrawCard: testDrawCard.c dominion.o rngs.o
	gcc  -o testDrawCard -g  testDrawCard.c dominion.o rngs.o $(CFLAGS)

badTestDrawCard: badTestDrawCard.c dominion.o rngs.o
	gcc -o badTestDrawCard -g  badTestDrawCard.c dominion.o rngs.o $(CFLAGS)

testBuyCard: testDrawCard.c dominion.o rngs.o
	gcc -o testDrawCard -g  testDrawCard.c dominion.o rngs.o $(CFLAGS)

testAll: dominion.o testSuite.c
	gcc -o testSuite testSuite.c -g  dominion.o rngs.o $(CFLAGS)

interface.o: interface.h interface.c
	gcc -c interface.c -g  $(CFLAGS)

runtests: testDrawCard 
	./testDrawCard &> unittestresult.out
	gcov dominion.c >> unittestresult.out
	cat dominion.c.gcov >> unittestresult.out


player: player.c interface.o
	gcc -o player player.c -g  dominion.o rngs.o interface.o $(CFLAGS)

all: playdom player testDrawCard testBuyCard badTestDrawCard

clean:
	rm -f *.o playdom.exe playdom player player.exe  *.gcov *.gcda *.gcno *.so *.out testDrawCard testDrawCard.exe unittest1 unittest2 unittest3 unittest4 cardtest1 cardtest2 cardtest3 cardtest4 randomtestadventurer randomtestcard1 randomtestcard2
	
makeTests: unittest1 unittest2 unittest3 unittest4 cardtest1 cardtest2 cardtest3 cardtest4 randomtestadventurer randomtestcard1 randomtestcard2

unittest1:
	gcc -fprofile-arcs -ftest-coverage dominion.c unittest1.c rngs.c -o unittest1 -lm

unittest2:
	gcc -fprofile-arcs -ftest-coverage dominion.c unittest2.c rngs.c -o unittest2 -lm
	
unittest3:
	gcc -fprofile-arcs -ftest-coverage dominion.c unittest3.c rngs.c -o unittest3 -lm

unittest4:
	gcc -fprofile-arcs -ftest-coverage dominion.c unittest4.c rngs.c -o unittest4 -lm

cardtest1:
	gcc -fprofile-arcs -ftest-coverage dominion.c cardtest1.c rngs.c -o cardtest1 -lm

cardtest2:
	gcc -fprofile-arcs -ftest-coverage dominion.c cardtest2.c rngs.c -o cardtest2 -lm
	
cardtest3:
	gcc -fprofile-arcs -ftest-coverage dominion.c cardtest3.c rngs.c -o cardtest3 -lm

cardtest4:
	gcc -fprofile-arcs -ftest-coverage dominion.c cardtest4.c rngs.c -o cardtest4 -lm
	
randomtestcard1:
	gcc -fprofile-arcs -ftest-coverage dominion.c randomtestcard1.c rngs.c -o randomtestcard1 -lm -g
	
randomtestcard2:
	gcc -fprofile-arcs -ftest-coverage dominion.c randomtestcard2.c rngs.c -o randomtestcard2 -lm -g 
	
randomtestadventurer:
	gcc -fprofile-arcs -ftest-coverage dominion.c randomtestadventurer.c rngs.c -o randomtestadventurer -lm -g

randomtestcard1.out: randomtestcard1
	./randomtestcard1
	gcov dominion.c >> randomtestcard1.out
	cat dominion.c.gcov >> randomtestcard1.out

randomtestcard2.out: randomtestcard2
	./randomtestcard2
	gcov dominion.c >> randomtestcard2.out
	cat dominion.c.gcov >> randomtestcard2.out
	
randomtestadventurer.out: randomtestadventurer
	./randomtestadventurer
	gcov dominion.c >> randomtestadventurer.out
	cat dominion.c.gcov >> randomtestadventurer.out	
	
runTest: 
	./unittest1 &> testResults.out
	./unittest2 >> testResults.out
	./unittest3 >> testResults.out
	./unittest4 >> testResults.out
	./cardtest1 >> testResults.out
	./cardtest2 >> testResults.out
	./cardtest3 >> testResults.out
	./cardtest4 >> testResults.out
	
#	./randomtestadventurer >> testResults.out
#	./randomtestcard1 >> testResults.out
#	./randomtestcard2 >> testResults.out
	
	gcov dominion.c >> testResults.out
	cat dominion.c.gcov >> testResults.out

