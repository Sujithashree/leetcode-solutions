class Solution {
public int sumOfLeftLeaves(TreeNode root) {

    if (root == null) {
        return 0;
    }
    int sum = 0;
    // Check whether left child is a leaf
    if (root.left != null &&
        root.left.left == null &&
        root.left.right == null) {
        sum += root.left.val;
    }
    // Recursively check left and right subtrees
    sum += sumOfLeftLeaves(root.left);
    sum += sumOfLeftLeaves(root.right);
    return sum;
}

}