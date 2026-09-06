import java.util.*;

class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {

        // Put all valid genes into a HashSet
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));

        // If endGene is not in the bank, it cannot be reached
        if (!bankSet.contains(endGene)) {
            return -1;
        }

        // BFS queue
        Queue<String> queue = new LinkedList<>();
        queue.offer(startGene);

        // To avoid visiting the same gene again
        Set<String> visited = new HashSet<>();
        visited.add(startGene);

        char[] genes = {'A', 'C', 'G', 'T'};

        int mutations = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            // Process one BFS level
            for (int i = 0; i < size; i++) {

                String current = queue.poll();

                // We reached the target
                if (current.equals(endGene)) {
                    return mutations;
                }

                char[] chars = current.toCharArray();

                // Change each of the 8 positions
                for (int j = 0; j < chars.length; j++) {

                    char original = chars[j];

                    // Try A, C, G, T
                    for (char gene : genes) {

                        // No mutation if character is unchanged
                        if (gene == original) {
                            continue;
                        }

                        chars[j] = gene;

                        String next = new String(chars);

                        // Valid mutation and not visited before
                        if (bankSet.contains(next) && !visited.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }

                    // Restore original character
                    chars[j] = original;
                }
            }

            mutations++;
        }

        return -1;
    }
}