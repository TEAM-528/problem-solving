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

const sumArr = new Array(N + 1).fill(0);
const cntArr = new Array(M).fill(0);
let answer = 0;

const nCombinationTwo = (num) => (num * (num - 1)) / 2;

for (let i = 1; i <= N; i++) {
  sumArr[i] = ((numArr[i - 1] % M) + (sumArr[i - 1] % M)) % M;

  cntArr[sumArr[i]] += 1;
}

for (let cnt of cntArr) {
  answer += nCombinationTwo(cnt);
}

answer += cntArr[0];
console.log(answer);
