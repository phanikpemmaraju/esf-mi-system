#!/bin/bash
. import_data.properties
/usr/bin/mongoimport --host $db_host --port $db_port --username=$db_username --password=$db_password --db $db_name --collection $db_collection --drop --headerline --ignoreBlanks --type $file_type --file $file_path
