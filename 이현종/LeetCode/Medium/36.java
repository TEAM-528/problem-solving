/**
 * [LeetCode] 36. Valid Sudoku
 * https://leetcode.com/problems/valid-sudoku/
 *
 * 접근 방식
 * 1) 각 행과 열을 탐색하여 중복되는 숫자가 있는지 판단한다.
 * 2) 각 3x3 박스를 탐색하여 중복되는 숫자가 있는지 판단한다.
 * 3) 1번과 2번 모두 비트마스킹을 활용하여 중복 숫자를 판단한다.
 */

class Solution {
    private boolean validRow(char[][] board) {
        for (int i=0; i<board.length; i++) {
            int bitmask = 0;
            for (int j=0; j<board[0].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if ((bitmask & (1 << (board[i][j] - '0'))) != 0) {
                    return false;
                }
                bitmask |= (1 << (board[i][j] - '0'));
            }
        }
        return true;
    }

    private boolean validColumn(char[][] board) {
        for (int j=0; j<board[0].length; j++) {
            int bitmask = 0;
            for (int i=0; i<board.length; i++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if ((bitmask & (1 << (board[i][j] - '0'))) != 0) {
                    return false;
                }
                bitmask |= (1 << (board[i][j] - '0'));
            }
        }
        return true;
    }

    private boolean validSubBox(int a, int b, char[][] board) {
        int bitmask = 0;
        for (int i=a; i<a+3; i++) {
            for (int j=b; j<b+3; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if ((bitmask & (1 << (board[i][j] - '0'))) != 0) {
                    return false;
                }
                bitmask |= (1 << (board[i][j] - '0'));
            }
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board) {
        if (!validRow(board)) {
            return false;
        }
        if (!validColumn(board)) {
            return false;
        }
        for (int i=0; i<board.length; i+=3) {
            for (int j=0; j<board[0].length; j+=3) {
                if (!validSubBox(i, j, board)) {
                    return false;
                }
            }
        }
        return true;
    }
}