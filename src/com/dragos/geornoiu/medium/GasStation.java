package com.dragos.geornoiu.medium;

public class GasStation {

    /**
     * 134. Gas Station
     * https://leetcode.com/problems/gas-station/
     * <p>
     * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
     * <p>
     * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its
     * next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
     * <p>
     * Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the
     * circuit once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique
     * <p>
     * Example 1:
     * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
     * Output: 3
     * Explanation:
     * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
     * Travel to station 4. Your tank = 4 - 1 + 5 = 8
     * Travel to station 0. Your tank = 8 - 2 + 1 = 7
     * Travel to station 1. Your tank = 7 - 3 + 2 = 6
     * Travel to station 2. Your tank = 6 - 4 + 3 = 5
     * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
     * Therefore, return 3 as the starting index.
     * <p>
     * Example 2:
     * Input: gas = [2,3,4], cost = [3,4,3]
     * Output: -1
     * Explanation:
     * You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
     * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
     * Travel to station 0. Your tank = 4 - 3 + 2 = 3
     * Travel to station 1. Your tank = 3 - 3 + 3 = 3
     * You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
     * Therefore, you can't travel around the circuit once no matter where you start.
     * <p>
     * Constraints:
     * n == gas.length == cost.length
     * 1 <= n <= 10^5
     * 0 <= gas[i], cost[i] <= 10^4
     */
    public static void main(String[] args) {
        int[] gasInput1 = new int[]{1, 2, 3, 4, 5};
        int[] costInput1 = new int[]{3, 4, 5, 1, 2};
        int result1 = canCompleteCircuit(gasInput1, costInput1);
        System.out.println(result1);
        assert result1 == 3;

        int[] gasInput2 = new int[]{2, 3, 4};
        int[] costInput2 = new int[]{3, 4, 3};
        int result2 = canCompleteCircuit(gasInput2, costInput2);
        System.out.println(result2);
        assert result2 == -1;

        int[] gasInput3 = new int[]{5, 1, 2, 3, 4};
        int[] costInput3 = new int[]{4, 4, 1, 5, 1};
        int result3 = canCompleteCircuit(gasInput3, costInput3);
        System.out.println(result3);
        assert result3 == 4;


        int[] gasInput4 = new int[]{5, 8, 2, 8};
        int[] costInput4 = new int[]{6, 5, 6, 6};
        int result4 = canCompleteCircuit(gasInput4, costInput4);
        System.out.println(result4);
        assert result4 == 3;

    }

    // O(n) solution
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int startingStation = 0;
        int gasInTank = 0;
        int totalGas = 0;
        for (int i = 0; i < gas.length; i++) {
            gasInTank += gas[i] - cost[i];
            totalGas += gas[i] - cost[i];

            // If at any point of the traversal, the sum from the last starting point falls bellow zero,
            // we can conclude that none of the traversed indexes (gas station) can be a valid solution, since
            //
            // So the result will be the startingPosition for which all indexes to the right are reachable, meaning
            // gasInTank never falls bellow zero.
            // Because exactly one solution is guaranteed to exist, we can kinda' ignore the elements on the left with
            // the condition≈ that the sum of the entire array (gas[i] - cost[i]) is also zero or a positive number.
            if (gasInTank < 0) {
                gasInTank = 0;
                startingStation = i + 1;
            }
        }

        return totalGas < 0 ? -1 : startingStation;
    }
}
