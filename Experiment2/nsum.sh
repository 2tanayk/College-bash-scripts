#!/bin/bash
read -p "Enter the no. of numbers to be added:" n
s=0
for((i=1;i<=n;i++))
do
	read e
	s=$((s+e))
done

echo "The sum of n nos. is $s"
