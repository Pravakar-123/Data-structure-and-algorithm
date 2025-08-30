import java.util.*;

class MedianFinder {
    private PriorityQueue<Integer> leftMaxHeap; // max heap
    private PriorityQueue<Integer> rightMinHeap; // min heap

    public MedianFinder() {
        leftMaxHeap = new PriorityQueue<>(Collections.reverseOrder()); // max-heap
        rightMinHeap = new PriorityQueue<>(); // min-heap
    }

    public void addNum(int num) {
        // Step 1: add to max heap (left side)
        leftMaxHeap.offer(num);

        // Step 2: balance → move top of left to right (to keep order property)
        rightMinHeap.offer(leftMaxHeap.poll());

        // Step 3: ensure size property (left can have +1 element)
        if (rightMinHeap.size() > leftMaxHeap.size()) {
            leftMaxHeap.offer(rightMinHeap.poll());
        }
    }

    public double findMedian() {
        if (leftMaxHeap.size() > rightMinHeap.size()) {
            return leftMaxHeap.peek(); // odd count
        } else {
            return (leftMaxHeap.peek() + rightMinHeap.peek()) / 2.0; // even count
        }
    }
}
