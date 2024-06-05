const fs = require("fs");
const N = +fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim();

const dp = new Array(31).fill(0);

dp[0] = 1;
dp[2] = 3;

for (let i = 4; i < dp.length; i += 2) {
  dp[i] = dp[i - 2] * 3;
  for (let j = 4; j <= i; j += 2) {
    dp[i] += dp[i - j] * 2;
  }
}

console.log(dp[N]);
