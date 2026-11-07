class Solution {
public int[] constructArray(int n, int k) {

    int[] ans = new int[n];
    int left = 1;
    int right = n;
    int index = 0;
    while (left <= right) {
        if (k > 1) {
            if (k % 2 == 1) {
                ans[index++] = left++;
            } else {
                ans[index++] = right--;
            }
            k--;
        } else {
            while (left <= right) {
                ans[index++] = left++;
            }
        }
    }
    return ans;
}

}