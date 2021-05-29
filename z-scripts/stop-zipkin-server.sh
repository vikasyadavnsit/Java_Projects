#!/usr/bin/bash

echo "Stopping Zipkin Server";
cd ./../zipkin/ && exec docker compose down 