#!/usr/bin/bash

echo "Stopping ELK Stack";
path='./../../services/'
cd "$path"elk/ && exec docker-compose down