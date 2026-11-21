import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private List<Integer>[] origTree;
    private List<Integer>[] newTree;
    private String str;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        str = s;
        origTree = new ArrayList[n];
        newTree = new ArrayList[n];
        
        for (int i = 0; i < n; i++) {
            origTree[i] = new ArrayList<>();
            newTree[i] = new ArrayList<>();
        }
        
        // Build the original tree
        for (int i = 1; i < n; i++) {
            origTree[parent[i]].add(i);
        }

        int[] newParent = new int[n];
        newParent[0] = -1;
        
        // Array to keep track of the closest ancestor with a specific character
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        // DFS to determine the new parent for every node
        dfsFindParents(0, lastSeen, newParent, parent);

        // Build the new tree structure based on the updated parents
        for (int i = 1; i < n; i++) {
            newTree[newParent[i]].add(i);
        }

        // DFS to calculate the size of subtrees in the new tree
        int[] answer = new int[n];
        dfsCalculateSizes(0, answer);
        
        return answer;
    }

    private void dfsFindParents(int u, int[] lastSeen, int[] newParent, int[] parent) {
        int charIndex = str.charAt(u) - 'a';
        int previousAncestor = lastSeen[charIndex];

        if (u != 0) {
            // Update parent if a matching ancestor exists, else keep original parent
            newParent[u] = previousAncestor != -1 ? previousAncestor : parent[u];
        }

        // Update the closest ancestor for the current character to be 'u'
        lastSeen[charIndex] = u;

        for (int v : origTree[u]) {
            dfsFindParents(v, lastSeen, newParent, parent);
        }

        // Backtrack: restore the previous ancestor before going back up the tree
        lastSeen[charIndex] = previousAncestor;
    }

    private int dfsCalculateSizes(int u, int[] answer) {
        int size = 1; // Node u itself
        for (int v : newTree[u]) {
            size += dfsCalculateSizes(v, answer);
        }
        answer[u] = size;
        return size;
    }
}
