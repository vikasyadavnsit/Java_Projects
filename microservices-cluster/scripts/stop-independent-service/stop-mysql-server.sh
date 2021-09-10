#!/usr/bin/bash

echo "Stopping MySQL Server & Adminer";
cd ./../../services/mysql/ && exec docker-compose down