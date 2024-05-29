const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => +ele);

const [N, K] = stdin;
const dp = Array.from({ length: N + 1 }, () =>
  Array.from({ length: K + 1 }, () => 0)
);
let answer = 0;

for (let i = 1; i <= N; i++) {
  dp[i][0] = 1;
  dp[i][1] = i;
}

for (let i = 2; i < N + 1; i++) {
  for (let j = 2; j < K + 1; j++) {
    dp[i][j] = (dp[i - 2][j - 1] + dp[i - 1][j]) % 1000000003;
  }
}

answer = (dp[N - 3][K - 1] + dp[N - 1][K]) % 1000000003;
console.log(answer);
