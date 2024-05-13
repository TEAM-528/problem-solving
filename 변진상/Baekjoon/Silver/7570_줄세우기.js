const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => ele.split(" ").map((ele) => +ele));

const N = stdin[0][0];
const kidsArr = stdin[1];
const dp = new Array(N).fill(1);

for (let i = 0; i < N; i++) {
  const currentKid = kidsArr[i];
  for (let j = 0; j < i; j++) {
    const prevKidNum = kidsArr[j];

    if (prevKidNum + 1 === currentKid) {
      dp[i] = dp[j] + 1;
      break;
    }
  }
}

console.log(N - Math.max(...dp));
