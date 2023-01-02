package com.dragos.geornoiu.strings;

import java.util.HashMap;
import java.util.Map;

public class DetectCapital {

    /**
     * 520. Detect Capital
     * https://leetcode.com/problems/detect-capital/
     *
     * We define the usage of capitals in a word to be right when one of the following cases holds:
     * All letters in this word are capitals, like "USA".
     * All letters in this word are not capitals, like "leetcode".
     * Only the first letter in this word is capital, like "Google".
     *
     * Given a string word, return true if the usage of capitals in it is right.
     *
     * Example 1:
     * Input: word = "USA"
     * Output: true
     *
     * Example 2:
     * Input: word = "FlaG"
     * Output: false
     *
     * Constraints:
     * 1 <= word.length <= 100
     * word consists of lowercase and uppercase English letters.
     */
    public static void main(String[] args){
      boolean result1 = detectCapitalUse("USA");
      System.out.println(result1);
      assert result1;

      boolean result2 = detectCapitalUse("FlaG");
      System.out.println(result2);
      assert !result2;

      boolean result3 = detectCapitalUse("leetcode");
      System.out.println(result3);
      assert result3;

      boolean result4 = detectCapitalUse("Google");
      System.out.println(result4);
      assert result4;

      boolean result5 = detectCapitalUse("aG");
      System.out.println(result5);
      assert !result5;

      boolean result6 = detectCapitalUse("DSADuD");
      System.out.println(result6);
      assert !result6;
    }

    //The solution has O(n) complexity and it cannot be done better as in order to validate the word, we have to
    //traverse it at least once
    //The logic here is based on the first two characters from the word, if both of them are upper cased, then all
    //chars of the word have to be upper-cased. Otherwise, we can ignore the first character, as it being lower-cased
    //or upper-cased doesn't influence anything, but all remaining chars have to be lower-cased.
    public static boolean detectCapitalUse(String word) {
        if(word.length() == 1) {
            return true;
        }

        char[] charArray = word.toCharArray();
        char firstIndexChar = charArray[0];
        char secondIndexChar = charArray[1];

        boolean shouldRemainingCharsBeLowercase = !Character.isUpperCase(firstIndexChar) ||
                !Character.isUpperCase(secondIndexChar);

        for(int i=1; i<charArray.length; i++) {
            if(shouldRemainingCharsBeLowercase && !Character.isLowerCase(charArray[i])) {
                return false;
            }

            if(!shouldRemainingCharsBeLowercase && !Character.isUpperCase(charArray[i])) {
                return false;
            }
        }

        return true;
    }
}
