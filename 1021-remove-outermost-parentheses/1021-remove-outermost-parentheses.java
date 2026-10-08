class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int opened = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // If opened > 0, this '(' is not outermost
                if (opened > 0) {
                    result.append(c);
                }
                opened++;
            } else { // c == ')'
                opened--;
                // If opened > 0 after decrementing, this ')' is not outermost
                if (opened > 0) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}