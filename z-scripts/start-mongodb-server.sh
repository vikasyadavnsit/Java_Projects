#!/usr/bin/bash

echo "Starting MongoDB & MongoExpress";
cd ./../mongodb/ && exec docker-compose up --build -d