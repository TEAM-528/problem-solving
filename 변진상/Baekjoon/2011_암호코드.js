// const fs = require("fs");
// const stringArr = fs
//   .readFileSync(
//     "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
//   )
//   .toString()
//   .trim()
//   .split("")
//   .map((ele) => +ele);

const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
let stdin = [];

const solution = (stdin) => {
  const stringArr = stdin
    .toString()
    .trim()
    .split("")
    .map((ele) => +ele);

  const N = stringArr.length;

  const dp = new Array(N + 1).fill(0);

  if (stringArr[0] == 0) {
    console.log(0);
  } else {
    dp[0] = 1;
    dp[1] = 1;

    for (let i = 2; i <= N; i++) {
      if (stringArr[i - 1] === 0) {
        if (stringArr[i - 2] === 1 || stringArr[i - 2] === 2) {
          dp[i] += dp[i - 2] % 1000000;
        } else {
          break;
        }
      } else {
        if (stringArr[i - 2] === 0) {
          dp[i] = dp[i - 1] % 1000000;
        } else {
          const doubleNum = parseInt(`${stringArr[i - 2]}${stringArr[i - 1]}`);

          if (doubleNum >= 1 && doubleNum <= 26)
            dp[i] = (dp[i - 2] + dp[i - 1]) % 1000000;
          else dp[i] = dp[i - 1] % 1000000;
        }
      }
    }

    console.log(dp[N]);
  }
};

rl.on("line", function (line) {
  stdin.push(line);
}).on("close", function () {
  solution(stdin);
  process.exit();
});
