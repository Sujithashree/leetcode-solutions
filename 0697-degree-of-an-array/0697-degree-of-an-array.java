class Solution {
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        HashMap<Integer, Integer> first = new HashMap<>();

        int degree = 0;
        int answer = nums.length;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            // Store first occurrence
            if (!first.containsKey(num)) {
                first.put(num, i);
            }

            // Increase frequency
            count.put(num, count.getOrDefault(num, 0) + 1);

            // Update degree
            degree = Math.max(degree, count.get(num));
        }

        // Find shortest range for elements having maximum frequency
        for (int num : count.keySet()) {
            if (count.get(num) == degree) {
                int start = first.get(num);
                
                // Find last occurrence
                int end = start;
                for (int i = start; i < nums.length; i++) {
                    if (nums[i] == num) {
                        end = i;
                    }
                }

                answer = Math.min(answer, end - start + 1);
            }
        }

        return answer;
    }
}