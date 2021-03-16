package MemoryAllocationOS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Driver {
    private static int pno = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Memory> memoryArrayList = new ArrayList<>();

        System.out.println("Input the status of main memory:");


        for (int i = 0; true; i++) {
            System.out.println("Enter memory in KBs:\n(enter a -ve no. to stop input)");
            int mSize = sc.nextInt();
            if (mSize < 0) {
                break;
            }//if ends

            //dummy
            sc.nextLine();

            System.out.println("Enter status that is F (free) or NF (not free):");
            String st = sc.next();
            boolean free = false;
            if (st.equalsIgnoreCase("F")) {
                free = true;
            }//if ends
            String process = "";

            if (!free) {
                process = "P" + pno;
                pno++;
            }//if ends

            memoryArrayList.add(new Memory(i + 1, mSize, free, process));
        }//for ends

        boolean flag = true;

        do {
            System.out.println("Enter the size of the process that needs to be added in KBs:");
            int mSize = sc.nextInt();

            System.out.println("Enter the algorithm for memory allocation:\n1)First Fit\n2)Best Fit\n3)Worst Fit\n4)" +
                    "Exit");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    firstFit(memoryArrayList, mSize);
                    break;
                case 2:
                    bestFit(memoryArrayList, mSize);
                    break;
                case 3:
                    worstFit(memoryArrayList, mSize);
                    break;
                case 4:
                    flag = false;
                    break;
                default:
                    System.out.println("Wrong choice!");
            }//switch ends
            displayMemoryStatus(memoryArrayList);
        } while (flag);


    }

    private static void displayMemoryStatus(ArrayList<Memory> memoryArrayList) {
        for (int i = 0; i < memoryArrayList.size(); i++) {
            Memory memory = memoryArrayList.get(i);
            memory.setSrno(i + 1);

            System.out.println(memory.getSrno() + " " + memory.getMemorySize() + " " + (memory.isFree() ? "Free" :
                    "Not Free") + " " + memory.getProcess());
        }//for ends
    }//displayMemoryStatus ends

    private static void worstFit(ArrayList<Memory> memoryArrayList, int mSize) {
        int max = 0, maxIndex = 0;
        Memory rMemory = null;
        for (int i = 0; i < memoryArrayList.size(); i++) {
            Memory m = memoryArrayList.get(i);

            if (m.getMemorySize() > max && m.isFree() && m.getMemorySize() >= mSize) {
                max = m.getMemorySize();
                maxIndex = i;
            }//if ends
        }//for ends

        if (max - mSize > 0) {
            memoryArrayList.add(maxIndex, new Memory(maxIndex + 1, mSize, false, "P" + pno++));
            memoryArrayList.get(maxIndex + 1).setMemorySize(max - mSize);
        }//if ends
        else {
            memoryArrayList.get(maxIndex).setStatus(false);
            memoryArrayList.get(maxIndex).setProcess("P" + pno++);
        }//else ends
    }//worstFit ends

    private static void bestFit(ArrayList<Memory> memoryArrayList, int mSize) {
        int min = Collections.max(memoryArrayList, Comparator.comparingInt(Memory::getMemorySize)).getMemorySize();
        int minIndex = memoryArrayList.indexOf(Collections.max(memoryArrayList,
                Comparator.comparingInt(Memory::getMemorySize)));
        
        for (int i = 0; i < memoryArrayList.size(); i++) {
            Memory m = memoryArrayList.get(i);

            if (m.getMemorySize() < min && m.isFree() && m.getMemorySize() >= mSize) {
                min = m.getMemorySize();
                minIndex = i;
            }//inner if ends
        }//for ends


        if (min - mSize > 0) {
            memoryArrayList.add(minIndex, new Memory(minIndex + 1, mSize, false, "P" + pno++));
            memoryArrayList.get(minIndex + 1).setMemorySize(min - mSize);
        }//if ends
        else {
            memoryArrayList.get(minIndex).setStatus(false);
            memoryArrayList.get(minIndex).setProcess("P" + pno++);
        }

    }//bestFit function ends

    private static void firstFit(ArrayList<Memory> memoryArrayList, int mSize) {

        for (int i = 0; i < memoryArrayList.size(); i++) {
            int e = memoryArrayList.get(i).getMemorySize();
            boolean free = memoryArrayList.get(i).isFree();

            if (e >= mSize && free) {
                if (e - mSize > 0) {
                    memoryArrayList.add(i, new Memory(i + 1, mSize, false, "P" + pno++));
                    memoryArrayList.get(i + 1).setMemorySize(e - mSize);
                }//if ends
                else {
                    memoryArrayList.get(i).setStatus(false);
                    memoryArrayList.get(i).setProcess("P" + pno++);
                }//else ends
                break;
            }//outer if ends
        }//for ends

    }//firstFit function ends
}//class ends

