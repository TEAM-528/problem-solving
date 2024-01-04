/**
 * [LeetCode] 8. String to Integer (atoi)
 * https://leetcode.com/problems/string-to-integer-atoi/description/
 *
 * 접근 방식
 * 1) 입력 값 문자열 길이가 최대 200이므로 굉장히 큰 수가 주어질 수 있다. 따라서 최대한 int 또는 long 타입 내에서 결과를 도출할 수 있어야한다.
 * 2) 공백, 부호, '0'을 건너뛰고 시작 index를 찾는다.
 * 3) 순차적으로 숫자를 읽으면서 결과를 계산한다.
 * 4) int 범위의 경계 값은 21억이므로 10자리 숫자이다. 따라서 10자리 수라면 9자리 숫자만 계산한 값이 `경계 값 / 10` 보다 크거나 같은지 확인한다.
 * 4-1) 만약 크다면 경계를 벗어나는 것으로 판단
 * 4-2) 만약 같다면 `경계 값 % 10`과 일의 자리 숫자를 비교하여, `경계 값 % 10`보다 크다면 경계를 벗어나는 것으로 판단
 * 5) 그 외의 값은 적절하게 처리한다.
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