#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <time.h>
#include <math.h>
#include "dominion.h"

#define NUMTESTS 300000

/*
 * Drake Vidkjer
 * This Function tests the smithy card using a random method.
 * 7/30/17
 */
int main()
{
	//define local variables
	int numPlayer, curPlayer, initGood, effectGood, curHandCount;
	struct gameState state;
	int k[10] = {adventurer, gardens, embargo, village, minion, mine, cutpurse, remodel, tribute, smithy};
	
	
	//initialize variables
	srand(time(NULL));
	
	for(int i = 0; i < NUMTESTS; i++)
	{
		printf("\n \n");
		
		//initialize a new random gamestate and num players and such
		numPlayer = (rand() % 4) + 1;
		curPlayer = (rand() % numPlayer) + 1;
		initGood = initializeGame(numPlayer, k, rand(), &state);
		
		//Make sure game initialized correctly
		if(initGood != 0)
		{
			printf("Test %d failed at game state initialization\n", i);
			continue;
		}
		else
		{
			printf("Test %d Game initilization good with current player %d of %d players\n", i, curPlayer, numPlayer);
		}
		
		//finish setting up specifics of game state
		state.whoseTurn = curPlayer;
		state.deckCount[curPlayer] = rand() % MAX_DECK;
		state.discardCount[curPlayer] = rand() % MAX_DECK;
		state.handCount[curPlayer] = rand() % MAX_HAND;
		
		//get info needed to see if ran correctly and then play the card
		curHandCount = state.handCount[curPlayer];
		effectGood = cardEffect(smithy, 0, 0, 0, &state, 0, 0);
		
		//check to make sure card ran correctly
		if(effectGood != 0)
		{
			printf("Test %d failed to play card\n", i);
			continue;
		}
		if(state.handCount[curPlayer] != (curHandCount + 3))
		{
			printf("Test %d failed to have the correct card effect\n", i);
			continue;
		}
		else
		{
			printf("Test %d had correct card effect", i);
		}
	}
	
	printf("\nAll tests executed successfully\n");
	return 0;	
}
