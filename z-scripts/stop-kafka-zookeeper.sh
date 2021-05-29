#!/usr/bin/bash

echo "Stopping Kafka Zookeeper & Kafka Broker";
cd ./../kafka/ && exec docker compose down 