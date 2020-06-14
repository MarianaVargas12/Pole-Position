#include <stdio.h>
#include<string.h>    //strlen
#include<stdlib.h>

#include "player.h"

int main() {

    Player* player1;
    Player* player2;
    playerData(&player1,1,"mariana");

    playerData(&player2,2,"sarah");

    playerGetData(&player1);
    playerGetData(&player2);
    return 0;
}
