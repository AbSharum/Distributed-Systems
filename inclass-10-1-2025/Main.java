import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        int totalSum = 0;
        int numThreads = 0;
        File directory = null;
        ArrayList<File> files = new ArrayList<File>();
        
        if (args.length == 2) {
            directory = new File(args[0]);
            numThreads = Integer.parseInt(args[1]);
        } else {
            System.out.println("Invalid argument(s)");
            System.exit(0);
        }

        getFiles(files, directory);
        
        Thread[] threads = new Thread[numThreads];
        Week7[] runners = new Week7[numThreads];

        int range = files.size() / numThreads;
        int leftOver = files.size() > numThreads ? files.size() % numThreads : 0;
        int threadIndex = 0;
        int startIndex = 0;



        if (numThreads <= files.size()) {

            while (threadIndex < numThreads) {
                // First k threads get an extra element until the number of leftover elements is reached
                int extendRange = threadIndex < leftOver ? 1 : 0;
                int endIndex = startIndex + range + extendRange - 1;
                runners[threadIndex] = new Week7(startIndex, endIndex, files,threadIndex+1);
                threads[threadIndex] = new Thread(runners[threadIndex]);
                threads[threadIndex++].start();
                startIndex = endIndex + 1;
            }
        } else {
            for (int i = 0; i < numThreads; i++) {
                runners[i] = new Week7(i, i, files,i+1);
                threads[i] = new Thread(runners[i]);
                threads[i].start();
            }
        }

        try {
            
            for (Thread t : threads) {
                t.join();
            }

            for (int i = 0; i < runners.length; i++) {
                System.out.println(runners[i]);
                totalSum += runners[i].getSum();
            }

            System.out.printf("Total Sum: %d",totalSum);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void getFiles(ArrayList<File> files, File dir) {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                getFiles(files, file);
            } else {
                files.add(file);
            }
        }
    }
}

class Week7 implements Runnable {

    private int localSum = 0;
    private ArrayList<File> files;
    private int startIndex;
    private int endIndex;
    private int threadNum;
    
    public Week7(int startIndex, int endIndex, ArrayList<File> files, int threadNum) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.files = files;
        this.threadNum = threadNum;
    }

    public int getSum() {
        return this.localSum;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public void run () {
        File f;
        String line;
        BufferedReader br;
        if (endIndex >= files.size()) return; 
        try {
            for(int i = startIndex; i <= endIndex; i++) {
                f = files.get(i);
                br = new BufferedReader(new FileReader(f));
                while((line = br.readLine()) != null) {
                    localSum += Integer.parseInt(line);
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String s = "";
        s += String.format("Thread %d: has files\n[",threadNum);
        endIndex = endIndex >= files.size() ? endIndex - 1 : endIndex;
        for (int i = startIndex; i <= endIndex; i++) {
            if (i < endIndex) {
                s += String.format("%s,",files.get(i).getName());
            } else {
                s += String.format("%s]\n",files.get(i).getName());
            }
        }
        s += String.format("Thread %d local sum: %d\n",threadNum,localSum);

        return s;
    }
}