import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        backtrack(result, "", 0, 0, n);

        return result;
    }

    private void backtrack(
        List<String> result,
        String current,
        int open,
        int close,
        int n
    ) {
        // We have used all parentheses
        if (current.length() == n * 2) {
            result.add(current);
            return;
        }

        // We can add '(' as long as we have not used all n opening brackets
        if (open < n) {
            backtrack(result, current + "(", open + 1, close, n);
        }

        // We can add ')' only when there is an unmatched '('
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, n);
        }
    }
}