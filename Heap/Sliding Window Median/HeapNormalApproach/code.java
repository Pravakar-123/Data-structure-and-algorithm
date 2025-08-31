import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    public double[] medianSlidingWindow(int[] nums, int k) {

        double[] median = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
           
            if (i + 1 >= k) {
                median[i - k + 1] = findMedian();
                remove(nums[i - k + 1]);
            }
        }
        return median;
    }

    private double findMedian() {
        return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek() / 2.0 + maxHeap.peek() / 2.0;
    }

    private void add(int num) {
        if (maxHeap.size() == 0 || maxHeap.peek() >= num)
            maxHeap.add(num);
        else
            minHeap.add(num);
        rebalanceHeaps();
    }

    private void remove(int num) {
        if (num > maxHeap.peek())
            minHeap.remove(num);
        else
            maxHeap.remove(num);
        rebalanceHeaps();
    }

    private void rebalanceHeaps() {
        if (maxHeap.size() == minHeap.size())
            return;
        if (maxHeap.size() > minHeap.size() + 1)
            minHeap.add(maxHeap.poll());
        else if (maxHeap.size() < minHeap.size())
            maxHeap.add(minHeap.poll());
    }
}