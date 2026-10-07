class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;

        int[] arr = new int[n + 2];

        arr[0] = 1;
        arr[n + 1] = 1;

        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }

        int[][] dp = new int[n + 2][n + 2];

        // length = number of balloons inside the interval
        for (int len = 1; len <= n; len++) {

            for (int left = 1; left + len - 1 <= n; left++) {

                int right = left + len - 1;

                // Choose k as the last balloon to burst
                for (int k = left; k <= right; k++) {

                    int coins = dp[left][k - 1]
                              + dp[k + 1][right]
                              + arr[left - 1] * arr[k] * arr[right + 1];

                    dp[left][right] = Math.max(dp[left][right], coins);
                }
            }
        }

        return dp[1][n];
    }
}