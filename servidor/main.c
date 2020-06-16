#include <stdio.h>
#include<string.h>    //strlen
#include<stdlib.h>

#include "player.h"
#include "game.h"

int main() {
    Game game;
    gameInitialize(&game);
    gamePrintMatrix(&game);
    return 0;
}

//input de consola
void* read_console(Connection_handler_args* args){
    char *line = NULL;
    int x;
    int y;
    int rounds;
    size_t len = 0;
    ssize_t read = 0;
    while (read > -1){
        read = getline(&line, &len, stdin);
        printf("line = %s", line);
        if (strcmp(line, "start\n")==0){
            printf("vueltas = ");
            getline(&line, &len, stdin);
            rounds = atoi(line);
            gameStart(args->game,rounds);
        }
        else if (strcmp(line, "life\n")==0){
            printf("x = ");
            getline(&line, &len, stdin);
            x = atoi(line);
            printf("y = ");
            getline(&line, &len, stdin);
            y = atoi(line);
            gameAddObject(args->game, x, y,LIVE);

        }
        else if (strcmp(line, "hole\n")==0){
            printf("x = ");
            getline(&line, &len, stdin);
            x = atoi(line);
            printf("y = ");
            getline(&line, &len, stdin);
            y = atoi(line);
            gameAddObject(args->game, x, y,HOLE);

        }
        else if (strcmp(line, "turbo\n")==0){
            printf("x = ");
            getline(&line, &len, stdin);
            x = atoi(line);
            printf("y = ");
            getline(&line, &len, stdin);
            y = atoi(line);
            gameAddObject(args->game, x, y,TURBO);

        }
        else if (strcmp(line, "print\n")==0){
            Game_print_matrix(args->game);


        }
    }
}