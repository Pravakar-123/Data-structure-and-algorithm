import java.util.*;
class HeapSolution {
    public int minSetSize(int[] arr) {
        int n = arr.length;
        int half = n / 2;

        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(freq.values());

        int removed = 0, ans = 0;
        while (removed < half) {
            removed += maxHeap.poll();
            ans++;
        }
        return ans;
    }
}