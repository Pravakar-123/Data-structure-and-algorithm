import java.util.*;

class Solution {

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] ans = new double[n - k + 1];
        DualHeap dh = new DualHeap(k);

        // build first window
        for (int i = 0; i < k; i++) dh.insert(nums[i]);
        ans[0] = dh.getMedian();

        // slide the window
        for (int i = k; i < n; i++) {
            dh.erase(nums[i - k]);   // remove outgoing
            dh.insert(nums[i]);      // add incoming
            ans[i - k + 1] = dh.getMedian();
        }
        return ans;
    }

    // Maintains:
    // - small: max-heap for lower half
    // - large: min-heap for upper half
    // - delayed: counts for lazy deletions
    // - smallSize/largeSize: sizes excluding delayed elements
    private static class DualHeap {
        PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> large = new PriorityQueue<>();
        Map<Integer, Integer> delayed = new HashMap<>();
        int smallSize = 0, largeSize = 0;
        final int k;

        DualHeap(int k) { this.k = k; }

        void insert(int num) {
            if (small.isEmpty() || num <= small.peek()) {
                small.offer(num);
                smallSize++;
            } else {
                large.offer(num);
                largeSize++;
            }
            makeBalance();
        }

        void erase(int num) {
            // mark for lazy deletion
            delayed.put(num, delayed.getOrDefault(num, 0) + 1);

            // decide which heap "owns" num using current small top
            if (!small.isEmpty() && num <= small.peek()) {
                smallSize--;
                // if num equals current top, prune immediately
                if (!small.isEmpty() && small.peek() == num) prune(small);
            } else {
                largeSize--;
                if (!large.isEmpty() && large.peek() == num) prune(large);
            }
            makeBalance();
        }

        double getMedian() {
            // ensure tops are clean before peeking
            prune(small);
            prune(large);
            if ((k & 1) == 1) {
                return (double) small.peek();
            } else {
                return ((long) small.peek() + (long) large.peek()) / 2.0;
            }
        }

        // Remove elements from heap top that are marked delayed
        void prune(PriorityQueue<Integer> heap) {
            while (!heap.isEmpty()) {
                int x = heap.peek();
                Integer cnt = delayed.get(x);
                if (cnt == null || cnt == 0) break;
                heap.poll();
                if (cnt == 1) delayed.remove(x);
                else delayed.put(x, cnt - 1);
            }
        }

        // Keep |small| == |large| or |small| == |large| + 1
        void makeBalance() {
            if (smallSize > largeSize + 1) {
                // move top of small -> large
                large.offer(small.poll());
                smallSize--;
                largeSize++;
                prune(small);
            } else if (smallSize < largeSize) {
                // move top of large -> small
                small.offer(large.poll());
                smallSize++;
                largeSize--;
                prune(large);
            }
        }
    }
}
