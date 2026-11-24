class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        int[] counts = new int[n + 1];

        for (int num : arr) {
            counts[Math.min(num, n)]++;
        }

        int maxVal = 1;
        for (int num = 2; num <= n; num++) {
            maxVal = Math.min(maxVal + counts[num], num);
        }

        return maxVal;
    }
}
