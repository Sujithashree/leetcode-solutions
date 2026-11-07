class Solution {
    public int maximumSwap(int num) {
        char[] digits = String.valueOf(num).toCharArray();

        // Store the last position of each digit
        int[] last = new int[10];

        for (int i = 0; i < digits.length; i++) {
            last[digits[i] - '0'] = i;
        }

        // Find the first position where we can improve the number
        for (int i = 0; i < digits.length; i++) {
            int current = digits[i] - '0';

            // Look for a larger digit, starting from 9
            for (int d = 9; d > current; d--) {
                if (last[d] > i) {
                    // Swap with the rightmost occurrence
                    char temp = digits[i];
                    digits[i] = digits[last[d]];
                    digits[last[d]] = temp;

                    return Integer.parseInt(new String(digits));
                }
            }
        }

        // No beneficial swap found
        return num;
    }
}