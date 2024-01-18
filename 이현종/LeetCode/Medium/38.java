/**
 * [LeetCode] 38. Count and Say
 * https://leetcode.com/problems/count-and-say/
 *
 * 접근 방식
 * 1) num과 count를 선언한다.
 *  1-1) 문자열을 읽어나가면서 이전 숫자와 동일하다면 count를 증가시킨다.
 *  1-2) 이전 숫자와 다르다면, 반환 할 result에 count와 num을 문자열에 붙여주고 num을 해당 숫자로 변경하고 count를 1로 선언한다.
 * 2) 1단계를 n번 진행한다.
 */

class Solution {

    private String getResult(String str) {
        int num = -1;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (num == -1) {
                num = c - '0';
                count++;
                continue;
            }
            if ((c - '0') == num) {
                count++;
                continue;
            }
            sb.append(count).append(num);
            num = c - '0';
            count = 1;
        }
        sb.append(count).append(num);
        return sb.toString();
    }

    public String countAndSay(int n) {
        int index = 2;
        String result = "1";
        while (index <= n) {
            result = getResult(result);
            index++;
        }
        return result;
    }
}