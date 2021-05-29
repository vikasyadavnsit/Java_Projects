#!/usr/bin/bash

for shell_file in $(find . -type f -iname "start-*")
do
    echo -e "\nExecuting $shell_file";
    sh $shell_file;
    echo "Execution finished for $shell_file";
done    

echo -e "\nExecution done for all shell files";
