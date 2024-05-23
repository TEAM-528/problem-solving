const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) =>
    ele.split(" ").map((ele, idx, arr) => {
      return +ele;
    })
  );

const N = stdin[0][0];
const ballsArr = stdin.slice(1);

ballsArr.forEach((ele, idx) => ele.push(idx));
ballsArr.sort((a, b) => a[1] - b[1]);

const colorSum = new Array(N + 1).fill(0);
const answer = new Array(N + 1).fill(0);
let sum = 0;
let lastBallIdx = 0;

ballsArr.forEach(([color, value, originalIdx], idx) => {
  while (value > ballsArr[lastBallIdx][1]) {
    sum += ballsArr[lastBallIdx][1];
    colorSum[ballsArr[lastBallIdx][0]] += ballsArr[lastBallIdx][1];
    lastBallIdx += 1;
  }
  answer[originalIdx] = sum - colorSum[color];
});

console.log(answer.join("\n"));
