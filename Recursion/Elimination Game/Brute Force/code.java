import java.util.*;
class Solution {
    public int lastRemaining(int n) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            arr.add(i);
        }
        boolean flag = true;
        while (arr.size() > 1) {
            arr = remove(arr, flag);
            flag = !flag;
        }
        return arr.get(0);
    }

    private ArrayList<Integer> remove(ArrayList<Integer> arr, boolean flag) {
        ArrayList<Integer> ans = new ArrayList<>();
        int n = arr.size();
        if (flag) {
            for (int i = 1; i < n; i = i + 2) {
                ans.add(arr.get(i));
            }
        } else {
            for (int i = n - 2; i >= 0; i = i - 2) {
                ans.add(0,arr.get(i));
            }
        }
        return ans;
    }
}