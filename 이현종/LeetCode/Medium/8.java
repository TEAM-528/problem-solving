/**
 * LeetCode
 * 문제: 8. String to Integer (atoi)
 */

class Solution {
    public int myAtoi(String s) {
        int total = 0;
        int pointer = 0;
        boolean sign = false;
        while (pointer < s.length() && s.charAt(pointer) == ' ') {
            pointer++;
        }
        if (pointer < s.length() &&
                (s.charAt(pointer) == '+' || s.charAt(pointer) == '-')) {
            sign = s.charAt(pointer) == '+' ? false : true;
            pointer++;
        }
        while (pointer < s.length() && s.charAt(pointer) == '0') {
            pointer++;
        }
        if (pointer == s.length()) {
            return 0;
        }
        int startIdx = pointer;
        int onesPlace = Math.abs((sign ? Integer.MIN_VALUE : Integer.MAX_VALUE) % 10);
        while (pointer < s.length() && Character.isDigit(s.charAt(pointer))) {
            if (pointer - startIdx >= 10) {
                return sign ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            if (pointer - startIdx == 9) {
                if (Integer.MAX_VALUE / 10 < total ||
                        (Integer.MAX_VALUE / 10 == total && s.charAt(pointer) - '0' > onesPlace)) {
                    return sign ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
            }
            total = total * 10 + (s.charAt(pointer) - '0');
            pointer++;
        }
        return sign ? total * -1 : total;
    }
}