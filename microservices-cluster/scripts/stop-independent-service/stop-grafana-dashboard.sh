#!/usr/bin/bash

echo "Stopping Grafana Dashboard";
path='./../../services/'
cd "$path"grafana/ && exec docker-compose down