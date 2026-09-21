import java.util.*;

class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;

        // dp[i] stores:
        // difference -> number of arithmetic subsequences
        // of length >= 2 ending at index i
        Map<Long, Integer>[] dp = new HashMap[n];

        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {

                // Use long because nums[i] - nums[j]
                // can overflow int.
                long diff = (long) nums[i] - nums[j];

                int previous = dp[j].getOrDefault(diff, 0);

                // +1 represents the pair [nums[j], nums[i]]
                dp[i].put(
                    diff,
                    dp[i].getOrDefault(diff, 0) + previous + 1
                );

                // Only previous sequences have length >= 3
                ans += previous;
            }
        }

        return ans;
    }
}