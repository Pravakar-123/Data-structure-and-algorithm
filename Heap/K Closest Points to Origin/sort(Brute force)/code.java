import java.util.*;

class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // Sort points by distance from origin
        Arrays.sort(points, (a, b) -> distance(a) - distance(b));

        // Take first k points
        return Arrays.copyOfRange(points, 0, k);
    }

    private int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1]; // squared distance
    }
}
