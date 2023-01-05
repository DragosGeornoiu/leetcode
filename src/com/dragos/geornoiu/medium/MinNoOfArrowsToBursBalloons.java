package com.dragos.geornoiu.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MinNoOfArrowsToBursBalloons {

    /**
     * 452. Minimum Number of Arrows to Burst Balloons
     * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
     * <p>
     * There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are
     * represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal
     * diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.
     * <p>
     * Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis.
     * A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the
     * number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
     * <p>
     * Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
     * <p>
     * Example 1:
     * Input: points = [[10,16],[2,8],[1,6],[7,12]]
     * Output: 2
     * Explanation: The balloons can be burst by 2 arrows:
     * - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
     * - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
     * <p>
     * Example 2:
     * Input: points = [[1,2],[3,4],[5,6],[7,8]]
     * Output: 4
     * Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.
     * <p>
     * Example 3:
     * Input: points = [[1,2],[2,3],[3,4],[4,5]]
     * Output: 2
     * Explanation: The balloons can be burst by 2 arrows:
     * - Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
     * - Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
     * <p>
     * Constraints:
     * 1 <= points.length <= 105
     * points[i].length == 2
     * -2^31 <= xstart < xend <= 2^31 - 1
     */
    public static void main(String[] args) {
        int[][] input1 = new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        int result1 = findMinArrowShots(input1);
        System.out.println(result1);
        assert result1 == 2;

        int[][] input2 = new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        int result2 = findMinArrowShots(input2);
        System.out.println(result2);
        assert result2 == 4;

        int[][] input3 = new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        int result3 = findMinArrowShots(input3);
        System.out.println(result3);
        assert result3 == 2;

        int[][] input4 = new int[][]{{3, 9}, {7, 12}, {3, 8}, {6, 8}, {9, 10}, {2, 9}, {0, 9}, {3, 9}, {0, 6}, {2, 8}};
        int result4 = findMinArrowShots(input4);
        System.out.println(result4);
        assert result4 == 2;

        int[][] input5 = new int[][]{};
        int result5 = findMinArrowShots(input5);
        System.out.println(result5);
        assert result5 == 0;

        int[][] input6 = new int[][]{{-2147483646, -2147483645}, {2147483646, 2147483647}};
        int result6 = findMinArrowShots(input6);
        System.out.println(result6);
        assert result6 == 2;

        int[][] input7 = new int[][]{{1, 2}};
        int result7 = findMinArrowShots(input7);
        System.out.println(result7);
        assert result7 == 1;

        int[][] input8 = new int[][]{{2, 3}, {2, 3}};
        int result8 = findMinArrowShots(input8);
        System.out.println(result8);
        assert result8 == 1;

        int[][] input9 = new int[][]{{0, 9}, {0, 1}, {0,6}};
        int result9 = findMinArrowShots(input9);
        System.out.println(result9);
        assert result9 == 1;
    }

    // We need to find the overlapping diameter range.
    // This approach sorts according to end of interval (points[][1]), not start of interval (≈points[][0])
    // We now only need to check which all balloons have start value less than or equal to the current balloon's end.
    // All these balloons can be popped by the same arrow.
    // When we encounter a balloon with start value greater than the current's end we simple make that balloon our
    // current balloon and increment the ans variable.
    // Time complexity is O(nlogn) for the sorting algorithm and O(n) for the iterating over the sorted array,
    // resulting in O(nlogn)
    public static int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(o -> o[1])); //Sorting according to end

        int ans = 1;
        int bound = points[0][1];

        for(int i = 1; i<points.length; i++){
            //checking for non-overlapping range
            if(points[i][0] > bound){
                bound = points[i][1];
                ans++;
            }
        }
        return ans;
    }
    // Sort the array in order of increasing Start / Left position.
    // This is an example of how the sorted array would look like
    // 0        9
    // 01
    // 0     6
    //   2      9
    //   2     8
    //    3     9
    //    3    8
    //       6 8
    //        7      12
    //          910
    // We can think of this solution as starting to shoot the arrows from the right.
    // So we keep the number of times the right needs to increase in order to accommodate for a new interval.
    // we compare the currentRight to the left (or start) of the next balloon interval and when the currentRight
    // is lower, we have determined that we will need a new arrow in order to shoot at the encountered interval.
    // And repeat.
    public static int findMinArrowShotsSortingByLeft(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));


        int currRight = points[0][1];
        int count = 1;

        for (int i = 1; i < points.length; i++) {
            if (currRight >= points[i][0]) {
                currRight = Math.min(currRight, points[i][1]);
            } else {
                count++;
                currRight = points[i][1];
            }
        }
        return count;
    }

    // Approach was to go trough the intervals of each baloon and if I find a position at which the current interval
    // intersects with an existing interval, I will set the existing interval to be only the common part of the two
    // intervals.
    //
    // Issue 1: Did not realize that I needed to have the initial array sorted as the following input would give an
    // extra value:
    // int[][] input4 = new int[][]{{3, 9}, {7, 12}, {3, 8}, {6, 8}, {9, 10}, {2, 9}, {0, 9}, {3, 9}, {0, 6}, {2, 8}};
    //
    // Issue 2: Use a bubble sort which resulted in exceeding max time execution.
    // The time complexity for a bubble sort is O(n^2)
    // The time complexity for Arrays.sort varies as it can use different implementations.
    //      Arrays.sort uses two sorting algorithms. One is a modification of Quicksort named dual-pivot quicksort,
    //      the other an adaptation of MergeSort named Timsort. Both have a time complexity of O(n log n), where n is
    //      the total number of items in the array.
    //
    // Sorting the array is O(nlogn) and because we have to traverse the array and also travers the existing
    // intersection, this would be a O(n*m) where m <= n and n is the total number intervals (or points), while m is
    // the not intersecting intervals.
    public static int findMinArrowShotsIntervalList(int[][] points) {
        //sortArray
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));

        List<List<Integer>> existingIntersections = new ArrayList<>();
        for (int[] point : points) {
            int leftEdge = point[0];
            int rightEdge = point[1];

            boolean foundIntersection = false;
            for (int j = 0; j < existingIntersections.size(); j++) {
                int existingIntersectionsLeftEdge = existingIntersections.get(j).get(0);
                int existingIntersectionsRightEdge = existingIntersections.get(j).get(1);

                if ((leftEdge <= existingIntersectionsLeftEdge && existingIntersectionsLeftEdge <= rightEdge) || (existingIntersectionsLeftEdge <= leftEdge && leftEdge <= existingIntersectionsRightEdge)) {
                    foundIntersection = true;
                    int newLeftEdge = Math.max(leftEdge, existingIntersectionsLeftEdge);
                    int newRightEdge = Math.min(rightEdge, existingIntersectionsRightEdge);
                    existingIntersections.set(j, List.of(newLeftEdge, newRightEdge));
                    break;
                }
            }

            if (!foundIntersection) {
                existingIntersections.add(List.of(leftEdge, rightEdge));
            }
        }

        System.out.println(existingIntersections);
        return existingIntersections.size();
    }
}
