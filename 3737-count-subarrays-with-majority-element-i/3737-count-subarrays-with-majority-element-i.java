class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int ans = 0;

        // Iterate through all possible starting points of a subarray
        for (int i = 0; i < n; i++) {
            int count = 0; // Tracks the frequency of 'target' in the current subarray
            
            // Expand the subarray to the right
            for (int j = i; j < n; j++) {
                if (nums[j] == target) {
                    count++;
                }
                
                int len = j - i + 1;
                // Check if target strictly appears more than half the time
                if (count > len / 2) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
