#!/usr/bin/bash

echo "Starting ELK Stack";
cd ./../../services/elk/ && exec docker-compose up --build -d