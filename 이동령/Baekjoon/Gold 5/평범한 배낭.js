var fs = require("fs");

// boj 제출용 코드
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

// 문제 풀이 코드
let [N, K] = input[0].split(" ").map((e) => +e);
let stuff = input.slice(1).map((value) => {
  let current = value.split(" ").map((e) => +e);
  return { w: current[0], v: current[1] };
});

let dp = Array.from({ length: N + 1 }, () =>
  Array.from({ length: K + 1 }, () => 0)
);

for (let i = 1; i <= N; i++) {
  let { v, w } = stuff[i - 1];
  for (let j = 1; j <= K; j++) {
    if (w <= j) dp[i][j] = Math.max(dp[i - 1][j], v + dp[i - 1][j - w]);
    else dp[i][j] = dp[i - 1][j];
  }
}

console.log(dp[N][K]);
