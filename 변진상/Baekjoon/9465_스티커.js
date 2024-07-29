const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => ele.split(" ").map((ele) => +ele));

const [testCaseN] = stdin[0];
const caseArr = stdin.slice(1);
for (let i = 0; i < testCaseN; i++) {
  const [N] = caseArr[3 * i];
  const arr = [
    [0, 0, ...caseArr[3 * i + 1]],
    [0, 0, ...caseArr[3 * i + 2]],
  ];

  for (let i = 2; i < arr[0].length; i++) {
    for (let j = 0; j < 2; j++) {
      if (j === 0) {
        arr[j][i] += Math.max(
          arr[j][i - 2],
          arr[j + 1][i - 2],
          arr[j + 1][i - 1]
        );
      } else {
        arr[j][i] += Math.max(
          arr[j][i - 2],
          arr[j - 1][i - 2],
          arr[j - 1][i - 1]
        );
      }
    }
  }

  console.log(Math.max(...arr.flat()));
}
