import java.util.*;

class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        
        // If start or end cell has a thief, safeness is strictly 0
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0; 
        }

        int[][] safeness = new int[n][n];
        for (int[] row : safeness) Arrays.fill(row, -1);
        Queue<int[]> q = new LinkedList<>();

        // Step 1: Multi-source BFS to find the safeness factor of each cell
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    q.offer(new int[]{i, j});
                    safeness[i][j] = 0;
                }
            }
        }

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1];
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dRow[i];
                int nc = c + dCol[i];
                // If valid and unvisited
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && safeness[nr][nc] == -1) {
                    safeness[nr][nc] = safeness[r][c] + 1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        // Step 2: Max-Heap BFS to find the path with the maximum minimum safeness
        // pq stores: {current_path_safeness, row, col}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        pq.offer(new int[]{safeness[0][0], 0, 0});
        
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int safe = curr[0], r = curr[1], c = curr[2];

            // If we reached the destination, return the safeness of this path
            if (r == n - 1 && c == n - 1) {
                return safe;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dRow[i];
                int nc = c + dCol[i];
                
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    // The safeness of the extended path is the minimum of the current path's 
                    // safeness and the next cell's safeness
                    pq.offer(new int[]{Math.min(safe, safeness[nr][nc]), nr, nc});
                }
            }
        }

        return 0;
    }
}
