import java.util.*;

class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {

        int n = quality.length;

        // Store workers as {quality, wage}
        Integer[] workers = new Integer[n];

        for (int i = 0; i < n; i++) {
            workers[i] = i;
        }

        // Sort by wage / quality ratio
        Arrays.sort(workers, (a, b) ->
            Double.compare(
                (double) wage[a] / quality[a],
                (double) wage[b] / quality[b]
            )
        );

        // Max heap: largest quality at the top
        PriorityQueue<Integer> maxHeap =
            new PriorityQueue<>(Collections.reverseOrder());

        long qualitySum = 0;
        double answer = Double.MAX_VALUE;

        for (int i : workers) {

            // Add current worker
            maxHeap.offer(quality[i]);
            qualitySum += quality[i];

            // Keep exactly k workers
            if (maxHeap.size() > k) {
                qualitySum -= maxHeap.poll();
            }

            // If we have k workers, calculate the cost
            if (maxHeap.size() == k) {
                double ratio = (double) wage[i] / quality[i];

                double cost = qualitySum * ratio;

                answer = Math.min(answer, cost);
            }
        }

        return answer;
    }
}

