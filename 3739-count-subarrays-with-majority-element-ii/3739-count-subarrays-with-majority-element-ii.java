class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        // The prefix sum can range from -n to n. 
        // We use an offset so all values sent to the BIT are positive indices.
        int offset = n + 1; 
        int maxVal = 2 * n + 2;
        int[] bit = new int[maxVal + 1];
        
        long count = 0;
        int currentSum = 0;
        
        // Add the initial prefix sum of 0 to the tree
        add(bit, currentSum + offset, 1, maxVal);
        
        for (int num : nums) {
            // Treat target as 1, anything else as -1
            currentSum += (num == target) ? 1 : -1;
            
            // We want to find how many previous prefix sums are strictly less than currentSum.
            // So we query the BIT for frequencies up to (currentSum - 1).
            count += query(bit, currentSum - 1 + offset);
            
            // Add the current prefix sum to the BIT for future elements to use
            add(bit, currentSum + offset, 1, maxVal);
        }
        
        return count;
    }
    
    // Standard Fenwick Tree / Binary Indexed Tree add operation
    private void add(int[] bit, int index, int val, int maxVal) {
        for (; index <= maxVal; index += index & (-index)) {
            bit[index] += val;
        }
    }
    
    // Standard Fenwick Tree / Binary Indexed Tree query operation
    private int query(int[] bit, int index) {
        int sum = 0;
        for (; index > 0; index -= index & (-index)) {
            sum += bit[index];
        }
        return sum;
    }
}
