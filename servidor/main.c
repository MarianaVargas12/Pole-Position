#include <stdio.h>
#include<string.h>    //strlen
#include<stdlib.h>
#include <w32api/wsman.h>

#include "player.h"
#include "game.h"

int main() {

    Player* player1;
    Player* player2;
    playerData(&player1,1,"mariana");

    playerData(&player2,2,"sarah");

    playerGetData(&player1);
    playerGetData(&player2);
    return 0;
}

//lee y escribe al dueno para agregar objetos
void* read_console(Game* game){
    char *line = NULL;
    int x;
    int y;
    size_t len = 0;
    ssize_t read = 0;
    while (read > -1){
        read = getline(&line, &len, stdin);
        printf("line = %s", line);
        if (strcmp(line, "start\n")==0){
            gameStart(game);
        }
        else if (strcmp(line, "life\n")==0){
            printf("x = ");
            getline(&line, &len, stdin);
            //convierte el string a numero
            x = atoi(line);
            printf("y = ");
            getline(&line, &len, stdin);
            y = atoi(line);
            gameLife(game, x, y);

        }
        else if (strcmp(line, "hole\n")==0){
            printf("x = ");
            getline(&line, &len, stdin);
            x = atoi(line);
            printf("y = ");
            getline(&line, &len, stdin);
            y = atoi(line);
            gameHole(game, x, y);

        }
        else if (strcmp(line, "turbo\n")==0){
            printf("x = ");
            getline(&line, &len, stdin);
            x = atoi(line);
            printf("y = ");
            getline(&line, &len, stdin);
            y = atoi(line);
            gameTurbo(game, x, y);

        }
        else if (strcmp(line, "print\n")==0){
            gamePrintMatrix(game);


        }
    }
}