#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "dominion.h"

//This function tests the councilRoom function
int main(int argc, char** argv)
{
	//local variables
	int i, j, c, numCards;
	j = 1;
	
	//initialize game state and set specific variables
	struct gameState state;
	int k[10] = {adventurer, council_room, embargo, village, minion, mine, cutpurse,
           sea_hag, tribute, smithy};
	i = initializeGame(3, k, 3, &state);
	
	//get some preliminary info
	int prehandCount0 = state.handCount[0];
	int prehandCount1 = state.handCount[1];
	int prehandCount2 = state.handCount[2];
	int preBuy = state.numBuys;
	
	//call the function
	j = cardEffect(council_room, 0, 0, 0, &state, 0, 0);

	//make sure function exited correctly
	assert(j == 0);
	
	//make sure values modifeied are correct
	/*
	i = state.numBuys - preBuy;
	assert(i == 1);
	i = prehandCount0 - state.handCount[0];
	assert(i == 4);
	i = prehandCount1 - state.handCount[1];
	assert(i == 1);
	i = prehandCount2 - state.handCount[2];
	assert(i == 1);
	i = preBuy - state.numBuys;
	assert(i == 1);
	*/
	return 0;
}
