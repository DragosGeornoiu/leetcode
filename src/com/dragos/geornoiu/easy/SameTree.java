package com.dragos.geornoiu.easy;

public class SameTree {

    /**
     * 100. Same Tree
     * https://leetcode.com/problems/same-tree/
     * <p>
     * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
     * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
     * <p>
     * Example 1:
     * Input: p = [1,2,3], q = [1,2,3]
     * Output: true
     * <p>
     * Example 2:
     * Input: p = [1,2], q = [1,null,2]
     * Output: false
     * <p>
     * Example 3:
     * Input: p = [1,2,1], q = [1,1,2]
     * Output: false
     * <p>
     * Constraints:
     * The number of nodes in both trees is in the range [0, 100].
     * -10^4 <= Node.val <= 10^4
     */
    public static void main(String[] args) {
        TreeNode firstTreeNode3 = new TreeNode(3, null, null);
        TreeNode firstTreeNode2 = new TreeNode(2, null, null);
        TreeNode firstTreeNode1 = new TreeNode(1, firstTreeNode2, firstTreeNode3);
        TreeNode secondTreeNode3 = new TreeNode(3, null, null);
        TreeNode secondTreeNode2 = new TreeNode(2, null, null);
        TreeNode secondTreeNode1 = new TreeNode(1, secondTreeNode2, secondTreeNode3);
        boolean result1 = isSameTree(firstTreeNode1, secondTreeNode1);
        System.out.println(result1);
        assert result1;

        TreeNode test2FirstTreeNode2 = new TreeNode(2, null, null);
        TreeNode test2FirstTreeNode1 = new TreeNode(1, test2FirstTreeNode2, null);
        TreeNode test2SecondTreeNode2 = new TreeNode(2, null, null);
        TreeNode test2SecondTreeNode1 = new TreeNode(1, null, test2SecondTreeNode2);
        boolean result2 = isSameTree(test2FirstTreeNode1, test2SecondTreeNode1);
        System.out.println(result2);
        assert !result2;

        TreeNode thirdTestFirstTreeNode3 = new TreeNode(1, null, null);
        TreeNode thirdTestFirstTreeNode2 = new TreeNode(2, null, null);
        TreeNode thirdTestFirstTreeNode1 = new TreeNode(1, thirdTestFirstTreeNode2, thirdTestFirstTreeNode3);
        TreeNode thirdTestSecondTreeNode3 = new TreeNode(2, null, null);
        TreeNode thirdTestSecondTreeNode2 = new TreeNode(1, null, null);
        TreeNode thirdTestSecondTreeNode1 = new TreeNode(1, thirdTestSecondTreeNode2, thirdTestSecondTreeNode3);
        boolean result3 = isSameTree(thirdTestFirstTreeNode1, thirdTestSecondTreeNode1);
        System.out.println(result3);
        assert !result3;

        TreeNode fourthTestFirstTreeNode3 = new TreeNode(15, null, null);
        TreeNode fourthTestFirstTreeNode2 = new TreeNode(5, null, null);
        TreeNode fourthTestFirstTreeNode1 = new TreeNode(10, fourthTestFirstTreeNode2, fourthTestFirstTreeNode3);
        TreeNode fourthTestSecondTreeNode3 = new TreeNode(15, null, null);
        TreeNode fourthTestSecondTreeNode2 = new TreeNode(5, null, fourthTestSecondTreeNode3);
        TreeNode fourthTestSecondTreeNode1 = new TreeNode(10, fourthTestSecondTreeNode2, null);
        boolean result4 = isSameTree(fourthTestFirstTreeNode1, fourthTestSecondTreeNode1);
        System.out.println(result4);
        assert !result4;
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }
        if (!isSameTree(p.left, q.left)) {
            return false;
        }

        return isSameTree(p.right, q.right);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
