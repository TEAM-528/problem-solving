const fs = require("fs");
const [N, K] = "7 3".split(" ").map((ele) => +ele);
// fs.readFileSync("/dev/stdin")
//   .toString()
//   .trim()
//   .split(" ")
//   .map((ele) => +ele);

const numArr = new Array(N).fill(1).map((v, i) => i + 1);

console.log(numArr);

let cnt = 1;

const answer = [];

while (numArr.length !== 0) {
  console.log(cnt);
  if (cnt % K !== 0) {
    numArr.push(numArr.shift());
  } else {
    answer.push(numArr.shift());
  }
  cnt++;
}

console.log(`<${answer.join(", ")}>`);
