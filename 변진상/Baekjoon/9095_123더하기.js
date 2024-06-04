const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => +ele);

const N = stdin[0];
const numArr = stdin.slice(1);
const answerArr = [];

const dp = new Array(11).fill(0);
dp[0] = 1;
dp[1] = 1;
dp[2] = 2;

for (let i = 3; i < dp.length; i++) {
  dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
}

numArr.forEach((ele) => answerArr.push(dp[ele]));

console.log(answerArr.join("\n"));
