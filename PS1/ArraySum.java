/********************************
Name: Abraham Sharum
Problem Set: PS1
Due Date: October 3, 2025
********************************/

class ArraySum implements Runnable {
    private int localSum = 0;
    private int[] nums;
    private int startIndex;
    private int endIndex;
    private int threadNum;
    
    public ArraySum(int startIndex, int endIndex, int[] nums, int threadNum) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.nums = nums;
        this.threadNum = threadNum + 1;
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
        if (endIndex >= nums.length) return; 
        for (int i = startIndex; i <= endIndex; i++) localSum += nums[i];
    }

    public String toString() {
        if (endIndex >= nums.length) return String.format("Thread <%d>: Invalid index range of range <%d> and <%d>.\n",this.threadNum,this.startIndex,this.endIndex);
        return String.format("Thread <%d>: Partial sum for range <%d>/<%d> is %d.\n",this.threadNum,this.startIndex,this.endIndex,this.localSum);
    }
}