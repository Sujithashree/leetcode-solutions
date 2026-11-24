class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 2; // Any subarray of length 2 always satisfies prod(a,b) == lcm(a,b) * gcd(a,b)

        for (int i = 0; i < n; i++) {
            long prod = 1;
            int currentGcd = 0;
            int currentLcm = 1;

            for (int j = i; j < n; j++) {
                prod *= nums[j];
                currentGcd = gcd(currentGcd, nums[j]);
                currentLcm = lcm(currentLcm, nums[j]);

                // Max possible value of lcm * gcd for nums[i] <= 10 is 2520 * 10 = 25200
                if (prod > 25200) {
                    break;
                }

                if (prod == (long) currentLcm * currentGcd) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
}
