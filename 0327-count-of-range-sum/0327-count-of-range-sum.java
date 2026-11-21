class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        // Use long to prevent overflow during prefix sum calculation
        long[] prefixSums = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
        }
        
        long[] temp = new long[n + 1];
        return mergeSortAndCount(prefixSums, temp, 0, n, lower, upper);
    }
    
    private int mergeSortAndCount(long[] sums, long[] temp, int left, int right, int lower, int upper) {
        if (left >= right) {
            return 0;
        }
        
        int mid = left + (right - left) / 2;
        int count = mergeSortAndCount(sums, temp, left, mid, lower, upper) + 
                    mergeSortAndCount(sums, temp, mid + 1, right, lower, upper);
        
        // Count the valid pairs across the two halves
        int j1 = mid + 1;
        int j2 = mid + 1;
        for (int i = left; i <= mid; i++) {
            // Find the first index in the right half where the difference is >= lower
            while (j1 <= right && sums[j1] - sums[i] < lower) j1++;
            // Find the first index in the right half where the difference is > upper
            while (j2 <= right && sums[j2] - sums[i] <= upper) j2++;
            
            count += (j2 - j1);
        }
        
        // Standard Merge step
        int p = left;
        int q = mid + 1;
        int k = left;
        
        while (p <= mid && q <= right) {
            if (sums[p] <= sums[q]) {
                temp[k++] = sums[p++];
            } else {
                temp[k++] = sums[q++];
            }
        }
        
        while (p <= mid) temp[k++] = sums[p++];
        while (q <= right) temp[k++] = sums[q++];
        
        // Copy sorted elements back to original array
        for (int idx = left; idx <= right; idx++) {
            sums[idx] = temp[idx];
        }
        
        return count;
    }
}
