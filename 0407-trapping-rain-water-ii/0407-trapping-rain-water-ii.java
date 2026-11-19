class Solution {
public int trapRainWater(int[][] heightMap) {

    int m = heightMap.length;
    int n = heightMap[0].length;
    if (m < 3 || n < 3) {
        return 0;
    }
    boolean[][] visited = new boolean[m][n];
    // Min Heap: stores {height, row, col}
    PriorityQueue<int[]> pq = new PriorityQueue<>(
        (a, b) -> Integer.compare(a[0], b[0])
    );
    // Add boundary cells
    for (int i = 0; i < m; i++) {
        pq.offer(new int[]{heightMap[i][0], i, 0});
        pq.offer(new int[]{heightMap[i][n - 1], i, n - 1});
        visited[i][0] = true;
        visited[i][n - 1] = true;
    }
    for (int j = 1; j < n - 1; j++) {
        pq.offer(new int[]{heightMap[0][j], 0, j});
        pq.offer(new int[]{heightMap[m - 1][j], m - 1, j});
        visited[0][j] = true;
        visited[m - 1][j] = true;
    }
    // Four directions
    int[][] directions = {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };
    int water = 0;
    // BFS using Min Heap
    while (!pq.isEmpty()) {
        int[] current = pq.poll();
        int height = current[0];
        int row = current[1];
        int col = current[2];
        for (int[] dir : directions) {
            int nr = row + dir[0];
            int nc = col + dir[1];
            // Check boundaries and visited
            if (nr < 0 || nr >= m ||
                nc < 0 || nc >= n ||
                visited[nr][nc]) {
                continue;
            }
            visited[nr][nc] = true;
            // Calculate trapped water
            water += Math.max(0,
                height - heightMap[nr][nc]);
            // Effective height after trapping water
            int newHeight = Math.max(
                height,
                heightMap[nr][nc]
            );
            pq.offer(new int[]{
                newHeight, nr, nc
            });
        }
    }
    return water;
}

}