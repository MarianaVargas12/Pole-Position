//
// Created by mariana on 6/13/2020.
//

#ifndef SERVIDOR_PLAYER_H
#define SERVIDOR_PLAYER_H
#include "car.h"
#include "object.h"

typedef struct Player{
    int x;
    int y;
    int number;
    int points;
    char name[50];
    Car car;
    int rounds;
} Player;

void updateLocation(Player *player,int x,int y);
void playerGetData(Player *player);
void addPoints(Player *player,const int points);
void playerData(Player *player, int number,int *name, int rounds);

#endif //SERVIDOR_PLAYER_H
