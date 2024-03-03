// const fs = require("fs");
// const stdin = fs
//   .readFileSync(
//     "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
//   )
//   .toString()
//   .trim()
//   .split("\n");

// const [N, weight] = stdin[0].split(" ").map((ele) => +ele);

// const stuffArr = stdin.slice(1).map((ele) => ele.split(" ").map((ele) => +ele));

// const dp = Array.from({ length: stuffArr.length }, () => [0, 0]);

// let answer = Number.MIN_SAFE_INTEGER;

// for (let i = 0; i < stuffArr.length; i++) {
//   const [crntWeight, crntValue] = stuffArr[i];

//   for (let j = 0; j <= i; j++) {
//     if (dp[j][0] + crntWeight <= weight) {
//       if (dp[j][1] + crntValue > dp[i][1]) {
//         dp[i][0] = dp[j][0] + crntWeight;
//         dp[i][1] = dp[j][1] + crntValue;

//         answer = Math.max(answer, dp[i][1]);
//       }
//     } else {
//       dp[i][0] = dp[i][0];
//       dp[i][0] = dp[i][0];
//     }
//   }
// }

// console.log(dp);
// console.log(answer);

const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const [N, weight] = stdin[0].split(" ").map((ele) => +ele);

const stuffArr = stdin.slice(1).map((ele) => ele.split(" ").map((ele) => +ele));

const dp = Array.from({ length: N + 1 }, () => new Array(weight + 1).fill(0));

for (let i = 0; i < stuffArr.length; i++) {
  const [crntW, crntV] = stuffArr[i];

  for (let j = 1; j < weight + 1; j++) {
    if (j < crntW) {
      dp[i + 1][j] = dp[i][j];
      continue;
    }

    if (dp[i][j - crntW] + crntV > dp[i][j]) {
      dp[i + 1][j] = dp[i][j - crntW] + crntV;
    } else {
      dp[i + 1][j] = dp[i][j];
    }
  }
}

console.log(dp[N][weight]);
