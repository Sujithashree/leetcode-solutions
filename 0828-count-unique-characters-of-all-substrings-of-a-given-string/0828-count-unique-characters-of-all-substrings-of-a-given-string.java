class Solution {
    public int uniqueLetterString(String s) {
        int n = s.length();
        int[][] last = new int[26][2];

        for (int i = 0; i < 26; i++) {
            last[i][0] = -1;
            last[i][1] = -1;
        }

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'A';

            int prev = last[c][0];
            int prevPrev = last[c][1];

            // Count contribution of previous occurrence
            ans += (long) (prev - prevPrev) * (i - prev);

            // Move occurrences
            last[c][1] = prev;
            last[c][0] = i;
        }

        // Count contribution of the last occurrence
        for (int c = 0; c < 26; c++) {
            int prev = last[c][0];
            int prevPrev = last[c][1];

            ans += (long) (prev - prevPrev) * (n - prev);
        }

        return (int) ans;
    }
}