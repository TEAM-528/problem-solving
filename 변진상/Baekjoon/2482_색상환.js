const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => +ele);

const [N, K] = stdin;

const colorArr = new Array(N).fill(false);
let answer = 0;

const startTime = new Date();

const dfs = (L, start) => {
  if (L === K) {
    answer += 1;
    return;
  } else {
    let lastIdx = colorArr[0] ? colorArr.length - 1 : colorArr.length;

    for (let i = start; i < lastIdx; i++) {
      colorArr[i] = true;
      dfs(L + 1, i + 2);
      colorArr[i] = false;
    }
  }
};

dfs(0, 0);

console.log(answer % 1000000003);
const endTime = new Date();

console.log(endTime.getTime() - startTime.getTime());
