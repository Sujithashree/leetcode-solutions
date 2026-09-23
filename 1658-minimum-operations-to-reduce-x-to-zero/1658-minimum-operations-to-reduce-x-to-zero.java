class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;

        // Sum of the entire array
        int total = 0;
        for (int num : nums) {
            total += num;
        }

        // We need to keep a subarray with this sum
        int target = total - x;

        // If target < 0, even removing everything is not enough
        if (target < 0) {
            return -1;
        }

        // Find the longest subarray with sum == target
        int left = 0;
        int sum = 0;
        int maxLen = -1;

        for (int right = 0; right < n; right++) {
            sum += nums[right];

            // Shrink the window if its sum is too large
            while (left <= right && sum > target) {
                sum -= nums[left];
                left++;
            }

            // Found a valid subarray
            if (sum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        // No valid subarray
        if (maxLen == -1) {
            return -1;
        }

        // Everything outside the subarray must be removed
        return n - maxLen;
    }
}