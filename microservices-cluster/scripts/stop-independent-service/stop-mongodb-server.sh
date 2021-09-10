#!/usr/bin/bash

echo "Stopping MongoDB & MongoExpress";
cd ./../../services/mongodb/ && exec docker-compose down