class Solution {
    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;

        // noSwap = minimum swaps up to previous index
        //          when previous index was NOT swapped
        int noSwap = 0;

        // swap = minimum swaps up to previous index
        //        when previous index WAS swapped
        int swap = 1;

        for (int i = 1; i < n; i++) {

            int newNoSwap = Integer.MAX_VALUE;
            int newSwap = Integer.MAX_VALUE;

            // Case 1: Don't swap at i
            // We can continue without swapping if:
            // nums1[i-1] < nums1[i]
            // nums2[i-1] < nums2[i]
            if (nums1[i - 1] < nums1[i] &&
                nums2[i - 1] < nums2[i]) {

                newNoSwap = Math.min(newNoSwap, noSwap);
                newSwap = Math.min(newSwap, swap + 1);
            }

            // Case 2: Swap at i
            // Previous and current elements are from opposite arrays.
            if (nums1[i - 1] < nums2[i] &&
                nums2[i - 1] < nums1[i]) {

                newNoSwap = Math.min(newNoSwap, swap);
                newSwap = Math.min(newSwap, noSwap + 1);
            }

            noSwap = newNoSwap;
            swap = newSwap;
        }

        return Math.min(noSwap, swap);
    }
}