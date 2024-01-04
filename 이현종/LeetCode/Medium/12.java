/**
 * [LeetCode] 12. Integer to Roman
 * https://leetcode.com/problems/integer-to-roman/
 * 
 * 접근 방식
 * 1) 사전에 문제에서 주어진 symbol에 해당하는 값을 선언해둔다.
 * 2) 큰 symbol부터 순차적으로 탐색한다.
 *  2-1) 만약 num 값이 해당 symbol 값보다 더 크거나 같다면, result에 해당 symbol을 추가하고 num 값을 줄인다.
 */

class Solution {

    static String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    static int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        for (int i=0; i<values.length; i++) {
            while (num >= values[i]) {
                result.append(symbols[i]);
                num -= values[i];
            }
        }
        return result.toString();
    }
}