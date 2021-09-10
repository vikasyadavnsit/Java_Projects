#!/usr/bin/bash

echo "Starting MongoDB & MongoExpress";
cd ./../..services/mongodb/ && exec docker-compose up --build -d