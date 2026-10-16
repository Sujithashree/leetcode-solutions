import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1_000_000_007;

    // Segment tree node arrays (size 4 * N)
    private long[] sum;
    private long[] sumSq;
    private long[] lazy;

    private void apply(int node, int l, int r, long val) {
        long len = r - l + 1;
        val %= MOD;
        
        // sumSq = sumSq + 2 * val * sum + len * val^2
        long term1 = (2 * val % MOD) * sum[node] % MOD;
        long term2 = len * (val * val % MOD) % MOD;
        sumSq[node] = (sumSq[node] + term1 + term2) % MOD;

        // sum = sum + len * val
        sum[node] = (sum[node] + len * val) % MOD;

        // lazy update
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void pushDown(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = l + (r - l) / 2;
            apply(2 * node, l, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void pushUp(int node) {
        sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
        sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
    }

    private void update(int node, int l, int r, int ql, int qr, int val) {
        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }
        pushDown(node, l, r);
        int mid = l + (r - l) / 2;
        if (ql <= mid) {
            update(2 * node, l, mid, ql, qr, val);
        }
        if (qr > mid) {
            update(2 * node + 1, mid + 1, r, ql, qr, val);
        }
        pushUp(node);
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;

        sum = new long[4 * n];
        sumSq = new long[4 * n];
        lazy = new long[4 * n];

        // Track last seen index of each element
        // Since nums[i] <= 100,000, an int[] or HashMap can be used
        Map<Integer, Integer> lastPos = new HashMap<>();

        long totalAns = 0;

        for (int j = 0; j < n; j++) {
            int prevIdx = lastPos.getOrDefault(nums[j], -1);
            int left = prevIdx + 1;
            int right = j;

            // Increment count of distinct elements for all starting points in [left, right]
            update(1, 0, n - 1, left, right, 1);

            // Add the sum of squares of distinct counts for all subarrays ending at index j
            totalAns = (totalAns + sumSq[1]) % MOD;

            lastPos.put(nums[j], j);
        }

        return (int) totalAns;
    }
}
