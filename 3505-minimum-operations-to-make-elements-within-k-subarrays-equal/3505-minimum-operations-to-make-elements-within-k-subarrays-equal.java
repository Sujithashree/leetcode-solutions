class Solution {

TreeMap<Integer, Integer> low = new TreeMap<>();
TreeMap<Integer, Integer> high = new TreeMap<>();
long lowSum = 0;
long highSum = 0;
int lowSize = 0;
int highSize = 0;
void addLow(int num) {
    low.put(num, low.getOrDefault(num, 0) + 1);
    lowSum += num;
    lowSize++;
}
void addHigh(int num) {
    high.put(num, high.getOrDefault(num, 0) + 1);
    highSum += num;
    highSize++;
}
void removeLow(int num) {
    int count = low.get(num);
    if (count == 1)
        low.remove(num);
    else
        low.put(num, count - 1);
    lowSum -= num;
    lowSize--;
}
void removeHigh(int num) {
    int count = high.get(num);
    if (count == 1)
        high.remove(num);
    else
        high.put(num, count - 1);
    highSum -= num;
    highSize--;
}
void rebalance() {
    int target = (lowSize + highSize + 1) / 2;
    while (lowSize > target) {
        int val = low.lastKey();
        removeLow(val);
        addHigh(val);
    }
    while (lowSize < target) {
        int val = high.firstKey();
        removeHigh(val);
        addLow(val);
    }
}
void add(int num) {
    if (lowSize == 0 || num <= low.lastKey()) {
        addLow(num);
    } else {
        addHigh(num);
    }
    rebalance();
}
void remove(int num) {
    if (low.containsKey(num)) {
        removeLow(num);
    } else {
        removeHigh(num);
    }
    rebalance();
}
long getCost() {
    long median = low.lastKey();
    return median * lowSize - lowSum
         + highSum - median * highSize;
}
public long minOperations(int[] nums, int x, int k) {
    int n = nums.length;
    long[] cost = new long[n - x + 1];
    // Step 1: Calculate sliding window costs
    for (int i = 0; i < x; i++) {
        add(nums[i]);
    }
    cost[0] = getCost();
    for (int i = x; i < n; i++) {
        remove(nums[i - x]);
        add(nums[i]);
        cost[i - x + 1] = getCost();
    }
    // Step 2: Dynamic Programming
    long INF = Long.MAX_VALUE / 2;
    long[][] dp = new long[k + 1][n + 1];
    for (int j = 1; j <= k; j++) {
        for (int i = 0; i <= n; i++) {
            dp[j][i] = INF;
        }
    }
    for (int j = 1; j <= k; j++) {
        for (int i = 1; i <= n; i++) {
            // Option 1: Skip current element
            dp[j][i] = dp[j][i - 1];
            // Option 2: Select a window of size x
            if (i >= x && dp[j - 1][i - x] != INF) {
                dp[j][i] = Math.min(
                    dp[j][i],
                    dp[j - 1][i - x] + cost[i - x]
                );
            }
        }
    }
    return dp[k][n];
}

}