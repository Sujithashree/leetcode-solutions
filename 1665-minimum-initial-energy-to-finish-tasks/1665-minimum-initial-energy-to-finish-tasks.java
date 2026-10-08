import java.util.Arrays;

class Solution {
    public int minimumEffort(int[][] tasks) {
        // Sort descending based on (minimum - actual)
        // (b[1] - b[0]) - (a[1] - a[0])
        Arrays.sort(tasks, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));

        int initialEnergy = 0;
        int currentEnergy = 0;

        for (int[] task : tasks) {
            int actual = task[0];
            int minimum = task[1];

            // If current remaining energy is less than required minimum
            if (currentEnergy < minimum) {
                // Add the deficit to our initial starting energy
                initialEnergy += (minimum - currentEnergy);
                // After adding, current energy matches the required minimum
                currentEnergy = minimum;
            }

            // Spend the actual energy required by this task
            currentEnergy -= actual;
        }

        return initialEnergy;
    }
}