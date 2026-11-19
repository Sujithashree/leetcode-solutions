class Solution {
public int[][] reconstructQueue(int[][] people) {

    // Sort by height descending
    // If heights are equal, sort k ascending
    Arrays.sort(people, (a, b) -> {
        if (a[0] != b[0]) {
            return b[0] - a[0];
        }
        return a[1] - b[1];
    });
    List<int[]> list = new ArrayList<>();
    // Insert each person at index k
    for (int[] person : people) {
        list.add(person[1], person);
    }
    return list.toArray(new int[people.length][]);
}

}