#!/bin/bash
read -p "Enter a no.:" n
if [ $((n%2)) -eq 0 ]
then
	echo "The no. is even"
else
	echo "The no. is odd"
fi
