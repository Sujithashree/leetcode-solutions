class Solution {
    public boolean checkString(String s) {
        // If "ba" exists in the string, it means an 'a' appeared after a 'b'.
        return !s.contains("ba");
    }
}
