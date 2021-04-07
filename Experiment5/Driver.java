package PageReplacementPolicies;

import java.sql.Array;
import java.util.*;

public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        do {
            System.out.println("Enter a no:\n1)FIFO\n2)LRU\n3)Exit");
            int ch = sc.nextInt();
            if (ch == 3) break;
            System.out.println("Enter no. of frames:");
            int frames = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter page no. reference string:");
            String ref = sc.nextLine();
            String[] refArr = ref.split(",");

            switch (ch) {
                case 1:
                    fifoReplacement(frames, refArr);
                    break;
                case 2:
                    lruReplacement(frames, refArr);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }//switch ends
        } while (flag);
    }//main ends

    private static void fifoReplacement(int frames, String[] refArr) {
        String[] frameArr = new String[frames];
        int miss = 0;
        int hit = 0;
        int oldestPos = 0;
        for (int i = 0, j = 0; i < refArr.length; i++, j++) {
            if (j < frameArr.length && frameArr[j] == null) {
                frameArr[j] = refArr[i];
                miss++;
            } else if (!Arrays.asList(frameArr).contains(refArr[i])) {
                frameArr[oldestPos] = refArr[i];
                oldestPos = (oldestPos + 1) % frames;
                miss++;
            } else {
                hit++;
            }
            //null represents an empty page frame
            System.out.println("Frame Status:" + Arrays.toString(frameArr));
        }//for ends
        System.out.println("hit to miss ratio:" + (hit / miss));
    }//fifoReplacement ends

    private static void lruReplacement(int frames, String[] refArr) {
        String[] frameArr = new String[frames];
        int miss = 0;
        int hit = 0;
        Deque<Integer> recentQueue = new ArrayDeque<>();

        for (int i = 0; i < refArr.length; i++) {
            if (i < frameArr.length && frameArr[i] == null) {
                frameArr[i] = refArr[i];
                recentQueue.addLast(i);
                miss++;
            } else if (!Arrays.asList(frameArr).contains(refArr[i])) {
                int index = recentQueue.pollFirst();
                frameArr[index] = refArr[i];
                recentQueue.addLast(index);
                miss++;
            } else {
                int index = Arrays.asList(frameArr).indexOf(refArr[i]);
                recentQueue.addLast(index);
                recentQueue.remove(index);
                hit++;
            }
            //null represents an empty page frame
            System.out.println("Frame Status:" + Arrays.toString(frameArr));
        }//for ends
        System.out.println("hit to miss ratio:" + (hit / (miss*1.0)));
    }//lruReplacement ends

}//Driver ends
