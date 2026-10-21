class Solution {
    public int minOperations(String s) {
        int n = s.length();
        
        // 1. Check if the string is already sorted
        boolean isSorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                isSorted = false;
                break;
            }
        }
        if (isSorted) {
            return 0;
        }
        
        // 2. If length is 2 and unsorted, we can only sort substrings of length 1 (no effect)
        if (n == 2) {
            return -1;
        }
        
        // 3. Find the global minimum and maximum characters
        char minChar = 'z';
        char maxChar = 'a';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c < minChar) minChar = c;
            if (c > maxChar) maxChar = c;
        }
        
        // 4. If first char is already minChar OR last char is already maxChar -> 1 operation
        if (s.charAt(0) == minChar || s.charAt(n - 1) == maxChar) {
            return 1;
        }
        
        // 5. Count occurrences of minChar and maxChar to check the 3-operation edge case
        int minCount = 0;
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == minChar) minCount++;
            if (s.charAt(i) == maxChar) maxCount++;
        }
        
        // If the ONLY minChar is at the end AND the ONLY maxChar is at the start -> 3 operations
        if (s.charAt(0) == maxChar && maxCount == 1 && s.charAt(n - 1) == minChar && minCount == 1) {
            return 3;
        }
        
        // 6. Otherwise, 2 operations are always sufficient
        return 2;
    }
}
