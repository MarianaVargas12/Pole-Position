cmake_minimum_required(VERSION 3.16)
project(Cpole C)

set(CMAKE_C_STANDARD 99)

add_executable(Cpole main.c sockets/Server.c sockets/Server.h json/cJson.h json/cJson.c json/json.h json/json.h json/arraylist.h
        json/bits.h json/debug.h json/json_c_version.h json/json_config.h json/json_inttypes.h json/json_object.h json/json_object_iterator.h
        json/json_object_private.h json/json_tokener.h json/json_util.h json/linkhash.h json/printbuf.h json/random_seed.h)

target_link_libraries(Cpole C json-c)