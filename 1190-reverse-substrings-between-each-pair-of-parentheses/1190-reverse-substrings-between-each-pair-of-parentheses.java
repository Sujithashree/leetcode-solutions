class Solution {
    public String reverseParentheses(String s) {
        Stack<StringBuilder> stack = new Stack<>();
        StringBuilder current = new StringBuilder();

        for (char c : s.toCharArray()) {

            if (c == '(') {
                // Save the string before this '('
                stack.push(current);
                current = new StringBuilder();

            } else if (c == ')') {
                // Reverse the content inside parentheses
                current.reverse();

                // Add it back to the previous string
                StringBuilder previous = stack.pop();
                previous.append(current);
                current = previous;

            } else {
                current.append(c);
            }
        }

        return current.toString();
    }
}