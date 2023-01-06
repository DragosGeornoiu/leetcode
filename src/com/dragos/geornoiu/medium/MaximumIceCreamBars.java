package com.dragos.geornoiu.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumIceCreamBars {

    /**
     * 1833. Maximum Ice Cream Bars
     * https://leetcode.com/problems/maximum-ice-cream-bars/
     * <p>
     * It is a sweltering summer day, and a boy wants to buy some ice cream bars.
     * <p>
     * At the store, there are n ice cream bars. You are given an array costs of length n, where costs[i] is the price
     * of the ith ice cream bar in coins. The boy initially has coins coins to spend, and he wants to buy as many ice
     * cream bars as possible.
     * <p>
     * Return the maximum number of ice cream bars the boy can buy with coins coins.
     * <p>
     * Note: The boy can buy the ice cream bars in any order
     * <p>
     * Example 1:
     * Input: costs = [1,3,2,4,1], coins = 7
     * Output: 4
     * Explanation: The boy can buy ice cream bars at indices 0,1,2,4 for a total price of 1 + 3 + 2 + 1 = 7.
     * <p>
     * Example 2:
     * Input: costs = [10,6,8,7,7,8], coins = 5
     * Output: 0
     * Explanation: The boy cannot afford any of the ice cream bars.
     * <p>
     * Example 3:
     * Input: costs = [1,6,3,1,2,5], coins = 20
     * Output: 6
     * Explanation: The boy can buy all the ice cream bars for a total price of 1 + 6 + 3 + 1 + 2 + 5 = 18.
     * <p>
     * Constraints:
     * costs.length == n
     * 1 <= n <= 10^5
     * 1 <= costs[i] <= 10^5
     * 1 <= coins <= 10^8
     */
    public static void main(String[] args) {
        int result1 = maxIceCream(new int[]{1, 3, 2, 4, 1}, 7);
        System.out.println(result1);
        assert result1 == 4;

        int result2 = maxIceCream(new int[]{10, 6, 8, 7, 7, 8}, 5);
        System.out.println(result2);
        assert result2 == 0;

        int result3 = maxIceCream(new int[]{1, 6, 3, 1, 2, 5}, 20);
        System.out.println(result3);
        assert result3 == 6;
    }

    //Although not pretty, there is a O(n) solution for this problem.
    public static int maxIceCream(int[] costs, int coins) {
        int[] iceCreamCounter = new int[100001];
        for (int cost : costs) {
            iceCreamCounter[cost]++;
        }

        int maxNoOfIceCreamsToBuy = 0;
        for(int i=1; i<iceCreamCounter.length; i++) {
            if(iceCreamCounter[i] == 0) {
                continue;
            }

            if(coins / i < iceCreamCounter[i]) {
                maxNoOfIceCreamsToBuy += coins / i;
                return maxNoOfIceCreamsToBuy;
            } else {
                maxNoOfIceCreamsToBuy += iceCreamCounter[i];
                coins -= iceCreamCounter[i] * i;
            }
        }

        return maxNoOfIceCreamsToBuy;
    }

    // There is also a solution by using a priority queue which raises some inetersting points.
    //
    // From the Javadoc: this implementation provides O(log(n)) time for the enqueing and dequeing methods
    // (offer, poll, remove() and add); linear time for the remove(Object) and contains(Object) methods; and constant
    // time for the retrieval methods (peek, element, and size).
    //
    // So enqueing on a PriorityQueue takes O(log(n)), so creating this for n elements would make you think that
    // it will lead to O(n log(n)) for creating the queue, which would be the same as sorting the collection.
    // However this is not entirely true. Although creating a PriorityQueue element by element would lead to an
    // O(n log(n)) complexity, creating a PriorityQueue from an existing collection is only O(n).
    // Some explications for this can be read here: https://stackoverflow.com/questions/9755721/how-can-building-a-heap-be-on-time-complexity
    //
    // Creating the list from the cost array is O(n).
    // Creating the priorityQueue from the list is O(n)
    // The iteration (the while loop) is O(n)
    // The poll method on the priorityQueue is O(log(n))
    // So, if my understanding is correct, this solution results also in O(n log(n)) because of the poll insinde the
    // while loop, but interesting that a priorityQueue can be created in O(n).
    public static int maxIceCreamWithPriorityQueue(int[] costs, int coins) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Arrays.stream(costs).boxed().toList());
        int noOfIceCreams = 0;
        while (coins > 0 && priorityQueue.peek() != null) {
            int cost = priorityQueue.poll();
            if (coins - cost >= 0) {
                coins -= cost;
                noOfIceCreams += 1;
            } else {
                return noOfIceCreams;
            }
        }


        return noOfIceCreams;
    }

    // Time complexity is O(n log(n)) for the sorting (Arrays.sort uses a variation of quickSort when dealing with
    // primitives and quicksort is O(n log(n))
    // And the interation is also O(n)
    // Resulting in O(n log(n)) complexity
    public static int maxIceCreamMySolution(int[] costs, int coins) {
        Arrays.sort(costs);

        int noOfIceCreams = 0;
        for (int cost : costs) {
            if (coins >= cost) {
                noOfIceCreams++;
                coins -= cost;
            } else {
                break;
            }
        }

        return noOfIceCreams;
    }
}
