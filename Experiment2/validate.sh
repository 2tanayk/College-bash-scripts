#!/bin/bash
username="tanay"
password=12345
echo "Enter username and password:"
read uname
read pword

validate () {
	if [ $1 == "tanay" ] && [ $2 == 12345 ]
	then 
		echo "Welcome!"
	else
		echo "Either username or password is incorrect"
	fi
}

validate $uname $pword

