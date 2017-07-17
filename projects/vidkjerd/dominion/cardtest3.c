#include <stdlib.h>
#include <assert.h>
#include "dominion.h"

//This function tests the smithy function
int main(int argc, char** argv)
{
	//local variables
	int i, j, numCards, deckCards;
	j = 1;
	
	//initialize game state and set specific variables
	struct gameState state;
	int k[10] = {adventurer, gardens, embargo, village, minion, mine, cutpurse,
           sea_hag, tribute, smithy};
	i = initializeGame(3, k, 3, &state);
	state.whoseTurn = 0;
	
	//get some infor before calling function
	numCards = state.handCount[0];
	deckCards = state.deckCount[0];
	
	j = cardEffect(smithy, 0, 0, 0, &state, 0, 0);
	
	//check new calues
	assert(state.handCount[0] == numCards + 2);
	assert(state.deckCount[0] == deckCards - 3);
	assert(j == 0);
	
	return 0;
}
