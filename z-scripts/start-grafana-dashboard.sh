#!/usr/bin/bash

echo "Starting Grafana Dashboard";
cd ./../grafana/ && exec docker-compose up --build -d