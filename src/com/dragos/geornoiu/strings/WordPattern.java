package com.dragos.geornoiu.strings;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {

    /**
     * 290. Word Pattern
     * https://leetcode.com/problems/word-pattern/
     *
     * Given a pattern and a string s, find if s follows the same pattern.
     * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
     *
     * Example 1:
     * Input: pattern = "abba", s = "dog cat cat dog"
     * Output: true
     *
     * Example 2:
     * Input: pattern = "abba", s = "dog cat cat fish"
     * Output: false
     *
     * Example 3:
     * Input: pattern = "aaaa", s = "dog cat cat dog"
     * Output: false
     *
     * Constraints:
     * 1 <= pattern.length <= 300
     * pattern contains only lower-case English letters.
     * 1 <= s.length <= 3000
     * s contains only lowercase English letters and spaces ' '.
     * s does not contain any leading or trailing spaces.
     * All the words in s are separated by a single space.
     */
    public static void main(String[] args){
      String pattern1 = "abba";
      String string1 = "dog cat cat dog";
      boolean result1 = wordPattern(pattern1, string1);
      System.out.println(result1);
      assert result1;

      String pattern2 = "abba";
      String string2 = "dog cat cat fish";
      boolean result2 = wordPattern(pattern2, string2);
      System.out.println(result2);
      assert !result2;

      String pattern3 = "aaaa";
      String string3 = "dog cat cat dog";
      boolean result3 = wordPattern(pattern3, string3);
      System.out.println(result3);
      assert !result3;

      String pattern4 = "aaaa";
      String string4 = "dog dog dog dog";
      boolean result4 = wordPattern(pattern4, string4);
      System.out.println(result4);
      assert result4;

      String pattern5 = "aaaa";
      String string5 = "dog dog dog";
      boolean result5 = wordPattern(pattern5, string5);
      System.out.println(result5);
      assert !result5;

      String pattern6 = "abba";
      String string6 = "dog dog dog dog";
      boolean result6 = wordPattern(pattern6, string6);
      System.out.println(result6);
      assert !result6;
    }

    //The time complexity here is O(n^2). The for loop has a O(n) time complexity and inside the for loop we use
    //the containsValue() method which has a time complexity of O(n), since it also just interates over the values
    //of the map
    public static boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");

        if(words.length != pattern.length()) {
            return false;
        }

        Map<Character, String> characterToWord = new HashMap<>();
        char[] charsArray = pattern.toCharArray();
        for(int i = 0; i<charsArray.length; i++) {
            Character c = charsArray[i];
            String matchingWord = characterToWord.get(c);
            if(matchingWord == null && characterToWord.containsValue(words[i])) {
                return false;
            } else if(matchingWord != null && !matchingWord.equals(words[i])) {
                return false;
            } else {
                characterToWord.put(c, words[i]);
            }
        }

        return true;
    }
}
