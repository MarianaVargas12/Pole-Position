# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.16

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /cygdrive/c/Users/wajib/AppData/Local/JetBrains/CLion2020.1/cygwin_cmake/bin/cmake.exe

# The command to remove a file.
RM = /cygdrive/c/Users/wajib/AppData/Local/JetBrains/CLion2020.1/cygwin_cmake/bin/cmake.exe -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor"

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/cmake-build-debug"

# Include any dependencies generated for this target.
include CMakeFiles/servidor.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/servidor.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/servidor.dir/flags.make

CMakeFiles/servidor.dir/main.c.o: CMakeFiles/servidor.dir/flags.make
CMakeFiles/servidor.dir/main.c.o: ../main.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/cmake-build-debug/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/servidor.dir/main.c.o"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/servidor.dir/main.c.o   -c "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/main.c"

CMakeFiles/servidor.dir/main.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/servidor.dir/main.c.i"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/main.c" > CMakeFiles/servidor.dir/main.c.i

CMakeFiles/servidor.dir/main.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/servidor.dir/main.c.s"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/main.c" -o CMakeFiles/servidor.dir/main.c.s

CMakeFiles/servidor.dir/car.c.o: CMakeFiles/servidor.dir/flags.make
CMakeFiles/servidor.dir/car.c.o: ../car.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/cmake-build-debug/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_2) "Building C object CMakeFiles/servidor.dir/car.c.o"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/servidor.dir/car.c.o   -c "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/car.c"

CMakeFiles/servidor.dir/car.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/servidor.dir/car.c.i"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/car.c" > CMakeFiles/servidor.dir/car.c.i

CMakeFiles/servidor.dir/car.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/servidor.dir/car.c.s"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/car.c" -o CMakeFiles/servidor.dir/car.c.s

CMakeFiles/servidor.dir/player.c.o: CMakeFiles/servidor.dir/flags.make
CMakeFiles/servidor.dir/player.c.o: ../player.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/cmake-build-debug/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_3) "Building C object CMakeFiles/servidor.dir/player.c.o"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/servidor.dir/player.c.o   -c "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/player.c"

CMakeFiles/servidor.dir/player.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/servidor.dir/player.c.i"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/player.c" > CMakeFiles/servidor.dir/player.c.i

CMakeFiles/servidor.dir/player.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/servidor.dir/player.c.s"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/player.c" -o CMakeFiles/servidor.dir/player.c.s

CMakeFiles/servidor.dir/game.c.o: CMakeFiles/servidor.dir/flags.make
CMakeFiles/servidor.dir/game.c.o: ../game.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/cmake-build-debug/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_4) "Building C object CMakeFiles/servidor.dir/game.c.o"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/servidor.dir/game.c.o   -c "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/game.c"

CMakeFiles/servidor.dir/game.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/servidor.dir/game.c.i"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/game.c" > CMakeFiles/servidor.dir/game.c.i

CMakeFiles/servidor.dir/game.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/servidor.dir/game.c.s"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/game.c" -o CMakeFiles/servidor.dir/game.c.s

CMakeFiles/servidor.dir/object.c.o: CMakeFiles/servidor.dir/flags.make
CMakeFiles/servidor.dir/object.c.o: ../object.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/cmake-build-debug/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_5) "Building C object CMakeFiles/servidor.dir/object.c.o"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/servidor.dir/object.c.o   -c "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/object.c"

CMakeFiles/servidor.dir/object.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/servidor.dir/object.c.i"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/object.c" > CMakeFiles/servidor.dir/object.c.i

CMakeFiles/servidor.dir/object.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/servidor.dir/object.c.s"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/object.c" -o CMakeFiles/servidor.dir/object.c.s

CMakeFiles/servidor.dir/constant.c.o: CMakeFiles/servidor.dir/flags.make
CMakeFiles/servidor.dir/constant.c.o: ../constant.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/cmake-build-debug/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_6) "Building C object CMakeFiles/servidor.dir/constant.c.o"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/servidor.dir/constant.c.o   -c "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/constant.c"

CMakeFiles/servidor.dir/constant.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/servidor.dir/constant.c.i"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/constant.c" > CMakeFiles/servidor.dir/constant.c.i

CMakeFiles/servidor.dir/constant.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/servidor.dir/constant.c.s"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/constant.c" -o CMakeFiles/servidor.dir/constant.c.s

# Object files for target servidor
servidor_OBJECTS = \
"CMakeFiles/servidor.dir/main.c.o" \
"CMakeFiles/servidor.dir/car.c.o" \
"CMakeFiles/servidor.dir/player.c.o" \
"CMakeFiles/servidor.dir/game.c.o" \
"CMakeFiles/servidor.dir/object.c.o" \
"CMakeFiles/servidor.dir/constant.c.o"

# External object files for target servidor
servidor_EXTERNAL_OBJECTS =

servidor.exe: CMakeFiles/servidor.dir/main.c.o
servidor.exe: CMakeFiles/servidor.dir/car.c.o
servidor.exe: CMakeFiles/servidor.dir/player.c.o
servidor.exe: CMakeFiles/servidor.dir/game.c.o
servidor.exe: CMakeFiles/servidor.dir/object.c.o
servidor.exe: CMakeFiles/servidor.dir/constant.c.o
servidor.exe: CMakeFiles/servidor.dir/build.make
servidor.exe: CMakeFiles/servidor.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir="/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/cmake-build-debug/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_7) "Linking C executable servidor.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/servidor.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/servidor.dir/build: servidor.exe

.PHONY : CMakeFiles/servidor.dir/build

CMakeFiles/servidor.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/servidor.dir/cmake_clean.cmake
.PHONY : CMakeFiles/servidor.dir/clean

CMakeFiles/servidor.dir/depend:
	cd "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/cmake-build-debug" && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor" "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor" "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/cmake-build-debug" "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/cmake-build-debug" "/cygdrive/c/Users/wajib/OneDrive/Documentos/TEC/2020 I Semestre/Lenguajes/Cpole/git/Pole-Position/servidor/cmake-build-debug/CMakeFiles/servidor.dir/DependInfo.cmake" --color=$(COLOR)
.PHONY : CMakeFiles/servidor.dir/depend

