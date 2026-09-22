import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {

        int sumA = 0;
        int sumB = 0;

        for (int candy : aliceSizes) {
            sumA += candy;
        }

        for (int candy : bobSizes) {
            sumB += candy;
        }

        int diff = (sumA - sumB) / 2;

        Set<Integer> bob = new HashSet<>();

        for (int candy : bobSizes) {
            bob.add(candy);
        }

        for (int a : aliceSizes) {
            int b = a - diff;

            if (bob.contains(b)) {
                return new int[]{a, b};
            }
        }

        return new int[0];
    }
}