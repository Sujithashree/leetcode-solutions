class Solution {
public int numberOfSpecialChars(String word) {
int[] firstLower = new int[26];
int[] lastLower = new int[26];
int[] firstUpper = new int[26];

    java.util.Arrays.fill(firstLower, Integer.MAX_VALUE);
    java.util.Arrays.fill(lastLower, -1);
    java.util.Arrays.fill(firstUpper, Integer.MAX_VALUE);
    for (int i = 0; i < word.length(); i++) {
        char c = word.charAt(i);
        if (Character.isLowerCase(c)) {
            int idx = c - 'a';
            firstLower[idx] = Math.min(firstLower[idx], i);
            lastLower[idx] = i;
        } else {
            int idx = c - 'A';
            firstUpper[idx] = Math.min(firstUpper[idx], i);
        }
    }
    int count = 0;
    for (int i = 0; i < 26; i++) {
        if (lastLower[i] != -1 &&
            firstUpper[i] != Integer.MAX_VALUE &&
            lastLower[i] < firstUpper[i]) {
            count++;
        }
    }
    return count;
}

}