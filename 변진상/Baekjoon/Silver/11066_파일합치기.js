const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const caseNum = +stdin[0];
const caseArr = stdin.slice(1);

for (let i = 0; i < caseNum; i++) {
  // 케이스 별로 나누기 위해
  const chapterNum = +caseArr[i * 2];
  const costArr = caseArr[i * 2 + 1].split(" ").map((ele) => +ele);

  const dp = Array.from({ length: chapterNum }, () =>
    new Array(chapterNum).fill(0)
  );

  for (let i = 1; i < chapterNum; i++) {
    dp[i - 1][i] = costArr[i] + costArr[i - 1];
  }

  for (let i = 0; i < chapterNum; i++) {
    for (let j = i + 2; j < chapterNum; j++) {
      dp[i][j] = dp[i][j - 1] + costArr[j];
    }
  }

  for (let i = 2; i < chapterNum; i++) {
    for (let cnt = 0; cnt < chapterNum - i; cnt++) {
      const [y, x] = [cnt, cnt + i];
      let minValue = Infinity;
      for (let dpX = x - i; dpX < x; dpX++) {
        minValue = Math.min(minValue, dp[y][dpX] + dp[dpX + 1][x]);
      }

      dp[y][x] = dp[y][x] + minValue;
    }
  }

  console.log(dp[0][chapterNum - 1]);
}
