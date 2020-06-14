//
// Created by maria on 6/14/2020.
//
#include "player.h"

//actualiza la localizacion del jugador
void updateLocation(Player *player, int x, int y){
    player->x=x;
    player->y=y;
}

void playerGetData(Player *player){
    printf("Player No: %d, Name: %s, Points: %d\nCar: ", player->number, player->name, player->points);
    carGetData(&player->car);
}
void addPoints(Player *player,const int points){
    player->points += points;

}
void playerData(Player *player, int num, int *name){
    player->number= num;
    player->points=0;
    strcpy(player->name, name);

    Car car;
    carData(&car);
    player->car = car;
}