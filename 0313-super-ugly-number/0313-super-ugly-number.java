class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int k = primes.length;

        int[] dp = new int[n];
        int[] index = new int[k];

        dp[0] = 1;

        for (int i = 1; i < n; i++) {

            long next = Long.MAX_VALUE;

            // Find the smallest possible next ugly number
            for (int j = 0; j < k; j++) {
                long candidate = (long) primes[j] * dp[index[j]];
                next = Math.min(next, candidate);
            }

            dp[i] = (int) next;

            // Move every pointer that produced this number
            for (int j = 0; j < k; j++) {
                long candidate = (long) primes[j] * dp[index[j]];

                if (candidate == next) {
                    index[j]++;
                }
            }
        }

        return dp[n - 1];
    }
}