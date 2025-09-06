import java.util.*;

class BruteForceSolution {
    public int minSetSize(int[] arr) {
        int n = arr.length;
        int half = n / 2;

        // Count frequencies
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        List<Integer> counts = new ArrayList<>(freq.values());
        int m = counts.size();
        int ans = m;

        // Try all subsets
        for (int mask = 1; mask < (1 << m); mask++) {
            int removed = 0, size = 0;
            for (int i = 0; i < m; i++) {
                if ((mask & (1 << i)) != 0) {
                    removed += counts.get(i);
                    size++;
                }
            }
            if (removed >= half) ans = Math.min(ans, size);
        }
        return ans;
    }
}