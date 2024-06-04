const fs = require("fs");
const N = +fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim();

const dp = new Array(N + 1).fill(0);
dp[2] = 1;
dp[3] = 1;

for (let i = 4; i <= N; i++) {
  let beforeMultiplyingThree = Infinity;
  let beforeMultiplyTwo = Infinity;

  if (i % 3 === 0) {
    beforeMultiplyingThree = dp[i / 3] + 1;
  }
  if (i % 2 === 0) {
    beforeMultiplyTwo = dp[i / 2] + 1;
  }
  const beforePlusOne = dp[i - 1] + 1;

  dp[i] = Math.min(beforeMultiplyingThree, beforeMultiplyTwo, beforePlusOne);
}

console.log(dp);
