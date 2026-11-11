class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {

        // Option 1: Buy all items separately
        long option1 = (long) need1 * cost1 + (long) need2 * cost2;

        // Option 2: Buy type 3 items for the common requirement
        int common = Math.min(need1, need2);

        long option2 = (long) common * costBoth
                     + (long) (need1 - common) * cost1
                     + (long) (need2 - common) * cost2;

        // Option 3: Buy only type 3 items
        int maxNeed = Math.max(need1, need2);

        long option3 = (long) maxNeed * costBoth;

        return Math.min(option1, Math.min(option2, option3));
    }
}