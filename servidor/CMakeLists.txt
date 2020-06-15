cmake_minimum_required(VERSION 3.16)
project(servidor C)

set(CMAKE_C_STANDARD 99)

add_executable(servidor main.c player.h car.c car.h player.c game.c game.h object.c object.h constant.c constant.h)