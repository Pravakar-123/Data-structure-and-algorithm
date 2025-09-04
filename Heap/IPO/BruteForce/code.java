
class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        boolean[] vis = new boolean[n];  // visited array

        while (k > 0) {
            int index = -1;
            int maxProfit = Integer.MIN_VALUE;

            // Step 1: Find the best feasible project
            for (int i = 0; i < n; i++) {
                if (!vis[i] && capital[i] <= w && profits[i] > maxProfit) {
                    index = i;
                    maxProfit = profits[i];
                }
            }

            // Step 2: If no feasible project found, return current capital
            if (index == -1) {
                return w;
            }

            // Step 3: Mark project as taken
            vis[index] = true;

            // Step 4: Increase capital by chosen profit
            w += profits[index];

            // Step 5: One project is done
            k--;
        }

        return w;
    }
}
