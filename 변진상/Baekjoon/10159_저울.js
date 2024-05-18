const fs = require("fs");

const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const N = +stdin[0];
const M = +stdin[1];
const weightInfoArr = stdin
  .slice(2)
  .map((ele) => ele.split(" ").map((ele) => +ele));

const dist = Array.from({ length: N + 1 }, () =>
  Array.from({ length: N + 1 }, () => 0)
);

let answerArr = [];

weightInfoArr.forEach(([heavyStuff, lightStuff]) => {
  dist[heavyStuff][lightStuff] = 1;
});

for (let k = 1; k <= N; k++) {
  for (let i = 1; i <= N; i++) {
    for (let j = 1; j <= N; j++) {
      if (dist[i][k] !== 0 && dist[k][j] !== 0) dist[i][j] = 1;
    }
  }
}

for (let i = 1; i <= N; i++) {
  let cnt = 0;
  for (let j = 1; j <= N; j++) {
    if (i === j) continue;
    if (dist[i][j] === 0 && dist[j][i] === 0) cnt++;
  }
  answerArr.push(cnt);
}
console.log(answerArr.join("\n"));
