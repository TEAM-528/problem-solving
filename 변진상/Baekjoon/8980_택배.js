const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => ele.split(" ").map((ele) => +ele));

const [N, C] = stdin[0];
const [M] = stdin[1];
const infoArr = stdin.slice(2);
const capabilityArr = new Array(N + 1).fill(C);
let answer = 0;

infoArr.sort((a, b) => a[0] - b[0]).sort((a, b) => a[1] - b[1]);
for (let i = 0; i < M; i++) {
  let tmpLimit = C;
  const [from, to, weight] = infoArr[i];

  for (let villageMum = from; villageMum < to; villageMum++) {
    tmpLimit = Math.min(capabilityArr[villageMum], tmpLimit);
  }
  const weightToUnload = Math.min(tmpLimit, weight);

  for (let villageMum = from; villageMum < to; villageMum++) {
    capabilityArr[villageMum] -= weightToUnload;
  }

  answer += weightToUnload;
}

console.log(answer);
