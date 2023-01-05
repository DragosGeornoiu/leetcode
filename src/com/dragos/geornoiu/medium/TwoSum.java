package com.dragos.geornoiu.medium;

public class TwoSum {
    /**
     * 167. Two Sum II - Input Array Is Sorted
     * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
     *
     * <p>
     * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers
     * such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2]
     * where 1 <= index1 < index2 <= numbers.length.
     * <p>
     * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of
     * length 2.
     * <p>
     * The tests are generated such that there is exactly one solution. You may not use the same element twice.
     * <p>
     * Your solution must use only constant extra space.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: numbers = [2,7,11,15], target = 9
     * Output: [1,2]
     * Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
     * Example 2:
     * <p>
     * Input: numbers = [2,3,4], target = 6
     * Output: [1,3]
     * Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
     * Example 3:
     * <p>
     * Input: numbers = [-1,0], target = -1
     * Output: [1,2]
     * Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 2 <= numbers.length <= 3 * 104
     * -1000 <= numbers[i] <= 1000
     * numbers is sorted in non-decreasing order.
     * -1000 <= target <= 1000
     * The tests are generated such that there is exactly one solution.
     */
    public static void main(String[] args) {
        // test case 1
        int[] numbers = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] results = twoSumBinarySearch(numbers, target);
        assert results[0] == 1;
        assert results[1] == 2;

        int[] numbers2 = new int[]{2, 3, 4};
        int target2 = 6;
        int[] results2 = twoSumBinarySearch(numbers2, target2);
        assert results2[0] == 1;
        assert results2[1] == 3;

        int[] numbers3 = new int[]{-1, 0};
        int target3 = -1;
        int[] results3 = twoSumBinarySearch(numbers3, target3);
        assert results3[0] == 1;
        assert results3[1] == 2;
    }

    //binary search approach
    //we have O(n) for the for loop and a O(logn) for the binary search, so this results in a O(nlogn)
    //basically we iterate over the array and do a binary search on the remaining elements (elements on the right of current iteration)
    private static int[] twoSumBinarySearch(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; ++i) {
            int left = i + 1;
            int right = numbers.length - 1;
            while (left <= right) {
                int mid = (right - left) / 2 + left;
                if (numbers[mid] + numbers[i] == target) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] + numbers[i] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return new int[]{-1, -1};
    }

    //two pointers approach
    //Interestingly, there is an O(n) solution using two pointers which seems faster on many cases.
    //Starting at the beginning and end of the array, and increasing the left pointer when the sum is lower than the
    //target, and decreasing the right pointer when≈ the sum is higher than the target
    private static int[] twoSumTwoPointers(int[] numbers, int target) {
        int leftPointer = 0;
        int rightPointer = numbers.length - 1;
        while (leftPointer < rightPointer) {
            if (numbers[leftPointer] + numbers[rightPointer] > target) {
                rightPointer--;
            } else if (numbers[leftPointer] + numbers[rightPointer] < target) {
                leftPointer++;
            } else {
                return new int[]{leftPointer + 1, rightPointer + 1};
            }
        }

        return new int[]{-1, -1};
    }


    //brute force approach
    //two for loops would mean O(n^2), but it would be a little better with the condition to stop when
    //numbers[i] + numbers[j] > target
    private static int[] twoSumBruteForce(int[] numbers, int target) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] > target) {
                    break;
                }

                if (numbers[i] + numbers[j] == target) {
                    return new int[]{i, j};
                }
            }

        }

        return new int[]{-1, -1};
    }
}
