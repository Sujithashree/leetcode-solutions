class Solution {
public int[] pivotArray(int[] nums, int pivot) {

    int n = nums.length;
    int[] ans = new int[n];
    int index = 0;
    // 1. Add elements smaller than pivot
    for (int num : nums) {
        if (num < pivot) {
            ans[index++] = num;
        }
    }
    // 2. Add elements equal to pivot
    for (int num : nums) {
        if (num == pivot) {
            ans[index++] = num;
        }
    }
    // 3. Add elements greater than pivot
    for (int num : nums) {
        if (num > pivot) {
            ans[index++] = num;
        }
    }
    return ans;
}

}