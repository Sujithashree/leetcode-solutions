class Solution {
    public int sortPermutation(int[] nums) {
        int k = -1; // -1 represents all 1s in two's complement binary
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                k &= nums[i];
            }
        }
        return k == -1 ? 0 : k;
    }
}
