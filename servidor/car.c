//
// Created by mariana on 6/13/2020.
//
#include "car.h"
void carData(Car *car){
    car->speed = ROADSPEED;
    car->turbo_on = 0;
    car->hole_on = 0;
    car->speedNext= ROADSPEED;
    car->lives = 3;
    car->tile = 0;
    car->yPosition = 0;
    car->xPosition = 0;
    car->xNext = 0;
    car->yNext = 0;
}
void carGetData(Car *car){
    printf("Color: %d, Lives: %d, Speed: %d, X Pos: %d, Y Pos: %d, Turbo: %d, Tile Type: %d \n",
           car->color, car->lives, car->speed, car->xPosition, car->yPosition, car->turbo_on,car->tile);

}
void CarMove(Car *car, int x_pos, int y_pos){
    car->xNext = x_pos;
    car->yNext = y_pos;
}

void CarTurbo(Car *car) {
    if (car->turbo_on == 0) {
        car->turbo_on = 1;
        car->speed = TURBOSPEED;
    } else if (car->turbo_on == 1) {
        car->turbo_on = 0;
        car->speed = ROADSPEED;

        printf("Turbo: %d, Speed: %d\n", car->turbo_on, car->speed);
    }
}

void CarHole(Car *car){
    if (car->hole_on == 0) {
        car->hole_on = 1;
        car->speed = HOLESPEED;
    } else if (car->hole_on == 1) {
        car->hole_on = 0;
        car->speed = ROADSPEED;

        printf("Hole: %d, Speed: %d\n", car->hole_on, car->speed);
    }
}