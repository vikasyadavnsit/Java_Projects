#!/usr/bin/bash

echo "Starting Prometheus Server";
cd ./../../services/prometheus/ && exec docker-compose up --build -d