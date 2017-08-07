#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <assert.h>
#include "dominion.h"

//This function tests the GameOver function
int main(int argc, char** argv)
{
	//Initialize local variables and other things
	srand(time(NULL));
	//int a = rand();
	int a, b, c, f, numCards;
	numCards = rand() % 20 + 3; 
	
	//initialize game state
	struct gameState* state = malloc(sizeof(struct gameState));
	
	//set the required datas in the gameState
	state->supplyCount[province] = 1;
	for(a = 0; a < numCards; a++)
	{
		state->supplyCount[a] = 0;
	}
	
	//this checks the second condition of gameover (3 supply = 0)
	b = isGameOver(state);
	assert(b == 1);
	
	//reset variables and check first condition
	state->supplyCount[province] = 0;
	for(a = 0; a < numCards; a++)
	{
		state->supplyCount[a] = 1;
	}
	c = isGameOver(state);
	
	assert(c == 1);
	
	//reset variables and run if game is not over
	state->supplyCount[province] = 1;
	for(a = 0; a < 25; a++)
	{
		state->supplyCount[a] = 1;
	}
	f = isGameOver(state);
	assert(f == 0);
	
	return 0;
}
	
	
	
