const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const N = +stdin[0];
const numArr = stdin[1].split(" ").map((ele) => +ele);

let cnt = 0;

for (let i = 0; i < numArr.length; i++) {
  let sum = 0;
  for (let j = i; j < numArr.length; j++) {
    sum += numArr[j];
    if (sum % 50 === 0) {
      cnt++;
    }
  }
}

console.log(cnt / 2);
