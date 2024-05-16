const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const [N, TARGET_VALUE] = stdin[0].split(" ").map((ele) => +ele);

const coinArr = new Set(stdin.slice(1).map((ele) => +ele));
const dp = new Array(TARGET_VALUE + 1).fill(Infinity);
dp[0] = 0;

coinArr.forEach((coinValue) => {
  for (let i = coinValue; i <= TARGET_VALUE; i++) {
    dp[i] = Math.min(dp[i], dp[i - coinValue] + 1);
  }
  console.log(dp);
});

console.log(dp[TARGET_VALUE] === Infinity ? -1 : dp[TARGET_VALUE]);
console.log(dp);
