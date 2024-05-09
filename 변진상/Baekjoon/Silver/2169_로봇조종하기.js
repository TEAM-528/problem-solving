const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => ele.split(" ").map((ele) => +ele));

const [N, M] = stdin[0];
const map = stdin.slice(1);

const dp = Array.from({ length: N }, () => Array.from({ length: M }, () => 0));

for (let i = 0; i < N; i++) {
  let startRow = 0;
  let tmpRightRow = new Array(M).fill(0);
  let tmpLeftRow = new Array(M).fill(0);

  for (let j = 0; j < M; j++) {
    if (i === 0) {
      startRow += map[i][j];
      dp[i][j] = startRow;
    } else {
      if (j === 0) {
        tmpRightRow[j] = map[i][j] + dp[i - 1][j];
        tmpLeftRow[M - j - 1] = map[i][M - j - 1] + dp[i - 1][M - j - 1];
      } else {
        tmpRightRow[j] = Math.max(
          tmpRightRow[j - 1] + map[i][j],
          dp[i - 1][j] + map[i][j]
        );

        tmpLeftRow[M - j - 1] = Math.max(
          tmpLeftRow[M - j] + map[i][M - j - 1],
          dp[i - 1][M - j - 1] + map[i][M - j - 1]
        );
      }
    }
  }
  for (let k = 0; k < M; k++) {
    if (i === 0) break;
    dp[i][k] = Math.max(tmpLeftRow[k], tmpRightRow[k]);
  }
}

console.log(dp[N - 1][M - 1]);
