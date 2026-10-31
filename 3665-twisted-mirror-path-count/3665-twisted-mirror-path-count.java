import java.util.Arrays;

class Solution {
    int MOD = 1_000_000_007;
    int m, n;
    int[][] grid;
    int[][][] memo;

    // dir = 0 means moving right
    // dir = 1 means moving down
    int resolve(int x, int y, int dir) {
        if (x >= m || y >= n)
            return -1;

        if (memo[x][y][dir] != -2)
            return memo[x][y][dir];

        // Empty cell: valid destination
        if (grid[x][y] == 0) {
            return memo[x][y][dir] = x * n + y;
        }

        // Mirror: change direction
        if (dir == 0) {
            // Entered moving right -> turned down
            return memo[x][y][dir] = resolve(x + 1, y, 1);
        } else {
            // Entered moving down -> turned right
            return memo[x][y][dir] = resolve(x, y + 1, 0);
        }
    }

    public int uniquePaths(int[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        this.memo = new int[m][n][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], -2);
            }
        }

        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // We only branch out from empty cells that are reachable
                if (grid[i][j] == 1 || dp[i][j] == 0) continue;
                if (i == m - 1 && j == n - 1) continue;

                // 1. Try moving right: (i, j + 1) with dir = 0
                int destRight = resolve(i, j + 1, 0);
                if (destRight != -1) {
                    int nx = destRight / n;
                    int ny = destRight % n;
                    dp[nx][ny] = (dp[nx][ny] + dp[i][j]) % MOD;
                }

                // 2. Try moving down: (i + 1, j) with dir = 1
                int destDown = resolve(i + 1, j, 1);
                if (destDown != -1) {
                    int nx = destDown / n;
                    int ny = destDown % n;
                    dp[nx][ny] = (dp[nx][ny] + dp[i][j]) % MOD;
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}
