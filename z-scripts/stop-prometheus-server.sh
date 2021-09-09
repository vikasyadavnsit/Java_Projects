#!/usr/bin/bash

echo "Stopping Prometheus Server";
cd ./../prometheus/ && exec docker-compose down 