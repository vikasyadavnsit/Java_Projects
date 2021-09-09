#!/usr/bin/bash

echo "Stopping MySQL Server & Adminer";
cd ./../mysql/ && exec docker-compose down 