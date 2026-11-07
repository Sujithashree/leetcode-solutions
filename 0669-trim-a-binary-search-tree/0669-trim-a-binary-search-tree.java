class Solution {
public TreeNode trimBST(TreeNode root, int low, int high) {

    // Base case
    if (root == null) {
        return null;
    }
    // Case 1: Node value is smaller than low
    if (root.val < low) {
        return trimBST(root.right, low, high);
    }
    // Case 2: Node value is greater than high
    if (root.val > high) {
        return trimBST(root.left, low, high);
    }
    // Case 3: Node is within the range
    root.left = trimBST(root.left, low, high);
    root.right = trimBST(root.right, low, high);
    return root;
}

}