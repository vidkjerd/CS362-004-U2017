#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "dominion.h"

//This function tests the garden function
int main(int argc, char** argv)
{
	int i;
	i = gardenCard();
	assert(i == -1);
	return 0;
}
