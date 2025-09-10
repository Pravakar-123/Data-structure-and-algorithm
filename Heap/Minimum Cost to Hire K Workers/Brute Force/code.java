import java.util.ArrayList;
import java.util.List;

class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        double minCost = Double.MAX_VALUE;

        // Generate combinations of size k
        List<int[]> combos = new ArrayList<>();
        generateCombinations(n, k, 0, new ArrayList<>(), combos);

        for (int[] combo : combos) {
            double ratio = 0.0;
            int totalQuality = 0;

            // Calculate max ratio and total quality
            for (int idx : combo) {
                ratio = Math.max(ratio, (double) wage[idx] / quality[idx]);
                totalQuality += quality[idx];
            }

            // Cost for this group
            double cost = ratio * totalQuality;
            minCost = Math.min(minCost, cost);
        }

        return minCost;
    }

    // Helper to generate combinations
    private void generateCombinations(int n, int k, int start, List<Integer> current, List<int[]> result) {
        if (current.size() == k) {
            result.add(current.stream().mapToInt(i -> i).toArray());
            return;
        }

        for (int i = start; i < n; i++) {
            current.add(i);
            generateCombinations(n, k, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

   
}