//
// Created by mariana on 6/14/2020.
//

#include "object.h"

//Funcion para agregar a un objeto sus valores iniciales
void Object_initialize(Object* obj, int type, int id, int x, int y, int alive){
    obj->alive=alive;
    obj->type = type;
    obj->id = id;
    obj->x = x;
    obj->y = y;
}

void bomb(Object* obj, int type, int id, int x, int y, int alive,int player){
    obj->alive=alive;
    obj->type = type;
    obj->id = id;
    obj->x = x;
    obj->y = y;
    obj->playerNumber =player;
}
