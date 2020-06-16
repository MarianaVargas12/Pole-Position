//
// Created by mariana on 6/14/2020.
//
#include "game.h"

//crea un hueco
void gameHole(Game *game, int x_pos, int y_pos){
    game->matrixGame[x_pos][y_pos] = HOLE;
    Game_add_object(game, x_pos, y_pos, HOLE);
}
//crea una vida
void gameLife(Game *game, int x_pos, int y_pos){
    game->matrixGame[x_pos][y_pos] = LIVE;
    Game_add_object(game, x_pos, y_pos, LIVE);
}
//crea un turbo
void gameTurbo(Game *game, int x_pos, int y_pos){
    game->matrixGame[x_pos][y_pos] = TURBO;
    Game_add_object(game, x_pos, y_pos, TURBO);
}
//poner una bomba
void gameBomb(Game *game, int player, int x, int y){
    if(game->matrixGame[x][y]==ROAD) {
        game->matrixGame[x][y] = BOMB;
        for (int i = 0; i < 70; i++) {
            if (game->objects[i].alive == 0) {
                bomb(&game->objects[i], BOMB, i, x, y, 1, player);
                break;
            }
        }
    }
}
//agrega objetos si es en la carretera
void gameAddObject(Game *game, int x_pos,int y_pos, int type){
    if(game->matrixGame[x_pos][y_pos]==ROAD){
        if(type==LIVE){
            gameLife(&game, x_pos, y_pos);
        }
        else if(type==TURBO){
            gameTurbo(&game, x_pos, y_pos);
        }
        else if(type==HOLE){
            gameHole(&game, x_pos, y_pos);
        }

    }else{
        printf("debe de ser en la carretera");
    }
}
//verifica el movimiento del carro para asignarle todo
void gameMovement(Game *game, int player){
    int x = game->players[player].car.xNext;
    int y= game->players[player].car.yNext;
    //siguiente movimiento es zacate
    if(game->matrixGame[x][y]==START){
        if(game->players[player].rounds==0){
            Final(&game,player);
        }else{
            game->players[player].rounds-=1;
        }
    }
    else if(game->matrixGame[x][y]==GRASS){
        //busca que era el lugar anterior en el que estaba
        if(game->players[player].car.speed== TURBOSPEED || game->players[player].car.speed== HOLESPEED){
            game->matrixGame[game->players[player].car.xPosition][game->players[player].car.yPosition]=ROAD;
            game->matrixGame[x][y]=PLAYER[player];
        }
        else if(game->players[player].car.speed==ROADSPEED){
            game->matrixGame[game->players[player].car.xPosition][game->players[player].car.yPosition]=ROAD;
            game->matrixGame[x][y]=PLAYER[player];
        }
        else{
            game->matrixGame[game->players[player].car.xPosition][game->players[player].car.yPosition]=GRASS;
            game->matrixGame[x][y]=PLAYER[player];
        }
        game->players[player].car.speed= GRASSSPEED;
    }
    //siguiente movimiento es carretera
    else if(game->matrixGame[x][y]=ROAD){
        //cual fue el lugar que estaba anteriormente
        if(game->players[player].car.speed== TURBOSPEED || game->players[player].car.speed== HOLESPEED){
            game->matrixGame[game->players[player].car.xPosition][game->players[player].car.yPosition]=ROAD;
            game->matrixGame[x][y]=PLAYER[player];
        }
        else if(game->players[player].car.speed==ROADSPEED){
            game->matrixGame[game->players[player].car.xPosition][game->players[player].car.yPosition]=ROAD;
            game->matrixGame[x][y]=PLAYER[player];
        }
        else{
            game->matrixGame[game->players[player].car.xPosition][game->players[player].car.yPosition]=GRASS;
            game->matrixGame[x][y]=PLAYER[player];
        }
        game->players[player].car.speed= ROADSPEED;

    }
    //tiene una colision
    else{
        GameCollision(&game, player);
        //lugar que estaba anteriormente
        if(game->players[player].car.speed== TURBOSPEED || game->players[player].car.speed== HOLESPEED){
            game->matrixGame[game->players[player].car.xPosition][game->players[player].car.yPosition]=ROAD;
            game->matrixGame[x][y]=PLAYER[player];
            game->players[player].car.speed= ROADSPEED;
            //se fija si la colision fue con un hueco o con un turbo
            if(game->players[player].car.speedNext== TURBOSPEED ){
                game->players[player].car.speed= TURBOSPEED;
            }
            else if(game->players[player].car.speedNext==HOLESPEED){
                game->players[player].car.speed= HOLESPEED;
            }
        }
        else if(game->players[player].car.speed==ROADSPEED){
            game->matrixGame[game->players[player].car.xPosition][game->players[player].car.yPosition]=ROAD;
            game->matrixGame[x][y]=PLAYER[player];
            game->players[player].car.speed=ROADSPEED;
            if(game->players[player].car.speedNext== TURBOSPEED){
                game->players[player].car.speed= TURBOSPEED;
            }
            else if(game->players[player].car.speedNext==HOLESPEED){
                game->players[player].car.speed= HOLESPEED;
            }
        }
        else{
            game->matrixGame[game->players[player].car.xPosition][game->players[player].car.yPosition]=GRASS;
            game->matrixGame[x][y]=PLAYER[player];
            game->players[player].car.speed=ROADSPEED;
            if(game->players[player].car.speedNext== TURBOSPEED){
                game->players[player].car.speed= TURBOSPEED;
            }
            else if(game->players[player].car.speedNext==HOLESPEED){
                game->players[player].car.speed= HOLESPEED;
            }
        }
    }
    game->players[player].car.xPosition = game->players[player].car.xNext;
    game->players[player].car.yPosition = game->players[player].car.yNext;
}
//verifica si ha chocado con algo
void GameCollision(Game *game, int player) {
    for(int i=0;i<MAXOBJECTS;i++){
        if(game->players[player].car.xNext==game->objects[i].x && game->players[player].car.yNext==game->objects[i].y) {
            GameAssigned(&game, player, i);
            break;
        }


    }
}
//se fija con que hizo collision
void GameAssigned(Game *game, int player, int id_object) {
      //turbo
    if (game->objects[id_object].type == TURBO){
        game->matrixGame[game->objects[id_object].y][game->objects[id_object].y] = ROAD;
        game->objects[id_object].alive = 0;
        game->players[player].car.speedNext=TURBOSPEED;
        //vida
    }else if(game->objects[id_object].type == LIVE ){
        game->matrixGame[game->objects[id_object].y][game->objects[id_object].y] = ROAD;
        game->objects[id_object].alive = 0;
        game->players[player].car.lives +=1;
        //hueco
    }else if(game->objects[id_object].type == HOLE){
        game->matrixGame[game->objects[id_object].y][game->objects[id_object].y] = ROAD;
        game->objects[id_object].alive = 0;
        game->players[player].car.speedNext=HOLESPEED;
        //bomba
    }else if(game->objects[id_object].type==BOMB){
        game->matrixGame[game->objects[id_object].y][game->objects[id_object].y] = ROAD;
        game->objects[id_object].alive = 0;
        game->players[player].car.lives -=1;
        addPoints(&game->players[game->objects[id_object].playerNumber], ATACK); //gana 100 puntos por derribar un jugador

    }
}
//agrega objetos a la lista
void Game_add_object(Game *game, int x, int y, int type) {
    for (int i = 0; i < MAXOBJECTS; i++){
        if (game->objects[i].alive == 0){
            Object_initialize(&game->objects[i],type, i, x, y, 1);
            break;
        }
    }
}
//verifica quien gana
void Final(Game *game, int player){
    if(everyone(&game) && game->final==0){
        game->players[player].points+=FIRST;
        game->final=4;
    } else if(everyone(&game) && game->final==1){
        game->players[player].points+=SECOND;
        game->final= 4;
    }else if(everyone(&game) && game->final==2){
        game->players[player].points+=THIRD;
        game->final=4;
    }else if(everyone(&game) && game->final==3){
        game->final=4;
    }else{
        if(game->final==0){
            game->players[player].points+=FIRST;
            game->final+=1;
        } else if(game->final==1){
            game->players[player].points+=SECOND;
            game->final+=1;
        }else if(game->final==2){
            game->players[player].points+=THIRD;
            game->final+=1;
        }
    }

}
bool everyone(Game* game){
    for(int i=0; i<MAXPLAYERS; i++){
        if(game->players[i].rounds!=0  || game->players[i].car.lives != 0){
            return false;
        }
    }
    return true;
}


//*****************************Inicio de juego******************************************************************
//crea la matriz
void gameInitialize(Game *game){
    //inicia todas los numeros de jugador como -1
    for (int i = 0; i < MAXPLAYERS; i++){
        game->players[i].number = -1;
    }
    game->Started = 0;

    //crea la matriz
    for(int i =0; i<ROW; i++){//filas
        for(int j =0; j<COLUMN; j++){//columnas
            if(16<=i && i<=21 && 38<=j){
                game->matrixGame[i][j] = START;
            }
            else if( 17<=i && i<=35 && 10<=j && j<=21 || 20<=i && i<=35 && 21<=j && j<=25 || 20<=i && i<=72 && 25<=j && j<=27 || 22<=i && i<=77 && 27<=j && j<=40
            || 24<=i && i<=77 && 40<=j && j<=45 || 24<=i && i<=53 && 45<=j && j<=48 || 24<=i && i<=45 && 48<=j && j<=78 ){
                game->matrixGame[i][j] = GRASS;
            }else if(0<=i && i<=11 && 0<=j && j<=23 || 0<=i && i<=16 && 30<=j && j<=43 || 0<=i && i<=18 && 43<=j && j<=84 || 0<=i && i<=100 && 84<=j && j<=100
                     || 85<=i && i<=100 && 72<=j && j<=84 || 51<=i && i<=100 && 54<=j && j<=72 || 56<=i && i<=100 && 51<=j && j<=54
                     || 83<=i && i<=100 && 21<=j && j<=51 || 75<=i && i<=100 && 0<=j && j<=21 || 42<=i && i<=100 && 0<=j && j<=18 || 11<=i && i<=42 && 0<=j && j<=3){
                game->matrixGame[i][j] = GRASS;
            }
            else{
                game->matrixGame[i][j] = ROAD; //se inicia todas las posiciones con 4 esto significa prohibido
            }
        }
    }
    //asigna un valor para los cuatro colores de carro y los agrega la lista
    for(int i=0; i<MAXPLAYERS; i++){
        game->colors[i] =   COLORS[i];
    }
}

//verifica que el color no este ocupado por otro jugador
bool Game_set_player_color(Game *game, int num_player, int color){
    for(int i =0;i<MAXPLAYERS;i++){
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

//agrega el color a desocupado
void gameAvailableColor(Game *game, int color) {
    game->colors[color-10] = color;
}

//inicializa la variable
void gameStart(Game *game,int round) {
    game->Started = 1;
    game->rounds = round;
}
//verifica que el color este OCUPADO
bool color_available(Game *game, int color){
    for(int i=0;i<MAXPLAYERS;i++){
        if(game->colors[i] == color) {
            return true;
        }
    }
    return false;
}
//bloquea el color escogido
void blockColor(Game *game, int color){
    for(int i=0; i<MAXPLAYERS; i++){
        if(game->colors[i] == color) {
            game->colors[i] = 0;
        }
    }
}
//******************************Informacion***********************************************
//imprime en la consola para que el dueno vea lo que esta pasando
void gamePrintMatrix(Game *game){
    for(int i =0;i<ROW;i++){
        printf("[");
        for(int j=0;j<COLUMN;j++){
            printf("%d, ", game->matrixGame[i][j]);
        }
        printf("]\n");
    }
    printf("\n\n");
}
//imprime los datos de los jugadores
void Game_get_players_data(Game *game){
    for(int i =0; i<MAXPLAYERS; i++){
        playerGetData(&game->players[i]);
    }
}