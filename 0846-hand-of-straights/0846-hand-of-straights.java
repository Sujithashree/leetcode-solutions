class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {

        int n = hand.length;

        // Total cards must be divisible into equal-sized groups
        if (n % groupSize != 0) {
            return false;
        }

        // Store frequency of each card
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int card : hand) {
            map.put(card, map.getOrDefault(card, 0) + 1);
        }

        // Keep creating groups
        while (!map.isEmpty()) {

            // Smallest available card must start a group
            int first = map.firstKey();

            for (int card = first; card < first + groupSize; card++) {

                // Required card doesn't exist
                if (!map.containsKey(card)) {
                    return false;
                }

                // Use one copy
                int count = map.get(card);

                if (count == 1) {
                    map.remove(card);
                } else {
                    map.put(card, count - 1);
                }
            }
        }

        return true;
    }
}

