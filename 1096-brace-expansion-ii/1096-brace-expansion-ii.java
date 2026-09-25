class Solution {
    private String s;
    private int index;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        index = 0;

        Set<String> result = parseExpression();

        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);
        return answer;
    }

    // Parses comma-separated expressions.
    // Example: a,b,{c,d}
    private Set<String> parseExpression() {
        Set<String> result = parseTerm();

        while (index < s.length() && s.charAt(index) == ',') {
            index++; // skip ','

            Set<String> next = parseTerm();
            result.addAll(next);
        }

        return result;
    }

    // Parses concatenated parts.
    // Example: ab{c,d}ef
    private Set<String> parseTerm() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < s.length()
                && s.charAt(index) != '}'
                && s.charAt(index) != ',') {

            Set<String> next;

            if (s.charAt(index) == '{') {
                index++; // skip '{'

                next = parseExpression();

                index++; // skip '}'
            } else {
                // Single lowercase letter
                next = new HashSet<>();
                next.add(String.valueOf(s.charAt(index)));
                index++;
            }

            result = concatenate(result, next);
        }

        return result;
    }

    // Cartesian product of two sets using string concatenation.
    private Set<String> concatenate(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}