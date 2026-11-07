class Solution {
public int findKthNumber(int m, int n, int k) {

    int left = 1;
    int right = m * n;
    while (left < right) {
        int mid = left + (right - left) / 2;
        int count = 0;
        // Count elements <= mid
        for (int i = 1; i <= m; i++) {
            count += Math.min(mid / i, n);
        }
        if (count >= k) {
            right = mid;
        } 
        else {
            left = mid + 1;
        }
    }
    return left;
}

}