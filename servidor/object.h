//
// Created by maria on 6/14/2020.
//

#ifndef SERVIDOR_OBJECT_H
#define SERVIDOR_OBJECT_H
typedef struct Object{
    int alive;
    int type;
    int id;
    int x;
    int y;
    int playerNumber;
}Object;

void Object_initialize(Object* obj, int type, int id, int x, int y, int alive);
void bomb(Object* obj, int type, int id, int x, int y, int alive,int player);

#endif //SERVIDOR_OBJECT_H
