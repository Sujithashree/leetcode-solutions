class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        
    int n = items.length;

    int minPrice = Integer.MAX_VALUE;
    for (int[] x : items) {
        minPrice = Math.min(minPrice, x[1]);
    }

    int[] gain = new int[n];

    for (int i = 0; i < n; i++) {
        gain[i] = 1;

        for (int j = 0; j < n; j++) {
            if (i != j &&
                items[j][0] % items[i][0] == 0) {
                gain[i]++;
            }
        }
    }

    int[] dp = new int[budget + 1];

    for (int i = 0; i < n; i++) {
        int cost = items[i][1];

        for (int b = budget; b >= cost; b--) {
            dp[b] = Math.max(
                dp[b],
                dp[b - cost] + gain[i]
            );
        }
    }

    int ans = budget / minPrice;

    for (int spent = 0; spent <= budget; spent++) {
        ans = Math.max(
            ans,
            dp[spent] + (budget - spent) / minPrice
        );
    }

    return ans;
    }
}