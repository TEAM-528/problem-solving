const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => +ele);

const N = stdin[0];
const numArr = stdin.slice(1);
const indexMap = new Map();
const sortedArr = [...numArr].sort((a, b) => a - b);

sortedArr.forEach((v, i) => {
  indexMap.set(v, i);
});

const movingCntArr = [];

for (let i = 0; i < numArr.length; i++) {
  const crntNum = numArr[i];

  const sortedIdx = indexMap.get(crntNum);

  movingCntArr.push(i - sortedIdx);
}

console.log(Math.max(...movingCntArr) + 1);
