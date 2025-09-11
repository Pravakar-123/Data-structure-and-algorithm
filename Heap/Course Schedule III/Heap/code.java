import java.util.*;

class Solution {
    public int scheduleCourse(int[][] courses) {
        // Step 1: sort by deadline
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);

        // max heap to store taken course durations
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        int currentTime = 0;

        for (int[] course : courses) {
            int duration = course[0], deadline = course[1];
            if (currentTime + duration <= deadline) {
                // take this course
                currentTime += duration;
                maxHeap.add(duration);
            } else if (!maxHeap.isEmpty() && maxHeap.peek() > duration) {
                // replace the longest course with this shorter one
                currentTime += duration - maxHeap.poll();
                maxHeap.add(duration);
            }
        }

        return maxHeap.size();
    }

   
}
