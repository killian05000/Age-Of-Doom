cmake_minimum_required(VERSION 3.0)

project(ProjetJava)

find_package(Java REQUIRED)
include(UseJava)

set(SRC src/Application.java src/CompareFileVisitor.java src/FileComparator.java src/FindDuplicateFiles.java src/IndexVisitor.java)
set(JAR lib/commons-codec-1.11.jar)

add_jar(app SOURCES ${SRC} INCLUDE_JARS ${JAR} ENTRY_POINT Application)
