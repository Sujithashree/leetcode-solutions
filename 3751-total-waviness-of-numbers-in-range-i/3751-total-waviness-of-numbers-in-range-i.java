class Solution {
public int totalWaviness(int num1, int num2) {
int ans = 0;

    for (int num = num1; num <= num2; num++) {
        String s = String.valueOf(num);
        for (int i = 1; i < s.length() - 1; i++) {
            int prev = s.charAt(i - 1) - '0';
            int curr = s.charAt(i) - '0';
            int next = s.charAt(i + 1) - '0';
            if ((curr > prev && curr > next) ||
                (curr < prev && curr < next)) {
                ans++;
            }
        }
    }
    return ans;
}

}