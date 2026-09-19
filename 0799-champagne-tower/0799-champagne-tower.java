class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[] dp = new double[query_row + 2];
        dp[0] = poured;

        for (int row = 0; row < query_row; row++) {
            double[] next = new double[query_row + 2];

            for (int glass = 0; glass <= row; glass++) {
                if (dp[glass] > 1.0) {
                    double overflow = (dp[glass] - 1.0) / 2.0;

                    next[glass] += overflow;
                    next[glass + 1] += overflow;
                }
            }

            dp = next;
        }

        return Math.min(1.0, dp[query_glass]);
    }
}