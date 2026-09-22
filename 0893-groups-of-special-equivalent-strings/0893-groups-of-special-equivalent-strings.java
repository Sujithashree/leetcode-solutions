import java.util.*;

class Solution {
    public int numSpecialEquivGroups(String[] words) {
        Set<String> groups = new HashSet<>();

        for (String word : words) {
            StringBuilder even = new StringBuilder();
            StringBuilder odd = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                if (i % 2 == 0) {
                    even.append(word.charAt(i));
                } else {
                    odd.append(word.charAt(i));
                }
            }

            char[] evenChars = even.toString().toCharArray();
            char[] oddChars = odd.toString().toCharArray();

            Arrays.sort(evenChars);
            Arrays.sort(oddChars);

            String key = new String(evenChars) + "|" + new String(oddChars);

            groups.add(key);
        }

        return groups.size();
    }
}