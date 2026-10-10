class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        int[] diff = new int[n];
        int max = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            max = Math.max(max, diff[i]);
        }

        long total = 0;
        for (int d : diff) {
            total += d;
        }

        if (total <= k) {
            return 0L;
        }

        int left = 0, right = max;

        while (left < right) {
            int mid = left + (right - left) / 2;
            long needed = 0;

            for (int d : diff) {
                if (d > mid) {
                    needed += d - mid;
                }
            }

            if (needed <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        long remaining = 0;
        for (int d : diff) {
            int reduced = Math.min(d, left);
            remaining += (long) reduced * reduced;
        }

        long operations = 0;
        for (int d : diff) {
            if (d > left) {
                operations += d - left;
            }
        }

        for (int i = 0; i < n && operations < k; i++) {
            if (diff[i] >= left && diff[i] > 0) {
                remaining -= (long) left * left;
                remaining += (long) (left - 1) * (left - 1);
                operations++;
            }
        }

        return remaining;
    }
}