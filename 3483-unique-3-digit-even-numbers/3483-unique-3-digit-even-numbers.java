class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];

        // Count how many times each digit occurs
        for (int digit : digits) {
            freq[digit]++;
        }

        int count = 0;

        // Try every 3-digit number
        for (int num = 100; num <= 998; num += 2) {
            int a = num / 100;          // hundreds digit
            int b = (num / 10) % 10;    // tens digit
            int c = num % 10;           // ones digit

            // Temporarily use the digits
            freq[a]--;
            freq[b]--;
            freq[c]--;

            // All digits must be available
            if (freq[a] >= 0 && freq[b] >= 0 && freq[c] >= 0) {
                count++;
            }

            // Restore the digits
            freq[a]++;
            freq[b]++;
            freq[c]++;
        }

        return count;
    }
}