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

const indexMap = new Map();

kidsArr.forEach((v, i) => indexMap.set(v, i));

for (let i = 0; i < N; i++) {
  const currentKid = kidsArr[i];
  if (currentKid === 1) continue;
  const prevKidIndex = indexMap.get(currentKid - 1);

  if (prevKidIndex < i) dp[i] = dp[prevKidIndex] + 1;
}

console.log(N - Math.max(...dp));
