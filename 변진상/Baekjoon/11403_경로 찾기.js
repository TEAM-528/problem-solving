const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => ele.split(" ").map((ele) => +ele));

const [N] = stdin[0];
const adjacencyList = stdin.slice(1);
const dist = Array.from({ length: N }, () =>
  Array.from({ length: N }, () => 0)
);
let answer = "";

for (let i = 0; i < N; i++) {
  for (let j = 0; j < N; j++) {
    if (adjacencyList[i][j]) dist[i][j] = adjacencyList[i][j];
    else dist[i][j] = Infinity;
  }
}

for (let k = 0; k < N; k++) {
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
    }
  }
}

for (let i = 0; i < N; i++) {
  for (let j = 0; j < N; j++) {
    if (dist[i][j] === Infinity) dist[i][j] = 0;
    else dist[i][j] = 1;
  }
  answer += dist[i].join(" ") + "\n";
}

console.log(answer);
