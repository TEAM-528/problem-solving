/**
 * [LeetCode] 29. Divide Two Integers
 * https://leetcode.com/problems/divide-two-integers/description/
 *
 * 접근 방식
 * 1) 연산을 편하게 하기 위해 절댓값으로 진행한다.
 * 2) 주어진 dividend 값을 높은 자리 수부터 divisor로 나누기 위해 dividend를 오른쪽으로 31번 shift 연산한다.
 * 3) shift 연산한 값이 divisor보다 크거나 같은지 작은지 비교한다.
 *  3-1) 만약 크거나 같다면 나눌 수 있다는 뜻이므로, result에 1 << 31번한 값을 더하고, dividend 값에 div << 31번 한 값을 뺀다.
 * 4) 만약 작다면 나눌 수 없다는 뜻으로 이번엔 30번 shift 연산하고, 2 ~ 3번을 동일하게 진행한다.
 * 5) shift 연산이 0번으로 될 때까지 2 ~ 4번을 반복한다.
 * 5) 기존 dividend와 divisor 부호가 다르다면 음수 결과이므로 result 음수로 만들고 반환한다.
 */

class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == 1 << 31 && divisor == -1) {
            return (1 << 31) - 1;
        }
        int result = 0;
        int num = Math.abs(dividend);
        int div = Math.abs(divisor);
        for (int i=31; i>=0; i--) {
            if ((num >>> i) - div >= 0) {
                result += 1 << i;
                num -= div << i;
            }
        }
        return (dividend >= 0) == (divisor >= 0) ? result : -result;
    }
}