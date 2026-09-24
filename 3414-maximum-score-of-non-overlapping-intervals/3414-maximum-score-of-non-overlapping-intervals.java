import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        // Store [left, right, weight, originalIndex]
        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by starting position
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // next[i] = first interval whose start > arr[i].right
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int left = arr[i][0];
            int right = arr[i][1];

            int lo = i + 1;
            int hi = n;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (arr[mid][0] > right) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }

            next[i] = lo;
        }

        /*
         * dp[i][k] = maximum score using intervals from i onward,
         *            choosing at most k intervals.
         */
        long[][] dp = new long[n + 1][5];

        /*
         * code[i][k] stores the lexicographically smallest set of
         * original indices achieving dp[i][k].
         *
         * Four indices are packed into one long.
         * Each index fits in 16 bits because n <= 50000.
         *
         * 65535 is used as an "empty" slot.
         */
        long[][] code = new long[n + 1][5];

        long EMPTY = pack(65535, 65535, 65535, 65535);

        for (int i = 0; i <= n; i++) {
            Arrays.fill(code[i], EMPTY);
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {

                // Option 1: skip this interval
                long skipScore = dp[i + 1][k];
                long skipCode = code[i + 1][k];

                // Option 2: take this interval
                long takeScore = arr[i][2] + dp[next[i]][k - 1];
                long takeCode = insert(code[next[i]][k - 1], arr[i][3]);

                if (takeScore > skipScore) {
                    dp[i][k] = takeScore;
                    code[i][k] = takeCode;
                } else if (takeScore < skipScore) {
                    dp[i][k] = skipScore;
                    code[i][k] = skipCode;
                } else {
                    // Same score -> lexicographically smaller indices
                    dp[i][k] = skipScore;

                    if (lexLess(takeCode, skipCode)) {
                        code[i][k] = takeCode;
                    } else {
                        code[i][k] = skipCode;
                    }
                }
            }
        }

        // Extract the four packed indices
        long resultCode = code[0][4];

        int[] result = new int[4];
        int size = 0;

        for (int shift = 48; shift >= 0; shift -= 16) {
            int index = (int) ((resultCode >>> shift) & 0xFFFF);

            if (index == 65535) {
                break;
            }

            result[size++] = index;
        }

        return Arrays.copyOf(result, size);
    }

    // Pack four 16-bit values into a long
    private static long pack(int a, int b, int c, int d) {
        return ((long) a << 48)
             | ((long) b << 32)
             | ((long) c << 16)
             | (long) d;
    }

    // Insert an original index into an already sorted list of indices
    private static long insert(long code, int index) {
        int[] values = new int[4];

        for (int i = 0; i < 4; i++) {
            values[i] = (int) ((code >>> (48 - 16 * i)) & 0xFFFF);
        }

        int pos = 0;

        while (pos < 4 && values[pos] < index) {
            pos++;
        }

        // Shift right
        for (int i = 3; i > pos; i--) {
            values[i] = values[i - 1];
        }

        values[pos] = index;

        return pack(values[0], values[1], values[2], values[3]);
    }

    // Compare two sorted index lists lexicographically
    private static boolean lexLess(long a, long b) {
        for (int shift = 48; shift >= 0; shift -= 16) {
            int x = (int) ((a >>> shift) & 0xFFFF);
            int y = (int) ((b >>> shift) & 0xFFFF);

            if (x != y) {
                return x < y;
            }

            // Both lists ended
            if (x == 65535) {
                return false;
            }
        }

        return false;
    }
}