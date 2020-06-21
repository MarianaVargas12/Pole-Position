//
// Created by maria on 6/14/2020.
//
#include <stdbool.h>
#include "player.h"

//actualiza la localizacion del jugador
void updateLocation(Player *player, int x, int y){
    player->x=x;
    player->y=y;
}
bool isAlive(Player *player){
    if(player->car.lives>0){
        return true;
    }else{
        return false;

    }
}
void playerGetData(Player *player){
    printf("Player No: %d, Name: %s, Points: %d\nCar: ", player->number, player->name, player->points);
    carGetData(&player->car);
}
void addPoints(Player *player,const int points){
    player->points += points;

}
void playerData(Player *player, int num){
    player->number= num;
    player->points=0;
    Car car;
    carData(&car);
    player->car = car;
}