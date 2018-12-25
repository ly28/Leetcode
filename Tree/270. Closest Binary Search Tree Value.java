/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // TreeNode prev = null;
    public int closestValue(TreeNode root, double target) {
        int res = root.val;
        while (root != null) {
            if (Math.abs(root.val - target) < Math.abs(res - target))
                res = root.val;
            root = root.val < target ? root.right : root .left;
        }
        return res;
    }
    
    public int closestValue1(TreeNode root, double target) {
        TreeNode prev = null;
        TreeNode curr = root;
        Stack<TreeNode> stk = new Stack<>();
        while (!stk.isEmpty() || curr != null) {
            while (curr != null) {
                stk.push(curr);
                curr = curr.left;
            }
            curr = stk.pop();
            if (curr.val > target) {
                if (prev == null) return curr.val;
                return Math.abs(prev.val - target) < Math.abs(curr.val - target) ? prev.val : curr.val;
            }
            prev = curr;
            curr = curr.right;
        }
        return prev.val;
    }
}