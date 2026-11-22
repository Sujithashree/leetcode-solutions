import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maximumLength(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        int maxLen = 1; // A single element is always a valid subset of length 1
        
        // 1 is a special case since 1 squared is always 1
        if (count.containsKey(1)) {
            int ones = count.get(1);
            // The length must be odd. 
            maxLen = Math.max(maxLen, ones % 2 == 0 ? ones - 1 : ones);
        }
        
        for (int x : count.keySet()) {
            // Skip 1, as it's already handled
            if (x == 1) continue; 
            
            long curr = x;
            int currentLen = 0;
            
            // While we have at least a pair to place on the left and right
            while (curr <= Integer.MAX_VALUE && count.getOrDefault((int)curr, 0) >= 2) {
                currentLen += 2;
                curr = curr * curr;
            }
            
            // Check if we can place a single element as the peak of the sequence
            if (curr <= Integer.MAX_VALUE && count.getOrDefault((int)curr, 0) >= 1) {
                currentLen += 1;
            } else {
                // We couldn't find a peak, so the previous element must act as the peak.
                // We subtract 1 because we incorrectly counted the previous element twice.
                currentLen -= 1;
            }
            
            maxLen = Math.max(maxLen, currentLen);
        }
        
        return maxLen;
    }
}
