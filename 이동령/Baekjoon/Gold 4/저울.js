const input = require("fs")
  .readFileSync(process.platform === "linux" ? "/dev/stdin" : "input.txt")
  .toString()
  .trim()
  .split("\n");

const [N, M, ...edges] = input.map((str) => str.split(" ").map((e) => +e));
let dist = Array.from({ length: N }, (_, i) =>
  Array.from({ length: N }, (_, j) => (i === j ? 0 : Infinity))
);

edges.forEach(([a, b]) => {
  dist[a - 1][b - 1] = 1;
});

for (let k = 0; k < N; k++) {
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
    }
  }
}

for (let i = 0; i < N; i++) {
  let result = N;
  for (let j = 0; j < N; j++) {
    if (dist[i][j] !== Infinity || dist[j][i] !== Infinity) result--;
  }
  console.log(result);
}
