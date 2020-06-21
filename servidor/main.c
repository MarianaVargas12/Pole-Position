#include <stdio.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <string.h>
#include <unistd.h>
#include <pthread.h>
#include <json-c/json.h>
#include <time.h>

#include <math.h>
#include "player.h"
#include "game.h"
#include "constant.h"

typedef struct Connection_handler_args {
    int* server_fd;
    Game* game;
}Connection_handler_args;

void* connection_handler(Connection_handler_args*);
//Funcion para obtener en un json_object, las posiciones de todos los objetos del mapa
void get_objects(json_object *json, Game *game, json_object* lifeArray, json_object* holeArray, json_object* turboArray, json_object* bombArray,json_object* playerArray, int currentPlayer);
void* read_console(Connection_handler_args*);

int get_id(Game* game, int i, int j);
clock_t t;
int main() {
    Game game;
    gameInitialize(&game);

    int puerto = 8888;
    int server_fd, new_socket;
    struct sockaddr_in address;
    int opt = 1;
    int addrlen = sizeof(address);
    int  started;
    struct sockaddr_in server , client;

    // Inicializacion sockets
    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0)
    {
        perror("socket failed");
        exit(EXIT_FAILURE);
    }

    // Agregar puerto
    if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEADDR,
                   &opt, sizeof(opt)))
    {
        perror("setsockopt");
        exit(EXIT_FAILURE);
    }
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons( puerto );

    if (bind(server_fd, (struct sockaddr *)&address,
             sizeof(address))<0)
    {
        perror("bind failed");
        exit(EXIT_FAILURE);
    }


    if (listen(server_fd, 3) < 0)
    {
        perror("listen");
        exit(EXIT_FAILURE);
    }
    //Se aceptan conexiones
    puts("Esperando conexiones...");
    pthread_t thread_id;


    Connection_handler_args args = { &started, &game};

    pthread_create(&thread_id, NULL, (void *(*)(void *)) read_console, &args);

    while ((new_socket = accept(server_fd, (struct sockaddr *)&address,
                                (socklen_t*)&addrlen)))
    {

        puts("Connection accepted");
        Connection_handler_args args = { &new_socket, &game};

        if(pthread_create(&thread_id , ((void *) 0) , (void *(*)(void *)) connection_handler, &args) < 0)
        {
            perror("could not create thread");
            return 0;
        }
    }

    if (new_socket < 0)
    {
        perror("accept failed");
        return 0;
    }
    return 0;

    return 0;
}

//input de consola
void* read_console(Connection_handler_args* args){
    char *line = NULL;
    int x;
    int y;
    int rounds;
    size_t len = 0;
    ssize_t read = 0;
    while (read > -1){
        read = getline(&line, &len, stdin);
        printf("line = %s", line);
        if (strcmp(line, "start\n")==0){
            printf("vueltas = ");
            getline(&line, &len, stdin);
            rounds = atoi(line);
            gameStart(args->game,rounds);
            gamerounds(args->game,rounds);
            t = clock();
        }
        else if (strcmp(line, "life\n")==0){
            printf("x = ");
            getline(&line, &len, stdin);
            x = atoi(line);
            printf("y = ");
            getline(&line, &len, stdin);
            y = atoi(line);
            gameAddObject(args->game, x, y,LIVE);

        }
        else if (strcmp(line, "hole\n")==0){
            printf("x = ");
            getline(&line, &len, stdin);
            x = atoi(line);
            printf("y = ");
            getline(&line, &len, stdin);
            y = atoi(line);
            gameAddObject(args->game, x, y,HOLE);

        }
        else if (strcmp(line, "turbo\n")==0){
            printf("x = ");
            getline(&line, &len, stdin);
            x = atoi(line);
            printf("y = ");
            getline(&line, &len, stdin);
            y = atoi(line);
            gameAddObject(args->game, x, y,TURBO);

        }
        else if (strcmp(line, "print\n")==0){
            gamePrintMatrix(args->game);


        }
    }
}

// Función que maneja las conexiones y envia lo que corresponda
void *connection_handler(Connection_handler_args* args) {
    int sock = *args->server_fd;
    char* mensaje = "Holaaaa";
    char enviar[2000];
    char buffer[1024] = {0};
    int valread, read_size;
    char message[2000], client_message[2000];
    Game* game = args->game;
    game->rounds = 1;

    json_object* connection_json;//Creacion del objeto json
    connection_json = json_object_new_object();
    int players_in_game = 0;
    int number;

    for (int i = 0; i < MAXPLAYERS; i++){
        if (game->players[i].number != -1){
            players_in_game++;
        }
    }
    printf("%d\n",players_in_game);
    //No permite conexión si el número de jugadores en línea es mayor al máximo
    if (players_in_game >= MAXPLAYERS){
        connection_json = json_object_new_object();
        json_object_object_add(connection_json,"command", json_object_new_string("full"));
        strcpy(message, json_object_to_json_string(connection_json));
        message[strlen(message)]='\n';
        write(sock , message , strlen(message));
        memset(message, 0 , 2000);
        return 0;
    }

    //Si hay campo, pide el nombre y le pasa al cliente los colores disponibles
    connection_json = json_object_new_object();
    json_object_object_add(connection_json,"command", json_object_new_string("identifiquese"));
    strcpy(message, json_object_to_json_string(connection_json));
    message[strlen(message)]='\n';
    write(sock , message , strlen(message));
    memset(message, 0 , 2000);

    //Espera mensaje del cliente
    if( (read_size = recv(sock , client_message , 2000 , 0)) > 0 ) {
        client_message[read_size] = '\0';

        printf("%s\n", client_message);

        connection_json = json_tokener_parse(client_message);

        printf("%s\n",json_object_to_json_string(connection_json));

        //Entrada de jugadores
        for (int i = 0; i < MAXPLAYERS; ++i) {
            if (game->players[i].number == -1){
                game->players[i].number = i;
                game->jugadores += 1;
                number = i;
                playerData(&game->players[i],i);
                //
                game->players[number].rounds=1;
                if ( (read_size = recv(sock , client_message , 2000 , 0)) > 0 ){
                    client_message[read_size] = '\0';

                    printf("%s\n", client_message);

                    connection_json = json_tokener_parse(client_message);

                    printf("%s\n",json_object_to_json_string(connection_json));

                    printf("%s\n", json_object_get_string(json_object_object_get(connection_json, "command")));

                    //Si le pide los colores disponibles
                    if (strcmp(json_object_get_string(json_object_object_get(connection_json, "command")),"get_colors")==0){
                        connection_json = json_object_new_object();
                        json_object_object_add(connection_json,"command", json_object_new_string("choose_a_color"));
                        json_object* my_array;
                        my_array = json_object_new_array();

                        //Llena el array con los colores disponibles
                        for (int i = 0; i < MAXPLAYERS; i++){
                            json_object_array_add(my_array, json_object_new_int(game->colors[i]));
                        }

                        json_object_object_add(connection_json, "available_colors", my_array);//Array con colores
                        json_object_object_add(connection_json, "player_num", json_object_new_int(number));//Numero de jugador
                        memset(message,0,2000);
                        strcpy(message, json_object_to_json_string(connection_json));
                        message[strlen(message)]='\n';
                        write(sock , message , strlen(message));//Envia el mensaje
                                //Recibe la respuesta del cliente
                        read_size = recv(sock , client_message , 2000 , 0);
                        client_message[read_size] = '\0';

                        printf("%s\n", client_message);

                        //Asignacion de color segun eleccion del cliente
                        connection_json = json_tokener_parse(client_message);
                        int color = json_object_get_int(json_object_object_get(connection_json, "color"));
                        if ( color >= 0){
                            Game_set_player_color(game, i, color);
                            game->players[i].car.tile=ROAD;
                        }
                        else{
                            Game_set_player_color(game, i, game->colors[0]);
                            game->players[i].car.tile=ROAD;
                        }
                        puts("Color:");
                        printf("%d \n",game->players[number].car.color);
                    }
                }
                connection_json = json_object_new_object();
                json_object_object_add(connection_json,"command", json_object_new_string("position_yourself"));//Pide la posicion inicial
                json_object_object_add(connection_json, "player", json_object_new_int(i)); //Envia el numero del jugador
                memset(message,0,2000);
                strcpy(message, json_object_to_json_string(connection_json));
                message[strlen(message)]='\n';
                write(sock , message , strlen(message));//envia el mensaje
                break;
            }
        }

        //clear the message buffer
        memset(client_message, 0, 2000);
    }

    //Este while mantiene la conexión con el cliente y actualiza lo necesario
    while( (read_size = recv(sock , client_message , 2000 , 0)) > 0 )
    {
        client_message[read_size] = '\0';

        connection_json = json_tokener_parse(client_message);
        //Le suma 1 al puntaje del jugador cada 10 segundos
        if(((clock() - t)/CLOCKS_PER_SEC >= 10) && game->players[number].car.lives > 0 && game->players[number].rounds > 0 ){
            t=clock();
            game->players[number].points += 1;
        }

        //verifica qué se debe hacer

        //Si el cliente le envia su nueva ubicacion
        if (strcmp(json_object_get_string(json_object_object_get(connection_json, "command")), "update_location") == 0){ //Si el comando es update_location
            if(game->rounds == 0){
                puts("GAME OVER");
                json_object* my_array = json_object_new_array();
                json_object* arrColor = json_object_new_array();
                connection_json = json_object_new_object();
                json_object_object_add(connection_json, "command", json_object_new_string("GameOver"));
                for (int i = 0; i < game->jugadores; i++){
                    json_object_array_add(my_array, json_object_new_int(game->players[i].points));
                    json_object_array_add(arrColor, json_object_new_int(game->players[i].car.color -10 ));
                }
                json_object_object_add(connection_json, "puntos", my_array);//Array con los puntos de todos
                json_object_object_add(connection_json, "players", arrColor);//Array con los colores de todos
                memset(message,0,2000);
                strcpy(message, json_object_to_json_string(connection_json));
                message[strlen(message)]='\n';
                write(sock , message , strlen(message));
                puts(message);
            }
            else{
                CarMove(&game->players[number].car,round(json_object_get_double(json_object_object_get(connection_json, "x"))/30), round(((int)json_object_get_double(json_object_object_get(connection_json, "y")))/30));
                game->players[number].x=(int)json_object_get_double(json_object_object_get(connection_json, "x"));
                game->players[number].y=(int)json_object_get_double(json_object_object_get(connection_json, "y"));
                gameMovement(game,number);
                connection_json = json_object_new_object();
                //Le envia las vidas, puntos y velocidad correspondiente
                json_object_object_add(connection_json, "command", json_object_new_string("update"));
                json_object_object_add(connection_json, "vidas", json_object_new_int(game->players[number].car.lives));
                json_object_object_add(connection_json, "puntos", json_object_new_int(game->players[number].points));
                json_object_object_add(connection_json, "velocidad", json_object_new_int(game->players[number].car.speed));


                //Envia si el juego esta iniciado
                if(game->players[number].rounds == 0){
                    json_object_object_add(connection_json, "start", json_object_new_int(0));
                }
                else{
                    json_object_object_add(connection_json, "start", json_object_new_int(game->Started));
                }

                memset(message,0,2000);
                strcpy(message, json_object_to_json_string(connection_json));
                message[strlen(message)]='\n';
                write(sock , message , strlen(message));
            }
        }
        //Si el cliente le pide los objetos
        else if (strcmp(json_object_get_string(json_object_object_get(connection_json, "command")), "get_objects") == 0){

            connection_json = json_object_new_object();
            get_objects(connection_json,game,json_object_new_array(), json_object_new_array(),json_object_new_array(), json_object_new_array(),json_object_new_array(),number);
            json_object_object_add(connection_json, "command", json_object_new_string("place_objects"));
            memset(message,0,2000);
            strcpy(message, json_object_to_json_string(connection_json));
            message[strlen(message)]='\n';
            write(sock , message , strlen(message));

        }

        //Si el cliente le envia que agrego una bomba
        else if (strcmp(json_object_get_string(json_object_object_get(connection_json, "command")), "bomba") == 0){
            gameBomb(game,number,json_object_get_int(json_object_object_get(connection_json, "x"))/30, json_object_get_int(json_object_object_get(connection_json, "y"))/30 + 1);
            printf("%d\n",json_object_get_int(json_object_object_get(connection_json, "x"))/30);
            printf("%d\n",json_object_get_int(json_object_object_get(connection_json, "y"))/30);
        }

        //Cualquier otra cosa
        else{

            connection_json = json_object_new_object();
            json_object_object_add(connection_json, "command", json_object_new_string("error"));

            memset(message,0,2000);
            strcpy(message, json_object_to_json_string(connection_json));
            message[strlen(message)]='\n';
            write(sock , message , strlen(message));

        }


        //clear the message buffer
        memset(client_message, 0, 2000);
    }

    //Si se desconecta un cliente
    if(read_size == 0)
    {
        puts("Client disconnected");
        game->players[number].number = -1;
        game->players[number].points = 0;
        gameAvailableColor(game, game->players[number].car.color);//Habilita nuevamente ese color de carro

        fflush(stdout);
    }
    else if(read_size == -1)
    {
        perror("recv failed");
    }

    return 0;



}

// Modifica el json que se introduce para que contenga a todos los objetos en pantalla
void get_objects(json_object *json, Game *game, json_object* lifeArray, json_object* holeArray, json_object* turboArray, json_object* bombArray, json_object* playerArray, int currentPlayer){
    json_object* temp;
    for (int i = 0; i < ROW; i++){
        for (int j = 0; j < COLUMN; j++){
            if (game->matrixGame[i][j]==LIVE){
                temp = json_object_new_array();
                json_object_array_add(temp, json_object_new_int(j));
                json_object_array_add(temp, json_object_new_int(i));
                json_object_array_add(temp, json_object_new_int(get_id(game, i, j)));
                json_object_array_add(lifeArray, temp);
            } else if (game->matrixGame[i][j] == HOLE){
                temp = json_object_new_array();
                json_object_array_add(temp, json_object_new_int(j));
                json_object_array_add(temp, json_object_new_int(i));
                json_object_array_add(temp, json_object_new_int(get_id(game, i, j)));
                json_object_array_add(holeArray, temp);

            } else if (game->matrixGame[i][j] == TURBO){
                temp = json_object_new_array();
                json_object_array_add(temp, json_object_new_int(j));
                json_object_array_add(temp, json_object_new_int(i));
                json_object_array_add(temp, json_object_new_int(get_id(game, i, j)));
                json_object_array_add(turboArray, temp);
            }else if (game->matrixGame[i][j] == BOMB){
                temp = json_object_new_array();
                json_object_array_add(temp, json_object_new_int(j));
                json_object_array_add(temp, json_object_new_int(i));
                json_object_array_add(temp, json_object_new_int(get_id(game, i, j)));
                json_object_array_add(bombArray, temp);
            }
        }
    }

    for (int i = 0; i <MAXPLAYERS; i++){
        if (i != currentPlayer && game->players[i].number != -1 && game->players->car.xPosition != 0){
            temp = json_object_new_array();
            json_object_array_add(temp, json_object_new_int(game->players[i].x));
            json_object_array_add(temp, json_object_new_int(game->players[i].y));
            json_object_array_add(temp, json_object_new_int(game->players[i].car.color));
            json_object_array_add(playerArray, temp);
        }
    }



    json_object_object_add(json, "lives", lifeArray);
    json_object_object_add(json, "holes", holeArray);
    json_object_object_add(json, "turbos", turboArray);
    json_object_object_add(json, "bombs", bombArray);
    json_object_object_add(json, "players", playerArray);
}

//devuelve el id de un objeto en cierta posición
int get_id(Game *game, int i, int j) {
    for (int k = 0; k < MAXOBJECTS; k++){
        if (game->objects[k].y == i && game->objects[k].x == j){

            return k;
        }
    }
}