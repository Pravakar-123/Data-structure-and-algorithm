import java.util.*;

class SortingSolution {
    public int minSetSize(int[] arr) {
        int n = arr.length;
        int half = n / 2;

        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        List<Integer> counts = new ArrayList<>(freq.values());
        counts.sort(Collections.reverseOrder());

        int removed = 0, ans = 0;
        for (int count : counts) {
            removed += count;
            ans++;
            if (removed >= half) break;
        }
        return ans;
    }
}
