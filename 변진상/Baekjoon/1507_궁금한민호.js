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
const numArr = stdin.slice(1);
const checkArr = Array.from({ length: N }, () =>
  Array.from({ length: N }, () => true)
);
let answer = 0;
let flag = true;

for (let k = 0; k < N; k++) {
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (i === j) continue;
      if (numArr[i][j] === numArr[i][k] + numArr[k][j]) {
        if (i === k || j === k) continue;
        checkArr[i][j] = false;
      } else if (numArr[i][j] > numArr[i][k] + numArr[k][j]) {
        flag = false;
      }
    }
  }
}

for (let i = 0; i < N; i++) {
  for (let j = i + 1; j < N; j++) {
    if (checkArr[i][j]) {
      answer += numArr[i][j];
    }
  }
}

console.log(flag ? answer : -1);
