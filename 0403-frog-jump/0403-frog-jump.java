class Solution {
public boolean canCross(int[] stones) {

    int n = stones.length;
    // If the first jump cannot reach the second stone
    if (stones[1] != 1) {
        return false;
    }
    // Only two stones: 0 and 1
    if (n == 2) {
        return true;
    }
    Map<Integer, Set<Integer>> map = new HashMap<>();
    for (int stone : stones) {
        map.put(stone, new HashSet<>());
    }
    // First jump is 1
    map.get(1).add(1);
    for (int stone : stones) {
        for (int jump : map.get(stone)) {
            for (int nextJump = jump - 1;
                 nextJump <= jump + 1;
                 nextJump++) {
                if (nextJump <= 0) {
                    continue;
                }
                int nextPosition = stone + nextJump;
                if (nextPosition == stones[n - 1]) {
                    return true;
                }
                if (map.containsKey(nextPosition)) {
                    map.get(nextPosition).add(nextJump);
                }
            }
        }
    }
    return false;
}

}