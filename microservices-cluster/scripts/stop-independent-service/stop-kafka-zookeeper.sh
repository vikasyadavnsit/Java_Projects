#!/usr/bin/bash

echo "Stopping Kafka Zookeeper & Kafka Broker";
cd ./../../services/kafka/ && exec docker-compose down