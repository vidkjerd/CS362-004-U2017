#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "dominion.h"

//This function tests the getCost function
int main(int argc, char** argv)
{
	//initialize local variable
	int i, j;
	//create array of prices of the different cards
	int prices[] = {0, 2, 5, 8, 0, 3, 6, 6, 5, 4, 4, 5, 4, 4, 3, 4, 3, 5, 3, 5, 3, 4, 2, 5, 4, 4, 4};
	
	//call the function for every card number
	for(i = 0; i < 27; i++)
	{
		j = getCost(i);
		assert(j == prices[i]);
	}
	
	//test with a bogus numbers
	j = getCost(-1);
	assert(j == -1);
	j = getCost(35);
	assert(j == -1);
	
	return 0;
}
