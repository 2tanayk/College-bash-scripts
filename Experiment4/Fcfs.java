package Scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

//       Process	Burst time	Arrival time
//        P1	6	2
//        P2	3	5
//        P3	8	1
//        P4	3	0
//        P5	4	4
public class Fcfs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Process> processes = new ArrayList<>();

        System.out.print("Enter total no. of processes:");
        int n = sc.nextInt();

        System.out.println("Enter burst time and arrival for each process:");

        for (int i = 0; i < n; i++) {
            processes.add(new Process("P" + (i + 1), sc.nextInt(), sc.nextInt()));
        }

        processes.sort(Comparator.comparingInt(Process::getArrivalTime));

        System.out.println(processes);

        int tWaitTime = 0;
        int tTime = processes.get(0).getBurstTime();

        int tTurnaround = processes.get(0).getBurstTime() - processes.get(0).getArrivalTime();
        for (int i = 1; i < n; i++) {
            int eWaitTime = tTime - processes.get(i).getArrivalTime();
            System.out.print("Waiting time for " + processes.get(i).getProcess() + ":" + eWaitTime + " ");
            tWaitTime += eWaitTime;
            tTime += processes.get(i).getBurstTime();
            int turnaround = tTime - processes.get(i).getArrivalTime();
            System.out.println("Turnaround time for " + processes.get(i).getProcess() + ":" + turnaround);
            tTurnaround += turnaround;
        }

        System.out.println("Average waiting time:" + tWaitTime / (n * 1.0));
        System.out.println("Average turnaround time:" + tTurnaround / (n * 1.0));


    }
}
