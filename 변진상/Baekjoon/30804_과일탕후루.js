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
const [fruits] = stdin.slice(1);

let lt = 0;
let rt = 0;
let numCntArr = new Array(10).fill(0);
let kind = 0;
let cnt = 0;
let max = Number.MIN_SAFE_INTEGER;

while (rt < N) {
  if (numCntArr[fruits[rt]] === 0) {
    kind++;
  }

  cnt += 1;
  numCntArr[fruits[rt]] += 1;

  if (kind > 2) {
    numCntArr[fruits[lt]] -= 1;
    if (numCntArr[fruits[lt]] === 0) kind -= 1;
    lt += 1;
    cnt -= 1;
  }

  max = Math.max(cnt, max);

  rt += 1;
}

console.log(max);
