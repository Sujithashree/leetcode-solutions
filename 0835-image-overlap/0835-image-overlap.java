import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;

        Map<String, Integer> count = new HashMap<>();
        int answer = 0;

        // Store all 1s in img2
        List<int[]> ones2 = new ArrayList<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img2[r][c] == 1) {
                    ones2.add(new int[]{r, c});
                }
            }
        }

        // Compare every 1 in img1 with every 1 in img2
        for (int r1 = 0; r1 < n; r1++) {
            for (int c1 = 0; c1 < n; c1++) {
                if (img1[r1][c1] == 0) {
                    continue;
                }

                for (int[] p : ones2) {
                    int dr = p[0] - r1;
                    int dc = p[1] - c1;

                    String key = dr + "," + dc;

                    int current = count.getOrDefault(key, 0) + 1;
                    count.put(key, current);

                    answer = Math.max(answer, current);
                }
            }
        }

        return answer;
    }
}