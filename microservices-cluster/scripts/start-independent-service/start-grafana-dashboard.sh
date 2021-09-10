#!/usr/bin/bash

echo "Starting Grafana Dashboard";
cd ./../../services/grafana/ && exec docker-compose up --build -d