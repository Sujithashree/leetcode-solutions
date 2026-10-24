/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private TreeNode first = null;
    private TreeNode second = null;
    private TreeNode prev = null;

    public void recoverTree(TreeNode root) {
        // 1. Find the two swapped nodes via in-order traversal
        inorder(root);
        
        // 2. Swap their values to restore the BST
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inorder(TreeNode curr) {
        if (curr == null) return;

        // Traverse left subtree
        inorder(curr.left);

        // Identify misplaced nodes (violation of strictly increasing order)
        if (prev != null && prev.val > curr.val) {
            if (first == null) {
                first = prev; // Capture the first node from the first violation
            }
            second = curr;    // Capture/update the second node
        }
        prev = curr; // Move prev pointer forward

        // Traverse right subtree
        inorder(curr.right);
    }
}
