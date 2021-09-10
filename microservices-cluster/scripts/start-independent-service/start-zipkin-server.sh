#!/usr/bin/bash

echo "Starting Zipkin Server";
cd ./../../services/zipkin/ && exec docker-compose up --build  -d