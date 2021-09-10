#!/usr/bin/bash

echo "Starting MySQL Server & Adminer";
cd ./../../services/mysql/ && exec docker-compose up --build  -d