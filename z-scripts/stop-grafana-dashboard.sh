#!/usr/bin/bash

echo "Stopping Grafana Dashboard";
cd ./../grafana/ && exec docker-compose down 