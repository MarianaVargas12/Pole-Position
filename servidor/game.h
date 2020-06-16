//
// Created by maria on 6/14/2020.
//

#ifndef SERVIDOR_GAME_H
#define SERVIDOR_GAME_H
#include "car.h"
#include <stdio.h>
#include "player.h"
#include "car.h"
#include <stdbool.h>
#include "constant.h"

typedef  struct Game{
    int Started;
    int matrixGame [100][100];
    int colors[4];
    Player players[4];
    Object objects[70];
    int rounds;
    int final;
}Game;

bool everyone(Game* game);
void Final(Game *game,int player);
void Game_add_object(Game *game, int x, int y, int type);
void gameHole(Game *game, int x_pos, int y_pos);
void GameCollision(Game *game, int player);
void GameAssigned(Game *game, int player, int id_object);
void gameBomb(Game *game, int player, int x, int y);
void gameAddObject(Game *game, int x_pos,int y_pos, int type);
void gameAvailableColor(Game *game, int color);
void gameStart(Game* game,int round);
void gameInitialize(Game *game);
void gamePrintMatrix(Game *game);
void gameHole(Game *game, int x_pos, int y_pos);
void gameLife(Game *game, int x_pos, int y_pos);
void gameTurbo(Game *game, int x_pos, int y_pos);
bool Game_set_player_color(Game *game, int num_player, int color);
bool color_available(Game *game, int color);
void blockColor(Game *game, int color);
void Game_get_players_data(Game *game);
void gameMovement(Game *game, int player);

#endif //SERVIDOR_GAME_H
