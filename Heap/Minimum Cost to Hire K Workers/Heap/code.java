import java.util.*;
class Solution{
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        double[][] workers = new double[n][2]; // [ratio, quality]

        // Step 1: compute ratio and store
        for (int i = 0; i < n; i++) {
            workers[i][0] = (double) wage[i] / quality[i];
            workers[i][1] = quality[i];
        }

        // Step 2: sort by ratio
        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));

        // Step 3: use max heap for qualities
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        double res = Double.MAX_VALUE;
        int qualitySum = 0;

        for (double[] w : workers) {
            int q = (int) w[1];
            qualitySum += q;
            maxHeap.offer(q);


            if (maxHeap.size() == k) {
                res = Math.min(res, w[0] * qualitySum);
                qualitySum -= maxHeap.poll();
            }
        }

        return res;
    }

}
