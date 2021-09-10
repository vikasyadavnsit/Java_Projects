#!/usr/bin/bash

searchDirectories=(landing-microservice)

echo ${#searchDirectories[@]} " Projects found";

for project_name in "${searchDirectories[@]}"
do 
    project_path="./../../applications/${project_name}";
    echo -e "\nBuilding Jar at ${project_path}";
    (cd $project_path && exec ./gradlew build -x test) | grep 'BUILD FAILED' 
    echo "Build Finished at ${project_path}";
done 

