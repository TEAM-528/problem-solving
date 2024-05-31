class Solution {
    public int M, N;
    public int[][] keyG;
    public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;
        keyG = key;

        for(int i=0; i<4; i++) {
            keyG = rotate();
            int[][] extKey = extendKey();
            for(int j=0; j<extKey.length-lock.length+1; j++) {
                for(int k=0; k<extKey[0].length-lock.length+1; k++) {
                    if (check(lock, extKey, j, k)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int[][] extendKey() {
        int length = 2*N-2+M;
        int[][] newMap = new int[length][length];

        for(int i=0; i<keyG.length; i++) {
            for(int j=0; j<keyG[0].length; j++) {
                newMap[i+N-1][j+N-1] = keyG[i][j];
            }
        }

        return newMap;
    }

    public int[][] rotate() { // 시계 방향으로 돌리기
        int[][] newMap = new int[keyG.length][keyG[0].length];

        for(int i=0; i<keyG.length; i++) {
            for(int j=0; j<keyG[0].length; j++) {
                newMap[j][keyG.length-i-1] = keyG[i][j];
            }
        }

        return newMap;
    }

    public boolean check(int[][] lock, int[][] extKey, int x, int y) {
        for(int i=0; i<lock.length; i++) {
            for(int j=0; j<lock[0].length; j++) {
                if (lock[i][j]==extKey[i+x][j+y]) {
                    return false;
                }
            }
        }
        return true;
    }
}