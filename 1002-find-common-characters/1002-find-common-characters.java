class Solution {
    public List<String> commonChars(String[] words) {
        List<String> result = new ArrayList<>();

        // Frequency of characters in the first word
        int[] minFreq = new int[26];

        for (char c : words[0].toCharArray()) {
            minFreq[c - 'a']++;
        }

        // Find minimum frequency across all words
        for (int i = 1; i < words.length; i++) {
            int[] freq = new int[26];

            for (char c : words[i].toCharArray()) {
                freq[c - 'a']++;
            }

            for (int j = 0; j < 26; j++) {
                minFreq[j] = Math.min(minFreq[j], freq[j]);
            }
        }

        // Build the result
        for (int i = 0; i < 26; i++) {
            while (minFreq[i] > 0) {
                result.add(String.valueOf((char) ('a' + i)));
                minFreq[i]--;
            }
        }

        return result;
    }
}