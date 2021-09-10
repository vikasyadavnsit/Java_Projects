#!/usr/bin/bash

echo "Stopping Prometheus Server";
cd ./../..services/prometheus/ && exec docker-compose down