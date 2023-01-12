package com.dragos.geornoiu.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfNodesInTheSubTreeWithTheSameLabel {

    /**
     * 1519. Number of Nodes in the Sub-Tree With the Same Label
     * https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/description/
     * <p>
     * You are given a tree (i.e. a connected, undirected graph that has no cycles) consisting of n nodes numbered from
     * 0 to n - 1 and exactly n - 1 edges. The root of the tree is the node 0, and each node of the tree has a label
     * which is a lower-case character given in the string labels (i.e. The node with the number i has the label
     * labels[i]).
     * <p>
     * The edges array is given on the form edges[i] = [ai, bi], which means there is an edge between nodes ai and bi
     * in the tree.
     * <p>
     * Return an array of size n where ans[i] is the number of nodes in the subtree of the ith node which have the
     * same label as node i.
     * <p>
     * A subtree of a tree T is the tree consisting of a node in T and all of its descendant nodes.
     * <p>
     * Example 1:
     * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], labels = "abaedcd"
     * Output: [2,1,1,1,1,1,1]
     * Explanation: Node 0 has label 'a' and its sub-tree has node 2 with label 'a' as well, thus the answer is 2.
     * Notice that any node is part of its sub-tree.
     * Node 1 has a label 'b'. The sub-tree of node 1 contains nodes 1,4 and 5, as nodes 4 and 5 have different labels
     * than node 1, the answer is just 1 (the node itself).
     * <p>
     * Example 2:
     * Input: n = 4, edges = [[0,1],[1,2],[0,3]], labels = "bbbb"
     * Output: [4,2,1,1]
     * Explanation: The sub-tree of node 2 contains only node 2, so the answer is 1.
     * The sub-tree of node 3 contains only node 3, so the answer is 1.
     * The sub-tree of node 1 contains nodes 1 and 2, both have label 'b', thus the answer is 2.
     * The sub-tree of node 0 contains nodes 0, 1, 2 and 3, all with label 'b', thus the answer is 4.
     * <p>
     * Example 3:
     * Input: n = 5, edges = [[0,1],[0,2],[1,3],[0,4]], labels = "aabab"
     * Output: [3,2,1,1,1]
     * <p>
     * Constraints:
     * 1 <= n <= 10^5
     * edges.length == n - 1
     * edges[i].length == 2
     * 0 <= ai, bi < n
     * ai != bi
     * labels.length == n
     * labels is consisting of only of lowercase English letters.
     */
    public static void main(String[] args) {
        int n = 4;
        int[][] edges = new int[][]{{0, 1}, {1, 2}, {0, 3}};
        String labels = "bbbb";
        int[] result = countSubTrees(n, edges, labels);
        System.out.println(Arrays.toString(result));
        assert Arrays.equals(result, new int[]{4, 2, 1, 1});
    }

    private static int[] answer;

    public static int[] countSubTrees(int n, int[][] edges, String labels) {
        answer = new int[n];
        List<List<Integer>> adjacencyList = createAdjacencyList(n, edges);
        traverseRecursively(-1, 0, adjacencyList, labels);
        return answer;
    }

    private static int[] traverseRecursively(int previousNode, int currentIndex, List<List<Integer>> adjacencyList, String labels) {
        int[] frequency = new int[26]; //26 letters
        List<Integer> listOfNeighboursForCurrentIndex = adjacencyList.get(currentIndex);
        for (int neighbour : listOfNeighboursForCurrentIndex) {
            if (neighbour == previousNode) {
                continue;
            }

            int[] subtreeResults = traverseRecursively(currentIndex, neighbour, adjacencyList, labels);
            for (int j = 0; j < subtreeResults.length; j++) {
                frequency[j] += subtreeResults[j];
            }

        }

        answer[currentIndex] = ++frequency[labels.charAt(currentIndex) - 'a'];
        return frequency;
    }

    private static List<List<Integer>> createAdjacencyList(int n, int[][] edges) {
        List<List<Integer>> adjacencyList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }

        return adjacencyList;
    }
}
