class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;

        if (n == 0 || k == 0) {
            return 0;
        }

        // If k is large, it becomes the unlimited transactions problem
        if (k >= n / 2) {
            int profit = 0;

            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }

            return profit;
        }

        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];

        // Initially, buying costs money
        Arrays.fill(buy, Integer.MIN_VALUE / 2);

        for (int price : prices) {
            for (int j = 1; j <= k; j++) {
                buy[j] = Math.max(buy[j], sell[j - 1] - price);
                sell[j] = Math.max(sell[j], buy[j] + price);
            }
        }

        return sell[k];
    }
}