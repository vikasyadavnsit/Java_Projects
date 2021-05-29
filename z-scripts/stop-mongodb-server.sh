#!/usr/bin/bash

echo "Stopping MongoDB & MongoExpress";
cd ./../mongodb/ && exec docker compose down 