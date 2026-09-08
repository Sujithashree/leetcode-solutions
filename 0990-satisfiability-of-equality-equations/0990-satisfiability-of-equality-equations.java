class Solution {
    public boolean equationsPossible(String[] equations) {
        int[] parent = new int[26];

        // Initially, every variable is its own parent
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }

        // Step 1: Process equality equations
        for (String eq : equations) {
            if (eq.charAt(1) == '=') {
                int a = eq.charAt(0) - 'a';
                int b = eq.charAt(3) - 'a';

                union(parent, a, b);
            }
        }

        // Step 2: Check inequality equations
        for (String eq : equations) {
            if (eq.charAt(1) == '!') {
                int a = eq.charAt(0) - 'a';
                int b = eq.charAt(3) - 'a';

                if (find(parent, a) == find(parent, b)) {
                    return false;
                }
            }
        }

        return true;
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }

        return parent[x];
    }

    private void union(int[] parent, int a, int b) {
        int rootA = find(parent, a);
        int rootB = find(parent, b);

        if (rootA != rootB) {
            parent[rootA] = rootB;
        }
    }
}