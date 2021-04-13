#include <stdio.h> 
#include <math.h> 
int binaryResult[40],input[32]; 

int decimalConvert(int start,int stop) 
{ 
int i; 
int res=0; 
int n = stop - start; 
int j = 0; 
for(i=start;i<stop;i++) 
{ 
double temp = pow(2,(n-j-1)); 
res=res+(input[i]*(int)temp); 
j++; 
} 
return res; 
} 

int binaryConvert(int n) 
{ 
int i=0; 
while((n/2)>=2) 
{ 
binaryResult[i]=n%2; 
n/=2; i++; 
} 
i++; 
binaryResult[i]=1; 
return i; 
} 

void displayBinRes(int n,int min_l) 
{ 
int i; 
if(min_l > (n+1)) 
{ 
for(i=0;i<(min_l-n-1);i++) 
{ 
printf("%d",0); 
} 
} 
for(i=n;i>=0;i--) 
{ 
printf("%d",binaryResult[i]); 
} 
}
 
int bitCalc(int processSize) 
{ 
int bits = 1; 
while((processSize / 2) >= 2) 
{ 
bits++; 
processSize = processSize/2; 
} 
return bits; 
}

void main() 
{ 
int processSize, pageSize, pMemorySize,frameNo,pageNo; 
int pageTable[5][3]; 
int i; 
int pTableBits,lBits,pb,lb; 
int pageBits; 
printf("Please enter the Process Size in KB: "); 
scanf("%d",&processSize); 
printf("Please enter Page Size in bytes: "); 
scanf("%d",&pageSize); 
pageBits = bitCalc(pageSize*1024); 
printf("Please enter Physical Memory Size in MB: "); 
scanf("%d",&pMemorySize); 
for(i=0;i<5;i++) 
{ 
printf("Please enter data for Page Table entry %d\n",(i+1)); 
printf("Page No: "); 
scanf("%d",&pageTable[i][0]); 
printf("Frame No: "); 
scanf("%d",&pageTable[i][1]); 
printf("Valid/Invalid Bit: "); 
scanf("%d",&pageTable[i][2]); 
} 
frameNo=pMemorySize/pageSize; 
printf("The Total number of Frames in the Physical Memory are: %d\n",frameNo); 
pageNo=processSize/pageSize; 
printf("The Total number of entries in the Page Table are: %d\n",pageNo); 
pTableBits = bitCalc(pMemorySize); 
printf("The number of bits in the Physical Address are: %d\n",(pTableBits+pageBits)); 
printf("The distribution is %d:%d\n",pTableBits,pageBits); 
pb=pTableBits+pageBits; 
lBits = bitCalc(processSize); 
printf("The number of bits in the Logical Address are: %d\n",(lBits+pageBits)); 
printf("The distribution is %d:%d\n",lBits,pageBits); 
lb=lBits+pageBits; 
while(1) 
{ 
printf("Please enter the logical address:\n"); 
for(i = 0; i < lb; i++) { 
int temp; 
scanf("%d",&temp); 
if(temp == 2) 
{ 
i = -1; 
break; 
} 
input[i] = temp; 
} 
if(i == -1) 
break; 
printf("\n"); 
int page_no = decimalConvert(0,lBits); 
int pos = -1; 
for(i=0;i<5;i++) 
{ 
if(page_no == pageTable[i][0]) 
pos = i; 
} 
if(pos != -1) 
{ 
if(pageTable[pos][2] == 1) 
{ 
printf("The generated Physical Address is: "); 
int p_addr = pageTable[pos][1]; 
p_addr = p_addr * pageSize * 1024; 
p_addr += decimalConvert(lBits,lb); 
int start = binaryConvert(p_addr); 
displayBinRes(start,pb); 
printf("\n"); printf("Page hit!\n"); 
} 
else 
printf("Page Fault!\n"); 
} 
else 
printf("Page not found!\n"); 
i = 0; 
} 
} 
