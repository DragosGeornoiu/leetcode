package com.dragos.geornoiu.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreorderTraversal {
    /**
     * 144. Binary Tree Preorder Traversal
     * https://leetcode.com/problems/binary-tree-preorder-traversal/
     * <p>
     * Given the root of a binary tree, return the preorder traversal of its nodes' values.
     * <p>
     * Example 1:
     * Input: root = [1,null,2,3]
     * Output: [1,2,3]
     * <p>
     * Example 2:
     * Input: root = []
     * Output: []
     * <p>
     * Example 3:
     * Input: root = [1]
     * Output: [1]
     * <p>
     * <p>
     * Constraints:
     * The number of nodes in the tree is in the range [0, 100].
     * -100 <= Node.val <= 100
     */
    public static void main(String[] args) {
        TreeNode treeNode3 = new TreeNode(3, null, null);
        TreeNode treeNode2 = new TreeNode(2, treeNode3, null);
        TreeNode treeNode1 = new TreeNode(1, null, treeNode2);
        List<Integer> result1 = preorderTraversalIterative(treeNode1);
        System.out.println(result1);
        assert result1.equals(List.of(1,2,3));

        List<Integer> result2 = preorderTraversalIterative(null);
        System.out.println(result2);
        assert result2.isEmpty();

        List<Integer> result3 = preorderTraversalIterative(new TreeNode(1, null, null));
        System.out.println(result3);
        assert result3.equals(List.of(1));
    }

    // Recursive solution
    public static List<Integer> preorderTraversalRecursive(TreeNode root) {
        List<Integer> traversedNodes = new ArrayList<>();
        preorderTraversalRecursive(root, traversedNodes);
        return traversedNodes;
    }

    // Iterative solution
    public static List<Integer> preorderTraversalIterative(TreeNode node) {
        List<Integer> traversedNodes = new ArrayList<>();
        if (node == null) {
            return traversedNodes;
        }

        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(node);

        while (!nodeStack.empty()) {

            // Pop the top item from stack and print it
            TreeNode currentNode = nodeStack.peek();
            traversedNodes.add(currentNode.val);
            nodeStack.pop();

            // Push right and left children of the popped node to stack
            if (currentNode.right != null) {
                nodeStack.push(currentNode.right);
            }
            if (currentNode.left != null) {
                nodeStack.push(currentNode.left);
            }
        }

        return traversedNodes;
    }

    public static void preorderTraversalRecursive(TreeNode node, List<Integer> traversedNodes) {
        if(node == null) {
            return;
        }

        traversedNodes.add(node.val);
        preorderTraversalRecursive(node.left, traversedNodes);
        preorderTraversalRecursive(node.right, traversedNodes);
    }

    private static class TreeNode {
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
