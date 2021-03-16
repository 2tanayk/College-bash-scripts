package MemoryAllocationOS;

public class Memory {
    private int srno;
    private int memorySize;
    private boolean free;
    private String process;

    public Memory(int srno, int memorySize, boolean free, String process) {
        this.srno = srno;
        this.memorySize = memorySize;
        this.free = free;
        this.process = process;
    }

    public int getSrno() {
        return srno;
    }

    public void setSrno(int srno) {
        this.srno = srno;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public boolean isFree() {
        return free;
    }

    public void setStatus(boolean free) {
        this.free = free;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "srno=" + srno +
                ", memorySize=" + memorySize +
                ", free=" + free +
                ", process='" + process + '\'' +
                '}';
    }
}
