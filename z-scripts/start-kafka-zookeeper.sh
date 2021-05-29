#!/usr/bin/bash

echo "Starting Kafka Zookeeper & Kafka Broker";
cd ./../kafka/ && exec docker compose up --build -d 