/**
 * [LeetCode] 22. Generate Parentheses
 * https://leetcode.com/problems/generate-parentheses/description/
 *
 * 접근 방식
 * 1) 백트래킹으로 문제를 풀 수 있을 것 같았기 때문에 다음과 같이 파라미터를 설정했다.
 *  1-1) 총 문자열의 길이(depth), 문자열을 저장(sb), 문제에서 주어진 왼쪽(또는 오른쪽) 괄호 수(n)
 *  1-2) 왼쪽 괄호는 n번만 사용할 수 있기 때문에 현재까지 사용한 왼쪽 괄호 수(left)
 *  1-3) 오른쪽 괄호는 왼쪽 괄호가 닫히지 않았을 때만 사용할 수 있기 때문에, 닫히지 않은 왼쪽 괄호 수(count)
 * 2) 백트래킹을 진행하다가 총 문자열의 길이(depth)가 n * 2가 됐을 때 result에 해당 문자열을 추가한다.
 * 3) result 반환
 */

class Solution {

    static List<String> result;

    private void permutation(int depth, StringBuilder sb, int left, int count, int n) {
        if (depth == n * 2) {
            result.add(sb.toString());
            return;
        }
        if (left < n) {
            sb.append('(');
            permutation(depth + 1, sb, left + 1, count + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (count > 0) {
            sb.append(')');
            permutation(depth + 1, sb, left, count - 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<String> generateParenthesis(int n) {
        result = new ArrayList<>();
        permutation(0, new StringBuilder(), 0, 0, n);
        return result;
    }
}