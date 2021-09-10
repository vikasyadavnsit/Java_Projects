#!/usr/bin/bash

echo "Stopping Zipkin Server";
cd ./../../services/zipkin/ && exec docker-compose down