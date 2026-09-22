import java.util.Arrays;

class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);

        int left = 0;
        int right = people.length - 1;
        int boats = 0;

        while (left <= right) {

            // Try to put the lightest and heaviest
            // person in the same boat.
            if (people[left] + people[right] <= limit) {
                left++;
            }

            // The heaviest person is placed in a boat.
            right--;
            boats++;
        }

        return boats;
    }
}