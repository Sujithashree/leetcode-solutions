class Solution {
public int numPrimeArrangements(int n) {
long MOD = 1000000007;
int primes = 0;

    for (int i = 2; i <= n; i++) {
        if (isPrime(i)) {
            primes++;
        }
    }
    long primeWays = 1;
    long nonPrimeWays = 1;
    for (int i = 2; i <= primes; i++) {
        primeWays = (primeWays * i) % MOD;
    }
    for (int i = 2; i <= n - primes; i++) {
        nonPrimeWays = (nonPrimeWays * i) % MOD;
    }
    return (int) ((primeWays * nonPrimeWays) % MOD);
}
private boolean isPrime(int n) {
    if (n < 2) return false;
    for (int i = 2; i * i <= n; i++) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

}