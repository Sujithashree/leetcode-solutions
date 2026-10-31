class Solution {

long[][][][] memoWays;
long[][][][] memoSum;
boolean[][][][] visited;
String s;
public long totalWaviness(long num1, long num2) {
    return solve(num2) - solve(num1 - 1);
}
long solve(long num) {
    if (num < 0) return 0;
    s = String.valueOf(num);
    int len = s.length();
    memoWays = new long[len][11][11][2];
    memoSum = new long[len][11][11][2];
    visited = new boolean[len][11][11][2];
    return dfs(0, -1, -1, 1)[1];
}
// Returns {number of valid numbers, total waviness}
long[] dfs(int pos, int prev2, int prev1, int tight) {
    if (pos == s.length()) {
        return new long[]{1, 0};
    }
    int a = prev2 + 1;
    int b = prev1 + 1;
    if (tight == 0 && visited[pos][a][b][0]) {
        return new long[]{
            memoWays[pos][a][b][0],
            memoSum[pos][a][b][0]
        };
    }
    int limit = tight == 1 ? s.charAt(pos) - '0' : 9;
    long ways = 0;
    long sum = 0;
    for (int d = 0; d <= limit; d++) {
        int newTight = (tight == 1 && d == limit) ? 1 : 0;
        int x = prev2;
        int y = prev1;
        long extra = 0;
        if (y == -1) {
            if (d != 0) {
                y = d;
            }
        } else if (x == -1) {
            x = y;
            y = d;
        } else {
            if ((y > x && y > d) ||
                (y < x && y < d)) {
                extra = 1;
            }
            x = y;
            y = d;
        }
        long[] child = dfs(pos + 1, x, y, newTight);
        ways += child[0];
        sum += child[1] + extra * child[0];
    }
    if (tight == 0) {
        visited[pos][a][b][0] = true;
        memoWays[pos][a][b][0] = ways;
        memoSum[pos][a][b][0] = sum;
    }
    return new long[]{ways, sum};
}

}