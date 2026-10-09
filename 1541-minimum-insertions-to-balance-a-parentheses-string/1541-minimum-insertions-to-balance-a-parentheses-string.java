class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int openCount = 0; // Tracks unmatched '('
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                openCount++;
            } else { // c == ')'
                // Check if the next character is also ')' to form the pair "))"
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i++; // Consume the second ')'
                } else {
                    // Only one ')' present, insert a ')' to complete the pair "))"
                    insertions++;
                }

                // Match the "))" with an existing '(' if available
                if (openCount > 0) {
                    openCount--;
                } else {
                    // No matching '(', so insert one '('
                    insertions++;
                }
            }
        }

        // Each remaining unmatched '(' needs two ')' to balance
        insertions += openCount * 2;

        return insertions;
    }
}