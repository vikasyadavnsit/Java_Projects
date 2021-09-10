#!/usr/bin/bash

searchDirectories=(api-gateway config-server discovery-server microservice-1 microservice-2 microservice-3)

echo ${#searchDirectories[@]} " Projects found";

for project_name in "${searchDirectories[@]}"
do 
    project_path="./../${project_name}";
    echo -e "\nBuilding Jar at ${project_path}";
    (cd $project_path && exec ./gradlew build -x test) | grep 'BUILD FAILED' 
    echo "Build Finished at ${project_path}";
done 

