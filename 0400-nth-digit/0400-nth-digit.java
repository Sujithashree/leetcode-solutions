class Solution {
public int findNthDigit(int n) {

    long digits = 1;
    long count = 9;
    long start = 1;
    // Find the digit-length group
    while (n > digits * count) {
        n -= digits * count;
        digits++;
        count *= 10;
        start *= 10;
    }
    // Find the actual number
    long num = start + (n - 1) / digits;
    // Find the digit position
    int index = (int)((n - 1) % digits);
    String s = String.valueOf(num);
    return s.charAt(index) - '0';
}

}