#!/usr/bin/bash

echo "Starting Prometheus Server";
cd ./../prometheus/ && exec docker-compose up --build -d