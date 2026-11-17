class Solution {
public int minimumChanges(String s, int k) {
int n = s.length();

    int[][] cost = new int[n][n];
    // Precompute minimum changes for every substring
    for (int len = 2; len <= n; len++) {
        for (int i = 0; i + len <= n; i++) {
            int j = i + len - 1;
            cost[i][j] = Integer.MAX_VALUE;
            // Try all proper divisors
            for (int d = 1; d < len; d++) {
                if (len % d != 0) continue;
                int changes = 0;
                // Check each group
                for (int r = 0; r < d; r++) {
                    int left = i + r;
                    int right = i + len - d + r;
                    while (left < right) {
                        if (s.charAt(left) != s.charAt(right)) {
                            changes++;
                        }
                        left += d;
                        right -= d;
                    }
                }
                cost[i][j] = Math.min(cost[i][j], changes);
            }
        }
    }
    // DP
    int INF = 1_000_000;
    int[][] dp = new int[n + 1][k + 1];
    for (int[] row : dp) {
        java.util.Arrays.fill(row, INF);
    }
    dp[0][0] = 0;
    for (int p = 1; p <= k; p++) {
        for (int i = 2 * p; i <= n; i++) {
            for (int j = 2 * (p - 1); j <= i - 2; j++) {
                if (dp[j][p - 1] == INF) continue;
                dp[i][p] = Math.min(
                    dp[i][p],
                    dp[j][p - 1] + cost[j][i - 1]
                );
            }
        }
    }
    return dp[n][k];
}

}