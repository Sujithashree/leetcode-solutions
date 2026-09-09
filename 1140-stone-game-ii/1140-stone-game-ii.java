class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;

        // suffix[i] = total stones from i to n-1
        int[] suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        // dp[i][M] = maximum stones the current player
        // can get starting from index i with current M.
        int[][] dp = new int[n][n + 1];

        return solve(0, 1, piles, suffix, dp);
    }

    private int solve(int i, int M, int[] piles,
                      int[] suffix, int[][] dp) {

        int n = piles.length;

        // All remaining piles can be taken
        if (i >= n) {
            return 0;
        }

        if (2 * M >= n - i) {
            return suffix[i];
        }

        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        int best = 0;

        // Try taking X piles, where 1 <= X <= 2M
        for (int X = 1; X <= 2 * M && i + X <= n; X++) {

            // Stones taken now
            int taken = suffix[i] - suffix[i + X];

            // Opponent gets to play from i + X
            int opponent = solve(
                i + X,
                Math.max(M, X),
                piles,
                suffix,
                dp
            );

            // Total remaining stones = suffix[i]
            // Current player gets everything except
            // what opponent can force for themselves.
            int current = suffix[i] - opponent;

            best = Math.max(best, current);
        }

        dp[i][M] = best;
        return best;
    }
}