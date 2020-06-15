//
// Created by mariana on 6/14/2020.
//
#include "game.h"

//crea un hueco y para eso pondremos que es un 5
void gameHole(Game *game, int x_pos, int y_pos){
    game->matrixGame[y_pos][x_pos] = 5;
    Game_add_object(game, x_pos, y_pos, 5);
}
//crea una vida, su numero es el 9
void gameLife(Game *game, int x_pos, int y_pos){
    game->matrixGame[y_pos][x_pos] = 9;
    Game_add_object(game, x_pos, y_pos, 9);
}
//crea un turbo, su numero es el 6
void gameTurbo(Game *game, int x_pos, int y_pos){
    game->matrixGame[y_pos][x_pos] = 6;
    Game_add_object(game, x_pos, y_pos, 6);
}
//poner una bomba
void gameBomb(Game *game, int player, int x, int y){
    game->matrixGame[y][x] = 10;
    for (int i = 0; i < 70; i++){
        if (game->objects[i].alive == 0){
            bomb(&game->objects[i],10,i,x,y,1,player);
            break;
        }
    }

}
//verifica si ha chocado con algo
void Game_collision(Game *game, int player, int id_object) {
      //turbo
    if (game->objects[id_object].type == 6 ){
        game->matrixGame[game->objects[id_object].y][game->objects[id_object].y] = 4;
        game->objects[id_object].alive = 0;
        CarTurbo(&game->players[player].car);
        //vida
    }else if(game->objects[id_object].type == 9 ){
        game->matrixGame[game->objects[id_object].y][game->objects[id_object].y] = 4;
        game->objects[id_object].alive = 0;
        game->players[player].car.lives +=1;
        //hueco
    }else if(game->objects[id_object].type == 5){
        game->matrixGame[game->objects[id_object].y][game->objects[id_object].y] = 4;
        game->objects[id_object].alive = 0;
        game->players[player].car.speed=0;
    }else if(game->objects[id_object].type==10){
        game->matrixGame[game->objects[id_object].y][game->objects[id_object].y] = 4;
        game->objects[id_object].alive = 0;
        game->players[player].car.lives -=1;
        addPoints(&game->players[game->objects[id_object].playerNumber],100); //gana 100 puntos por derribar un jugador

    }
}



//agrega objetos a la lista
void Game_add_object(Game *game, int x, int y, int type) {
    for (int i = 0; i < 70; i++){
        if (game->objects[i].alive == 0){
            Object_initialize(&game->objects[i],type, i, x, y, 1);
            break;
        }
    }
}


//*****************************Inicio de juego******************************************************************
//crea la matriz
void gameInitialize(Game *game){
    //inicia todas los numeros de jugador como -1
    for (int i = 0; i < 4; i++){
        game->players[i].number = -1;
    }
    game->started = 0;

    //crea la matriz
    for(int i =0; i<100; i++){//filas
        for(int j =0; j<100; j++){//columnas
            game->matrixGame[i][j] = 4; //se inicia todas las posiciones con 4 esto significa prohibido
        }
    }
    //asigna un valor para los cuatro colores de carro y los agrega la lista
    for(int i=0; i<4; i++){
        game->colors[i] = i+10;
    }
}

//verifica que el color no este ocupado por otro jugador
bool Game_set_player_color(Game *game, int num_player, int color){
    for(int i =0;i<4;i++){
        if(game->players[i].number == num_player){
            if(color_available(game, color)){
                game->players[i].car.color = color;
                blockColor(game, color);
                return true;
            }
            else return false;
        }
    }
}

//agrega el color a available
void gameAvailableColor(Game *game, int color) {
    game->colors[color-10] = color;
}

//inicializa la variable
void gameStart(Game *game) {
    game->started = 1;

}
//verifica que el color este disponible
bool color_available(Game *game, int color){
    for(int i=0;i<4;i++){
        if(game->colors[i] == color) {
            return true;
        }
    }
    return false;
}
//bloquea el color escogido
void blockColor(Game *game, int color){
    for(int i=0; i<4; i++){
        if(game->colors[i] == color) {
            game->colors[i] = 0;
        }
    }
}
//******************************Informacion***********************************************
//imprime en la consola para que el dueno vea lo que esta pasando
void gamePrintMatrix(Game *game){
    for(int i =0;i<100;i++){
        printf("[");
        for(int j=0;j<100;j++){
            printf("%d, ", game->matrixGame[i][j]);
        }
        printf("]\n");
    }
    printf("\n\n");
}
//imprime los datos de los jugadores
void Game_get_players_data(Game *game){
    for(int i =0; i<4; i++){
        playerGetData(&game->players[i]);
    }
}