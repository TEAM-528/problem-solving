const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => ele.split(" ").map((ele) => +ele));

const [N, K] = stdin[0];
const sectionArr = stdin.slice(1);
const sumArr = new Array(1000001).fill(0);
let sectionSum = 0;
let [lt, rt] = [0, 0];

sectionArr.forEach(([start, end]) => {
  for (let i = start; i < end; i++) {
    sumArr[i] += 1;
  }
});

while (lt <= rt && rt < 1000001) {
  if (K === sectionSum) break;
  else if (sectionSum < K) {
    sectionSum += sumArr[rt];
    rt += 1;
  } else if (sectionSum > K) {
    sectionSum -= sumArr[lt];
    lt += 1;
  }
}

const answer = K === sectionSum ? `${lt} ${rt}` : `0 0`;

console.log(answer);
