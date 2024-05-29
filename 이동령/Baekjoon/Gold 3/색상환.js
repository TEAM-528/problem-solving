const input = require("fs")
  .readFileSync(process.platform === "linux" ? "/dev/stdin" : "input.txt")
  .toString()
  .trim()
  .split("\n");

const MOD = 1000000003;
const [N, K] = input.map((str) => +str);
let DP = Array.from({ length: N + 1 }, (_, i) =>
  Array.from({ length: K + 1 }, (_, j) => (j === 0 ? 1 : j === 1 ? i : 0))
);

for (let i = 2; i <= N; i++) {
  for (let j = 2; j <= K; j++) {
    DP[i][j] = (DP[i - 2][j - 1] + DP[i - 1][j]) % MOD;
  }
}

console.log(DP);

console.log((DP[N - 3][K - 1] + DP[N - 1][K]) % MOD);
