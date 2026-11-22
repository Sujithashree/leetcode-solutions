import java.util.*;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        
        // This will store the minimum damage taken to reach each cell
        int[][] minDamage = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(minDamage[i], Integer.MAX_VALUE);
        }
        
        Deque<int[]> dq = new ArrayDeque<>();
        
        // Initialize the starting point
        minDamage[0][0] = grid.get(0).get(0);
        dq.offerFirst(new int[]{0, 0});
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!dq.isEmpty()) {
            int[] curr = dq.pollFirst();
            int r = curr[0];
            int c = curr[1];
            
            // In 0-1 BFS, the first time we pop the destination, it is guaranteed to be the minimum cost.
            if (r == m - 1 && c == n - 1) {
                // Return true if remaining health is 1 or more
                return health - minDamage[r][c] >= 1;
            }
            
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                
                // If within bounds
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int cost = grid.get(nr).get(nc);
                    
                    // If we found a path with less damage to the neighbor cell, update and add to queue
                    if (minDamage[r][c] + cost < minDamage[nr][nc]) {
                        minDamage[nr][nc] = minDamage[r][c] + cost;
                        
                        if (cost == 0) {
                            dq.offerFirst(new int[]{nr, nc}); // 0 cost goes to front
                        } else {
                            dq.offerLast(new int[]{nr, nc});  // 1 cost goes to back
                        }
                    }
                }
            }
        }
        
        return false;
    }
}
