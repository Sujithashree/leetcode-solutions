import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[][] pascal;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        
        // Precompute combinations C(n, k) using Pascal's Triangle
        pascal = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            pascal[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                pascal[i][j] = (pascal[i - 1][j - 1] + pascal[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        // Subtract 1 to exclude the original ordering
        return (int) ((dfs(list) - 1 + MOD) % MOD);
    }

    private long dfs(List<Integer> nums) {
        int n = nums.size();
        if (n <= 2) {
            return 1;
        }

        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            if (nums.get(i) < root) {
                left.add(nums.get(i));
            } else {
                right.add(nums.get(i));
            }
        }

        long leftWays = dfs(left);
        long rightWays = dfs(right);
        long combinations = pascal[n - 1][left.size()];

        // Total ways = C(n-1, left.size()) * leftWays * rightWays % MOD
        long ways = (combinations * leftWays) % MOD;
        ways = (ways * rightWays) % MOD;

        return ways;
    }
}