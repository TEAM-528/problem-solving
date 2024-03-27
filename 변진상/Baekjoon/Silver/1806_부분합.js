const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");
const [N, S] = stdin[0].split(" ").map((ele) => +ele);
const numArr = stdin[1].split(" ").map((ele) => +ele);

let [lt, rt] = [0, 0];
let answer = Number.MAX_SAFE_INTEGER;
let sum = numArr[lt];

while (lt <= rt) {
  if (sum < S) {
    rt++;
    if (rt >= numArr.length) break;
    sum += numArr[rt];
  } else {
    answer = Math.min(answer, rt - lt + 1);
    sum -= numArr[lt];
    lt++;
  }
}

console.log(lt === 0 && rt >= numArr.length ? 0 : answer);
