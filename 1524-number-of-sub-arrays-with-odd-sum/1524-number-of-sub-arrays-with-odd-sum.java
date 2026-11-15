class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1_000_000_007;
        int oddCount = 0;
        int evenCount = 1; // Represents the initial prefix sum of 0 (which is even)
        int prefixSum = 0;
        int totalSubarrays = 0;

        for (int num : arr) {
            prefixSum += num;

            if (prefixSum % 2 == 1) {
                // Current prefix sum is odd; pair with all previous even prefix sums
                totalSubarrays = (totalSubarrays + evenCount) % MOD;
                oddCount++;
            } else {
                // Current prefix sum is even; pair with all previous odd prefix sums
                totalSubarrays = (totalSubarrays + oddCount) % MOD;
                evenCount++;
            }
        }

        return totalSubarrays;
    }
}
