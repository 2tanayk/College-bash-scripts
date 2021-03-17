package Scheduling;

public class Process {
    private String process;
    private int arrivalTime;
    private int burstTime;

    public Process(String process, int burstTime, int arrivalTime) {
        this.process = process;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    @Override
    public String toString() {
        return "Process{" +
                "process='" + process + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", burstTime=" + burstTime +
                '}';
    }
}
