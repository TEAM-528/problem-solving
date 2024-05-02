const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");
const [N, M] = stdin[0].split(" ").map((ele) => +ele);

const URLPwList = stdin.slice(1, N + 1).map((ele) => ele.split(" "));
const probArr = stdin.slice(N + 1);
const pwMap = new Map();
const answerArr = [];

URLPwList.forEach(([url, pw]) => {
  pwMap.set(url, pw);
});

probArr.forEach((ele) => {
  answerArr.push(pwMap.get(ele));
});

console.log(answerArr.join("\n"));
