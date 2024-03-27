const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const numArr = stdin[1]
  .split(" ")
  .map((ele) => +ele)
  .sort((a, b) => a - b);

let diff = Number.MAX_SAFE_INTEGER;
const answer = [0, numArr.length - 1];

let [lt, rt] = [0, numArr.length - 1];

while (lt < rt) {
  let sum = numArr[lt] + numArr[rt];

  if (diff > Math.abs(sum)) {
    diff = Math.abs(sum);
    answer[0] = lt;
    answer[1] = rt;

    if (sum === 0) {
      break;
    }
  }

  if (sum < 0) {
    lt++;
  } else {
    rt--;
  }
}

console.log(numArr[answer[0]], numArr[answer[1]]);
