class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        int currentTank = 0;
        int startStation = 0;

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            
            // Net gas gained/lost moving from station i to i + 1
            currentTank += gas[i] - cost[i];

            // If tank drops below 0, we can't reach station i + 1.
            // Reset and try starting from station i + 1.
            if (currentTank < 0) {
                startStation = i + 1;
                currentTank = 0;
            }
        }

        // If total gas is at least total cost, startStation is guaranteed to work
        return totalGas >= totalCost ? startStation : -1;
    }
}
