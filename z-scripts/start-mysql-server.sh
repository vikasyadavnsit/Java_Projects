#!/usr/bin/bash

echo "Starting MySQL Server & Adminer";
cd ./../mysql/ && exec docker-compose up --build  -d