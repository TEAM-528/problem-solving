const fs = require("fs");

const N = +fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim();

const dp = new Array(N + 1).fill(0);

dp[1] = 1;
dp[2] = 3;

for (let i = 3; i < dp.length; i++) {
  dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 10007;
}

console.log(dp[N]);
