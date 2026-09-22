import java.util.Arrays;

class Solution {
    public int sumSubseqWidths(int[] nums) {
        final long MOD = 1_000_000_007L;

        Arrays.sort(nums);

        int n = nums.length;

        long power = 1;
        long answer = 0;

        for (int i = 0; i < n; i++) {

            // nums[i] as maximum
            long maxContribution = nums[i] * power % MOD;

            // nums[i] as minimum
            long minPower = modPow(2, n - 1 - i, MOD);
            long minContribution = nums[i] * minPower % MOD;

            answer = (answer + maxContribution - minContribution) % MOD;

            power = (power * 2) % MOD;
        }

        return (int) ((answer + MOD) % MOD);
    }

    private long modPow(long base, int exponent, long mod) {
        long result = 1;

        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = result * base % mod;
            }

            base = base * base % mod;
            exponent >>= 1;
        }

        return result;
    }
}