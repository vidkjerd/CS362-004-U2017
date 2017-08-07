#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <time.h>
#include <math.h>
#include "dominion.h"

#define NUMTESTS 300000

/*
 * Drake Vidkjer
 * This Function tests the adventurer card using a random method.
 * 7/30/17
 */
int main()
{
	//define local variables
	struct gameState state;
	int numPlayer, curPlayer, initGood, effectGood, curHandCount, j, preTre, postTre, deckTre;
	int k[10] = {adventurer, gardens, embargo, village, minion, mine, cutpurse, remodel, tribute, smithy};
	
	
	//initialize variables
	srand(time(NULL));
	
	for(int i = 0; i < NUMTESTS; i++)
	{
		printf("\n \n");
		
		//initialize a new random gamestate. and num players and such
		numPlayer = (rand() % 4) + 1;
		curPlayer = (rand() % numPlayer) + 1;
		initGood = initializeGame(numPlayer, k, rand(), &state);
		preTre = 0;
		postTre = 0;
		deckTre = 0;
		
		
		//Make sure game initialized correctly
		if(initGood != 0)
		{
			printf("Test %d failed at game state. initialization\n", i);
			continue;
		}
		else
		{
			printf("Test %d Game initilization good with current player %d of %d players\n", i, curPlayer, numPlayer);
		}
		
		//finish setting up specifics of game state.
		state.whoseTurn = curPlayer;
		state.deckCount[curPlayer] = rand() % MAX_DECK;
		state.discardCount[curPlayer] = rand() % MAX_DECK;
		state.handCount[curPlayer] = rand() % MAX_HAND;
		
		//generate a random hand and deck for the player, also see how many treasure cards placed
		for(j = 0; j < state.handCount[curPlayer]; j++)
		{
			state.hand[curPlayer][j] = rand() % (treasure_map + 1);
			
			if(state.hand[curPlayer][j] >= copper && state.hand[curPlayer][j] <= gold)
			{
				preTre++;
			}
		}
		for(j = 0; j < state.discardCount[curPlayer]; j++)
		{
			state.discard[curPlayer][j] = rand() % (treasure_map + 1);
			
			if(state.hand[curPlayer][j] >= copper && state.hand[curPlayer][j] <= gold)
			{
				//preTre++;
			}
		}
		for(j = 0; j < state.deckCount[curPlayer]; j++)
		{
			state.deck[curPlayer][j] = rand() % (treasure_map + 1);
			if(state.hand[curPlayer][j] >= copper && state.hand[curPlayer][j] <= gold)
			{
				deckTre++;
			}
		}
		
		//get info needed to see if ran correctly and then play the card
		curHandCount = state.handCount[curPlayer];
		effectGood = cardEffect(adventurer, 0, 0, 0, &state, 0, 0);
		
		//check to make sure card ran correctly
		if(effectGood == 0)
		{
			printf("Test %d played the card successfully\n", i);
		}
		else
		{
			printf("Test %d had failed to play card", i);
			continue;
		}
		
		//check to make sure card had correct effect.
		for(j = 0; j < state.handCount[curPlayer]; j++)
		{
			if(state.hand[curPlayer][j] >= copper && state.hand[curPlayer][j] <= gold)
			{
				postTre++;
			}
		}
		if(state.handCount[curPlayer] != (curHandCount + 2))
		{
			printf("Test %d failed to add 2 cards\n", i);
			continue;
		}
		else if((postTre != (preTre + 2)) && (deckTre >= 2))
		{
			printf("Test %d did not add 2 treasure cards\n", i);
			printf("pre %d post %d deck %d", preTre, postTre, deckTre);
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
