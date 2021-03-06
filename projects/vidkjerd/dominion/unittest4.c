#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <assert.h>
#include "dominion.h"

/*This function tests the numHandCards function
 * The function just returns the integer from the game state, so all we
 * need to check is if it returns the correct variable. No real need to
 * check max/mins.
 */
int main(int argc, char** argv)
{
	//Initialize local variables and other things
	srand(time(NULL));
	int hands = rand();
	int turn = rand() % 20;
	int i;
	
	//initialize game state and set the necessary vaurables
	struct gameState* state = malloc(sizeof(struct gameState));
	state->handCount[turn] = hands;
	state->whoseTurn = turn;
	
	//call the function and  check if it returned the right thing
	i = numHandCards(state);
	assert(i == hands);
	
	return 0;
}
