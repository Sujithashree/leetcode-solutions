import java.util.Arrays;

class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;

        Arrays.sort(nums1);

        Integer[] indices = new Integer[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        // Sort nums2 indices from largest value to smallest value.
        Arrays.sort(indices, (a, b) -> Integer.compare(nums2[b], nums2[a]));

        int[] result = new int[n];

        int left = 0;
        int right = n - 1;

        for (int idx : indices) {

            // If the largest nums1 can beat nums2[idx],
            // use it.
            if (nums1[right] > nums2[idx]) {
                result[idx] = nums1[right];
                right--;
            } else {
                // Otherwise sacrifice the smallest nums1.
                result[idx] = nums1[left];
                left++;
            }
        }

        return result;
    }
}