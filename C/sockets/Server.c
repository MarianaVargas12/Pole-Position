#include <stdio.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <string.h>
#include <unistd.h>
#include <pthread.h>
#include <json-c/json.h>

typedef struct Connection_handler_args {
    int* server_fd;
    char* mensaje;
}Connection_handler_args;
void* connection_handler(Connection_handler_args*);

int escuchaEnvia(int puerto,char* mensaje)
{
    int server_fd, new_socket;
    struct sockaddr_in address;
    int opt = 1;
    int addrlen = sizeof(address);
    int  started;
    struct sockaddr_in server , client;

    // Creating socket file descriptor
    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0)
    {
        perror("socket failed");
        exit(EXIT_FAILURE);
    }

    // Forcefully attaching socket to the port
    if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEADDR,
                   &opt, sizeof(opt)))
    {
        perror("setsockopt");
        exit(EXIT_FAILURE);
    }
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons( puerto );


    // Forcefully attaching socket to the port 8080
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
    //Se acepttan conexiones
    puts("Waiting for incoming connections...");
    pthread_t thread_id;


    Connection_handler_args args = { &started, mensaje };

    pthread_create(&thread_id, NULL, (void *(*)(void *)) connection_handler, &args);

    while ((new_socket = accept(server_fd, (struct sockaddr *)&address,
                             (socklen_t*)&addrlen)))
    {

        puts("Connection accepted");
        Connection_handler_args args = { &new_socket, mensaje};

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

}

// Función que maneja las conexiones y envia lo que corresponda
void *connection_handler(Connection_handler_args* args) {
    int sock = *args->server_fd;
    char* mensaje = args->mensaje;
    char enviar[2000];
    char buffer[1024] = {0};
    int valread;
    json_object* connection_json;//Creacion del objeto json
    connection_json = json_object_new_object();
    //Crea JSON con el mensaje a enviar
    json_object_object_add(connection_json,"command",json_object_new_string(mensaje));
    strcpy(enviar,json_object_to_json_string(connection_json));
    enviar[strlen(enviar)]='\n';
    //Lee lo que envia el cliente
    valread = read( sock, buffer, 1024);
    connection_json = json_tokener_parse(buffer);
    char * message = (json_object_get_string(json_object_object_get(connection_json, "command")));
    puts(message);//Mensaje deserealizado
    puts(buffer);//Mensaje Original

    send(sock , enviar, strlen(enviar) , 0 );
    close(sock);
    return 0;


}
