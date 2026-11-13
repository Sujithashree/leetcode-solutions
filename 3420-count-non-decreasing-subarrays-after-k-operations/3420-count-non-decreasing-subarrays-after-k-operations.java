import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        long cost = 0;
        
        // Monotonic decreasing deque storing pairs: {blockValue, countOfElementsInBlock}
        Deque<int[]> dq = new ArrayDeque<>();
        int r = n - 1;

        for (int l = n - 1; l >= 0; l--) {
            int count = 1;
            
            // Merge all subsequent blocks whose prefix max is smaller than nums[l]
            while (!dq.isEmpty() && dq.peekLast()[0] <= nums[l]) {
                int[] last = dq.pollLast();
                cost += (long) (nums[l] - last[0]) * last[1];
                count += last[1];
            }
            
            dq.offerLast(new int[]{nums[l], count});

            // Shrink window from the right while operations exceed k
            while (cost > k) {
                int[] first = dq.peekFirst();
                cost -= (long) (first[0] - nums[r]);
                first[1]--;
                
                if (first[1] == 0) {
                    dq.pollFirst();
                }
                r--;
            }

            // All subarrays starting at l and ending at any index in [l, r] are valid
            ans += (r - l + 1);
        }

        return ans;
    }
}
