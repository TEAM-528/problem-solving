const startTime = new Date();

const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => ele.split(" ").map((ele) => +ele));

const [N, M] = stdin[0];
const edgeInfo = stdin.slice(1, N);
const questionsArr = stdin.slice(N, N + M);
const answerArr = [];

const dist = Array.from({ length: N + 1 }, () =>
  Array.from({ length: N + 1 }, () => Infinity)
);

edgeInfo.forEach(([from, to, cost]) => {
  dist[from][to] = cost;
  dist[to][from] = cost;
});

for (let i = 1; i <= N; i++) {
  dist[i][i] = 0;
}

for (let k = 1; k <= N; k++) {
  for (let i = 1; i <= N; i++) {
    for (let j = 1; j <= N; j++) {
      dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
    }
  }
}

questionsArr.forEach(([from, to]) => {
  answerArr.push(dist[from][to]);
});

console.log(answerArr.join("\n"));

const endTime = new Date();

console.log(endTime.getTime() - startTime.getTime());
