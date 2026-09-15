class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();

        if (s.length() < 10) {
            return result;
        }

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i <= s.length() - 10; i++) {
            String sequence = s.substring(i, i + 10);

            map.put(sequence, map.getOrDefault(sequence, 0) + 1);

            // Add only when it appears for the second time
            if (map.get(sequence) == 2) {
                result.add(sequence);
            }
        }

        return result;
    }
}