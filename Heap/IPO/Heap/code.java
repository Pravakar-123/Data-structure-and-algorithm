import java.util.PriorityQueue;
//Heap brute force
class Solution1 {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;

        // Min-heap based on capital, if equal capital then higher profit first
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0])
        );

        // Max-heap based on profit
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);

        // Step 1: Push all projects into minHeap (capital, profit)
        for (int i = 0; i < n; i++) {
            minHeap.offer(new int[]{capital[i], profits[i]});
        }

        // Do at most k projects
        while (k > 0) {
            // Step 2: Move feasible projects (capital <= w) into maxHeap
            while (!minHeap.isEmpty() && minHeap.peek()[0] <= w) {
                maxHeap.offer(minHeap.poll());
            }

            // If no feasible project, break
            if (maxHeap.isEmpty()) break;

            // Step 3: Pick the project with max profit
            int[] best = maxHeap.poll();
            w += best[1];
            k--;

            // Step 4: Move all remaining projects in maxHeap back into minHeap
            while (!maxHeap.isEmpty()) {
                minHeap.offer(maxHeap.poll());
            }
        }

        return w;
    }
}

//Heap optimization
class Solution2 {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;

        // Min-heap by capital (store index)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> capital[a] - capital[b]);

        // Max-heap by profit (store index)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> profits[b] - profits[a]);

        // Step 1: push all indices into minHeap
        for (int i = 0; i < n; i++) {
            minHeap.offer(i);
        }

        // Step 2: do at most k projects
        while (k > 0) {
            // Move feasible projects into maxHeap
            while (!minHeap.isEmpty() && capital[minHeap.peek()] <= w) {
                maxHeap.offer(minHeap.poll());
            }

            // If no feasible project, stop
            if (maxHeap.isEmpty()) break;

            // Take best profit
            w += profits[maxHeap.poll()];
            k--;
        }

        return w;
    }
}