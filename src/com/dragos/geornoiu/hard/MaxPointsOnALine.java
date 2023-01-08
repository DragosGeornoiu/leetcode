package com.dragos.geornoiu.hard;

import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnALine {

    /**
     * 149. Max Points on a Line
     * https://leetcode.com/problems/max-points-on-a-line/
     * <p>
     * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum
     * number of points that lie on the same straight line.
     * <p>
     * Example 1:
     * Input: points = [[1,1],[2,2],[3,3]]
     * Output: 3
     * <p>
     * Example 2:
     * Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
     * Output: 4
     * <p>
     * Constraints:
     * 1 <= points.length <= 300
     * points[i].length == 2
     * -10^4 <= xi, yi <= 10^4
     * All the points are unique.
     */
    public static void main(String[] args) {
        int[][] points1 = new int[][] {{1,1},{2,2},{3,3}};
        int result1 = maxPoints(points1);
        System.out.println(result1);
        assert result1 == 3;

        int[][] points2 = new int[][] {{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};
        int result2 = maxPoints(points2);
        System.out.println(result2);
        assert result2 == 4;

        int[][] points3 = new int[][] {{0, 0}};
        int result3 = maxPoints(points3);
        System.out.println(result3);
        assert result3 == 1;
    }

    // Time complexity is O(n^2)
    // Formula for determining the slope between two points is: m = (y₂ - y₁) / (x₂ - x₁).
    public static int maxPoints(int[][] points) {
        if(points.length == 1) {
            return 1;
        }

        int maxNoOfPoints = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Double, Integer> slopeCounter = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (points[i] == points[j]) {
                    continue;
                }

                double slope = 0.0;
                if (points[j][0] - points[i][0] == 0) {
                    slope = Double.POSITIVE_INFINITY;
                } else {
                    slope = (points[j][1] - points[i][1]) / (double) (points[j][0] - points[i][0]);
                }

                int noOfPoints = slopeCounter.getOrDefault(slope, 1) + 1;
                slopeCounter.put(slope, noOfPoints);

                if (maxNoOfPoints < noOfPoints) {
                    maxNoOfPoints = noOfPoints;
                }
            }
        }

        return maxNoOfPoints;
    }
}
