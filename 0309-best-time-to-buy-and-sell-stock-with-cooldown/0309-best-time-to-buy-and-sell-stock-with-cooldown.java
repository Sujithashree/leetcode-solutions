class Solution {
    public int maxProfit(int[] prices) {
        int hold = -prices[0];
        int sold = 0;
        int cooldown = 0;

        for (int i = 1; i < prices.length; i++) {
            int prevHold = hold;
            int prevSold = sold;
            int prevCooldown = cooldown;

            // Buy today OR continue holding
            hold = Math.max(prevHold, prevCooldown - prices[i]);

            // Sell today
            sold = prevHold + prices[i];

            // Do nothing today OR cooldown after selling
            cooldown = Math.max(prevCooldown, prevSold);
        }

        return Math.max(sold, cooldown);
    }
}