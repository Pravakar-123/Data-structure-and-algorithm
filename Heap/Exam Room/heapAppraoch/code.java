import java.util.*;

class ExamRoom {
    private int N;
    private PriorityQueue<int[]> pq;   // max-heap of intervals
    private Map<Integer, int[]> startMap; // start -> interval
    private Map<Integer, int[]> endMap;   // end -> interval

    public ExamRoom(int n) {
        this.N = n;
        pq = new PriorityQueue<>((a, b) -> {
            int distA = getDistance(a);
            int distB = getDistance(b);
            if (distA == distB) return a[0] - b[0]; // smaller start wins
            return distB - distA; // larger distance first
        });
        startMap = new HashMap<>();
        endMap = new HashMap<>();
        addInterval(new int[]{0, n - 1});
    }

    // calculate distance of an interval
    private int getDistance(int[] interval) {
        int start = interval[0], end = interval[1];
        if (start == 0 || end == N - 1) {
            return end - start; // edge interval
        }
        return (end - start) / 2;
    }

    private void addInterval(int[] interval) {
        pq.offer(interval);
        startMap.put(interval[0], interval);
        endMap.put(interval[1], interval);
    }

    private void removeInterval(int[] interval) {
        startMap.remove(interval[0]);
        endMap.remove(interval[1]);
        // Don't remove from pq immediately (lazy deletion)
    }

    public int seat() {
        while (!pq.isEmpty()) {
            int[] interval = pq.poll();

            // skip stale intervals
            if (startMap.get(interval[0]) != interval ||
                endMap.get(interval[1]) != interval) {
                continue;
            }

            int start = interval[0], end = interval[1];
            int seat;
            if (start == 0) {
                seat = 0;
            } else if (end == N - 1) {
                seat = N - 1;
            } else {
                seat = start + (end - start) / 2;
            }

            // remove used interval
            removeInterval(interval);

            // split into left and right
            if (seat > start) addInterval(new int[]{start, seat - 1});
            if (seat < end) addInterval(new int[]{seat + 1, end});

            return seat;
        }
        return -1;
    }

    public void leave(int p) {
        int start = p, end = p;

        if (endMap.containsKey(p - 1)) {
            int[] left = endMap.get(p - 1);
            start = left[0];
            removeInterval(left);
        }
        if (startMap.containsKey(p + 1)) {
            int[] right = startMap.get(p + 1);
            end = right[1];
            removeInterval(right);
        }

        addInterval(new int[]{start, end});
    }
}
