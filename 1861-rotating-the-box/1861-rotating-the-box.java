class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
        int m = boxGrid.length;
        int n = boxGrid[0].length;

        // Step 1: Let stones fall to the right
        for (int i = 0; i < m; i++) {
            int empty = n - 1;

            for (int j = n - 1; j >= 0; j--) {
                if (boxGrid[i][j] == '*') {
                    // Obstacle: stones cannot cross it
                    empty = j - 1;
                } 
                else if (boxGrid[i][j] == '#') {
                    // Move stone to the nearest available position
                    boxGrid[i][j] = '.';
                    boxGrid[i][empty] = '#';
                    empty--;
                }
            }
        }

        // Step 2: Rotate 90 degrees clockwise
        char[][] result = new char[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[j][m - 1 - i] = boxGrid[i][j];
            }
        }

        return result;
    }
}