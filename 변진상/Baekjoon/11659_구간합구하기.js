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
const numArr = stdin[1];
numArr.unshift(0);
const sectionNumArr = stdin.slice(2);
let answer = [];

const dp = Array.from({ length: N + 1 }, () => 0);

let tmpSum = 0;
for (let i = 1; i <= N; i++) {
  tmpSum += numArr[i];
  dp[i] = tmpSum;
}

sectionNumArr.forEach(([from, to]) => {
  answer.push(dp[to] - dp[from - 1]);
});

console.log(answer.join("\n"));
