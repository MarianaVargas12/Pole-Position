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
    int matrixGame [100][100];
    int colors[4];
    Player players[4];
    Object objects[70];
    int started;
}Game;

void Game_add_object(Game *game, int x, int y, int type);
void gameHole(Game *game, int x_pos, int y_pos);
void Game_collision(Game *game, int player, int id_object);
void gameAvailableColor(Game *game, int color);
void gameStart(Game* game);
void gameInitialize(Game *game);
void gamePrintMatrix(Game *game);
void gameHole(Game *game, int x_pos, int y_pos);
void gameLife(Game *game, int x_pos, int y_pos);
void gameTurbo(Game *game, int x_pos, int y_pos);
bool Game_set_player_color(Game *game, int num_player, int color);
bool color_available(Game *game, int color);
void blockColor(Game *game, int color);
void Game_get_players_data(Game *game);

#endif //SERVIDOR_GAME_H
