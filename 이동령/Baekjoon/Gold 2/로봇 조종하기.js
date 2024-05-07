const input = require("fs")
  .readFileSync(process.platform === "linux" ? "/dev/stdin" : "input.txt")
  .toString()
  .split("\n");

const [[N, M], ...mars] = input.map((str) => str.split(" ").map((e) => +e));
const DP = Array.from({ length: N }, () =>
  Array.from({ length: M }, () => -Infinity)
);
DP[0].map((e, i) => (DP[0][i] = (DP[0][i - 1] || 0) + mars[0][i]));

for (let i = 0; i < N - 1; i++) {
  let forward = new Array(M).fill(0);
  let reverse = new Array(M).fill(0);
  for (let j = 0; j < M; j++) {
    forward[j] = Math.max(
      DP[i][j] + mars[i + 1][j],
      forward[j - 1] === undefined ? -Infinity : forward[j - 1] + mars[i + 1][j]
    );
    reverse[M - j - 1] = Math.max(
      DP[i][M - j - 1] + mars[i + 1][M - j - 1],
      reverse[M - j] === undefined
        ? -Infinity
        : reverse[M - j] + mars[i + 1][M - j - 1]
    );
  }
  forward.forEach((e, j) => (DP[i + 1][j] = Math.max(e, reverse[j])));
}

console.log(DP[N - 1][M - 1]);
