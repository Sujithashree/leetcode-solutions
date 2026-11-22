class Solution {
    public int numberOfSubstrings(String s) {
        // Track the most recent index where 'a', 'b', and 'c' were seen.
        int[] lastSeen = {-1, -1, -1};
        int count = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Update the last seen index of the current character
            lastSeen[s.charAt(i) - 'a'] = i;
            
            // Find the minimum of the three indices
            int minIndex = Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2]));
            
            // If all three characters have appeared at least once, 
            // minIndex will be >= 0.
            if (minIndex != -1) {
                // Number of valid substrings ending at i is (minIndex + 1)
                count += minIndex + 1;
            }
        }
        
        return count;
    }
}
