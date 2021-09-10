#!/usr/bin/bash

echo "Starting Kafka Zookeeper & Kafka Broker";
cd ./../..services/kafka/ && exec docker-compose up --build -d