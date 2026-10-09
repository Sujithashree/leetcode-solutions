class Solution {
    public int minMoves(int[] nums, int limit) {
        // The possible target sum ranges from 2 to 2 * limit.
        // diff array needs size up to 2 * limit + 2 for difference updates.
        int[] diff = new int[2 * limit + 2];
        int n = nums.length;

        for (int i = 0; i < n / 2; i++) {
            int a = nums[i];
            int b = nums[n - 1 - i];

            int minVal = Math.min(a, b);
            int maxVal = Math.max(a, b);

            // Cost intervals for target sum S:
            // [2, minVal]: 2 moves
            // [minVal + 1, minVal + maxVal - 1]: 1 move
            // [minVal + maxVal]: 0 moves
            // [minVal + maxVal + 1, maxVal + limit]: 1 move
            // [maxVal + limit + 1, 2 * limit]: 2 moves

            // Baseline: Assume 2 moves across [2, 2 * limit]
            diff[2] += 2;
            diff[2 * limit + 1] -= 2;

            // In range [minVal + 1, maxVal + limit], moves decrease by 1 (to 1 move)
            diff[minVal + 1] -= 1;
            diff[maxVal + limit + 1] += 1;

            // At exactly minVal + maxVal, moves decrease by another 1 (to 0 moves)
            diff[minVal + maxVal] -= 1;
            diff[minVal + maxVal + 1] += 1;
        }

        int minMoves = n;
        int currentMoves = 0;

        // Prefix sum to evaluate actual moves for each target sum S
        for (int sum = 2; sum <= 2 * limit; sum++) {
            currentMoves += diff[sum];
            minMoves = Math.min(minMoves, currentMoves);
        }

        return minMoves;
    }
}