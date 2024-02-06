/**
 * [LeetCode] 54. Spiral Matrix
 * https://leetcode.com/problems/spiral-matrix/description/
 *
 * 접근 방식
 * 1) 우, 하, 좌, 상 방향으로 반복해서 순회하도록 구성한다.
 *  1-1) 이때 matrix 배열의 행 길이를 N, 열 길이를 M이라고 가정하고 (-1, 0)에서 시작하면
 *       우로 N번 탐색, 하로 M-1번 탐색, 좌로 N-1번 탐색, 상으로 M-2번 탐색하는 것을 알 수 있다.
 *  1-2) 따라서 행 또는 열을 탐색하면 다음 행 또는 열을 탐색할 때 1번 덜 탐색하는 것을 알 수 있다.
 * 2) 탐색한 원소들을 순차적으로 result 리스트에 담아서 반환한다.
 */

class Solution {

    int N, M, a, b;
    boolean check;
    List<Integer> result;

    private void iterateRow(int[][] matrix) {
        b = (check) ? b + 1 : b - 1;
        if (check) {
            for (int j=b; j<b+M; j++) {
                result.add(matrix[a][j]);
            }
            b = b + M - 1;
        } else {
            for (int j=b; j>b-M; j--) {
                result.add(matrix[a][j]);
            }
            b = b - M + 1;
        }
    }

    private void iterateColumn(int[][] matrix) {
        a = (check) ? a + 1 : a - 1;
        if (check) {
            for (int i=a; i<a+N; i++) {
                result.add(matrix[i][b]);
            }
            a = a + N - 1;
        } else {
            for (int i=a; i>a-N; i--) {
                result.add(matrix[i][b]);
            }
            a = a - N + 1;
        }
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        result = new ArrayList<>();
        N = matrix.length - 1;
        M = matrix[0].length;
        check = true;
        a = 0;
        b = -1;
        while (N > 0 || M > 0) {
            if (M <= 0) {
                break;
            }
            iterateRow(matrix);
            M--;
            if (N <= 0) {
                break;
            }
            iterateColumn(matrix);
            N--;
            check = !check;
        }
        return result;
    }
}