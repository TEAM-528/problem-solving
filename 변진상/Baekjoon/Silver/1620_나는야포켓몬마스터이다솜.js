const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const [N, M] = stdin[0].split(" ").map((ele) => +ele);
const nameArr = stdin.slice(1, N + 1);
const probArr = stdin.slice(N + 1);
const answerArr = [];

const monsterMap = new Map();

nameArr.forEach((val, idx) => {
  monsterMap.set(val, idx + 1);
});

probArr.forEach((ele) => {
  if (!isNaN(ele)) {
    // 숫자
    answerArr.push(nameArr[+ele - 1]);
  } else {
    answerArr.push(monsterMap.get(ele));
  }
});

console.log(answerArr.join("\n"));
