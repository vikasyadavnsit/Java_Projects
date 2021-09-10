#!/usr/bin/bash

echo "Stopping Redis Master, Slave and 3 Sentinel Nodes";
cd ./../../services/redis/ && exec docker-compose down