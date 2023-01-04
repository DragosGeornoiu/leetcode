package com.dragos.geornoiu.arrays.unsorted;

import java.util.HashMap;
import java.util.Map;

public class MinimumRounds {

    /**
     * 2244. Minimum Rounds to Complete All Tasks
     * https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks/
     * <p>
     * You are given a 0-indexed integer array tasks, where tasks[i] represents the difficulty level of a task.
     * In each round, you can complete either 2 or 3 tasks of the same difficulty level.
     * <p>
     * Return the minimum rounds required to complete all the tasks, or -1 if it is not possible to complete all the tasks.
     * <p>
     * Example 1:
     * Input: tasks = [2,2,3,3,2,4,4,4,4,4]
     * Output: 4
     * Explanation: To complete all the tasks, a possible plan is:
     * - In the first round, you complete 3 tasks of difficulty level 2.
     * - In the second round, you complete 2 tasks of difficulty level 3.
     * - In the third round, you complete 3 tasks of difficulty level 4.
     * - In the fourth round, you complete 2 tasks of difficulty level 4.
     * It can be shown that all the tasks cannot be completed in fewer than 4 rounds, so the answer is 4.
     * <p>
     * Example 2:
     * Input: tasks = [2,3,3]
     * Output: -1
     * Explanation: There is only 1 task of difficulty level 2, but in each round, you can only complete either 2 or
     * 3 tasks of the same difficulty level. Hence, you cannot complete all the tasks, and the answer is -1.
     * <p>
     * <p>
     * Constraints:
     * 1 <= tasks.length <= 105
     * 1 <= tasks[i] <= 109
     */
    public static void main(String[] args) {
        int[] input1 = new int[]{2, 2, 3, 3, 2, 4, 4, 4, 4, 4};
        int result1 = minimumRounds(input1);
        System.out.println(result1);
        assert result1 == 4;

        int[] input2 = new int[]{2, 3, 3};
        int result2 = minimumRounds(input2);
        System.out.println(result2);
        assert result2 == -1;

        int[] input3 = new int[]{3, 3, 3, 3};
        int result3 = minimumRounds(input3);
        System.out.println(result3);
        assert result3 == 2;

        int[] input4 = new int[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
        int result4 = minimumRounds(input4);
        System.out.println(result4);
        assert result4 == 4;
    }


    // Time complexity here i O(n).
    // We have O(n) for traversing the array in order to see the number of tasks per difficulty.
    // And for iterating all the values of the hashmap we would need another O(k) where k is all unique elements in the
    // array, so k <= n. SO it would be O(2n) worst case, which is reduced to O(n)
    //
    // This is a Greedy Algorithm for "Activity Selection Problem".
    //
    // The solution to this problem is in the realization that any positive number is a sum of 2s and 3s, except for the
    // number 1. So first we group count all the tasks by difficulty using a Hashmap.
    // Afterwards we go trough all the counts and verify
    //      - if a task has a count of 1, we don't have a valid solution
    //      - all other cases can be resolved, as mentioned previously, every other number is a sum o 2s and 3s
    // Another way to look at the problem;
    //      If count = 1, not possible, return -1
    //      If count = 2, needs one 2-tasks
    //      If count = 3, needs one 3-tasks
    //      If count = 3k, count = 3 * k, needs k batches.
    //      If count = 3k + 1, count = 3 * (k - 1) + 2 + 2, needs k + 1 batches.
    //      If count = 3k + 2, count = 3 * k + 2, needs k + 1 batches.
    // So it would results in (count + 2) / 3 batch,
    private static int minimumRounds(int[] tasks) {
        HashMap<Integer, Integer> difficultyCount = new HashMap<>();
        for (int task : tasks) {
            difficultyCount.put(task, difficultyCount.getOrDefault(task, 0) + 1);
        }

        int minimumRounds = 0;
        for (int count : difficultyCount.values()) {
            if (count == 1) {
                return -1;
            }

            //this is the line that does all the magic
            minimumRounds += (count + 2) / 3;
        }

        return minimumRounds;
    }

    // The idea behind my approach here was correct, however I made more checks than it would of been needed.
    // See the previous solutions. Time complexity would of been the same
    private static int minimumRoundsMySolution(int[] tasks) {
        Map<Integer, Integer> taskDifficultyCounter = new HashMap<>();
        for (int task : tasks) {
            taskDifficultyCounter.put(task, taskDifficultyCounter.getOrDefault(task, 0) + 1);
        }

        int minimumRounds = 0;
        for (Integer taskCount : taskDifficultyCounter.values()) {
            if (taskCount == 1) {
                return -1;
            }

            ///can be done in rounds of 3 problems
            if (taskCount % 3 == 0) {
                return taskCount / 3;
            }

            //can be done in rounds of 3 problems and one round of 2 problems.
            if (taskCount % 3 == 2) {
                return taskCount / 3 + 1;
            }

            //this can be done because the maximum of 2 days tasks is always 2
            int threeDaysTasks = taskCount / 3 - 1;
            int remainingTasks = taskCount - threeDaysTasks * 3;

            // the following if satement is not needed at all, since this case cannot happen at this point
            // if count was 18, it would of been divided by 3 perfectly to 6 rounds
            // if count was 19, it would of been divided into (19 / 3 - 1) = 5 rounds of 3 tasks, letting two more
            // rounds of 2 tasks
            // if counts was 20, it would determine (19 / 3 - 1) = 6 rounds of 3 tasks an one more round of 2 tasks
            // will be determined afteards
            if (remainingTasks % 2 == 1) {
                return -1;
            }

            int twoDaysTasks = remainingTasks / 2;
            minimumRounds += threeDaysTasks + twoDaysTasks;
        }

        return minimumRounds;
    }
}
