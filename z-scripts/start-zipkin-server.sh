#!/usr/bin/bash

echo "Starting Zipkin Server";
cd ./../zipkin/ && exec docker compose up --build  -d