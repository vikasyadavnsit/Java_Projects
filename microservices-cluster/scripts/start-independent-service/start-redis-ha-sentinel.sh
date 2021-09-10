#!/usr/bin/bash

echo "Starting Redis Master, Slave and 3 Sentinel Nodes";
cd ./../../services/redis/ && exec docker-compose up --build -d