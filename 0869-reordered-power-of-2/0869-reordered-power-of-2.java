class Solution {
    public boolean reorderedPowerOf2(int n) {
        int target = digitMask(n);

        for (int i = 0; i <= 30; i++) {
            int power = 1 << i;

            if (digitMask(power) == target) {
                return true;
            }
        }

        return false;
    }

    private int digitMask(int n) {
        int mask = 0;

        while (n > 0) {
            int digit = n % 10;
            mask += 1 << digit;
            n /= 10;
        }

        return mask;
    }
}