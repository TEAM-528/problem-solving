const fs = require("fs");
let maxV = 0;
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) =>
    ele.split(" ").map((ele, idx, arr) => {
      if (idx === 1) maxV = Math.max(maxV, +ele);
      return +ele;
    })
  );

const N = stdin[0][0];
const ballsArr = stdin.slice(1);

ballsArr;

const sumArr = Array.from({ length: N + 1 }, () =>
  Array.from({ length: maxV + 1 }, () => 0)
);

const sortedBallsArr = [...ballsArr].sort((a, b) => a[1] - b[1]);

sortedBallsArr.forEach(([color, value]) => {
  for (let i = value + 1; i <= maxV; i++) {
    sumArr[color][i] += value;
  }
});

const answer = [];

ballsArr.forEach(([color, value]) => {
  let sum = 0;
  for (let i = 1; i <= N; i++) {
    if (color === i) continue;
    sum += sumArr[i][value];
  }
  answer.push(sum);
});

console.log(answer.join("\n"));
