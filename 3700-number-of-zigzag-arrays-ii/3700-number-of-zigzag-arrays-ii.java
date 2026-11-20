import java.util.*;

class Solution {
private static final long MOD = 1_000_000_007L;

public int zigZagArrays(int n, int l, int r) {
    int m = r - l + 1;
    int size = 2 * m;
    long[][] trans = new long[size][size];
    // Increasing -> Decreasing
    for (int x = 0; x < m; x++) {
        for (int y = 0; y < x; y++) {
            trans[y][m + x] = 1;
        }
    }
    // Decreasing -> Increasing
    for (int x = 0; x < m; x++) {
        for (int y = x + 1; y < m; y++) {
            trans[m + y][x] = 1;
        }
    }
    // Calculate transition matrix^(n-1)
    long[][] power = matrixPow(trans, n - 1);
    // Initially, every value can start in either direction
    long[] init = new long[size];
    Arrays.fill(init, 1);
    long[] result = multiply(power, init);
    long ans = 0;
    for (long v : result) {
        ans = (ans + v) % MOD;
    }
    return (int) ans;
}
private long[][] matrixPow(long[][] mat, long exp) {
    int n = mat.length;
    long[][] res = new long[n][n];
    for (int i = 0; i < n; i++) {
        res[i][i] = 1;
    }
    while (exp > 0) {
        if ((exp & 1) == 1) {
            res = multiply(res, mat);
        }
        mat = multiply(mat, mat);
        exp >>= 1;
    }
    return res;
}
private long[][] multiply(long[][] a, long[][] b) {
    int n = a.length;
    long[][] res = new long[n][n];
    for (int i = 0; i < n; i++) {
        for (int k = 0; k < n; k++) {
            if (a[i][k] == 0) continue;
            for (int j = 0; j < n; j++) {
                if (b[k][j] == 0) continue;
                res[i][j] = (
                    res[i][j] + a[i][k] * b[k][j]
                ) % MOD;
            }
        }
    }
    return res;
}
private long[] multiply(long[][] mat, long[] vec) {
    int n = mat.length;
    long[] res = new long[n];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            res[i] = (
                res[i] + mat[i][j] * vec[j]
            ) % MOD;
        }
    }
    return res;
}

}