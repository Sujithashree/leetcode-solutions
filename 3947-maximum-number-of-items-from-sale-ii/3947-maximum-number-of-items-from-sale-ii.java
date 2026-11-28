import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int minPrice = Integer.MAX_VALUE;
        int maxFactor = 0;

        // 1. Find the cheapest price and maximum factor
        for (int[] item : items) {
            if (item[0] > maxFactor) maxFactor = item[0];
            if (item[1] < minPrice) minPrice = item[1];
        }

        // 2. Count frequency of each factor
        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        // 3. Harmonic Sieve: compute total items divisible by each factor i
        int[] multCount = new int[maxFactor + 1];
        for (int i = 1; i <= maxFactor; i++) {
            if (freq[i] > 0) {
                for (int j = i; j <= maxFactor; j += i) {
                    multCount[i] += freq[j];
                }
            }
        }

        // 4. Filter valid 2-for-1 bundles where price < 2 * minPrice
        // Pack (price << 32) | bonusCount into a primitive long for fast sorting
        long threshold = 2L * minPrice;
        long[] bundles = new long[n];
        int validCount = 0;

        for (int[] item : items) {
            int price = item[1];
            if (price < threshold) {
                int bonus = multCount[item[0]] - 1;
                if (bonus > 0) {
                    bundles[validCount++] = ((long) price << 32) | (bonus & 0xFFFFFFFFL);
                }
            }
        }

        // 5. Sort valid bundles by price ascending
        Arrays.sort(bundles, 0, validCount);

        int totalItems = 0;
        long remBudget = budget;

        // 6. Greedily purchase 2-item bundles first
        for (int i = 0; i < validCount; i++) {
            long price = bundles[i] >>> 32;
            if (remBudget < price) break;

            long available = bundles[i] & 0xFFFFFFFFL;
            long buy = Math.min(available, remBudget / price);

            remBudget -= buy * price;
            totalItems += (int) (buy * 2);
        }

        // 7. Spend all remaining budget on the single cheapest item
        totalItems += (int) (remBudget / minPrice);

        return totalItems;
    }
}