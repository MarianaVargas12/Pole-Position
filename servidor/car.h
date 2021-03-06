//
// Created by mariana on 6/13/2020.
//

#include <stdio.h>
#include <string.h>
#include "constant.h"
#ifndef SERVIDOR_CAR_H
#define SERVIDOR_CAR_H

typedef struct Car
{
    int lives;
    int speed;
    int speedNext;
    int turbo_on;
    int hole_on;
    int xNext;
    int yNext;
    int xPosition;
    int yPosition;
    int color;
    int tile;

}Car;

void carData(Car *car);
void carGetData(Car *car);
void CarMove(Car *car, int x_pos, int y_pos);
void CarTurbo(Car *car);
void CarHole(Car *car);


#endif //SERVIDOR_CAR_H
