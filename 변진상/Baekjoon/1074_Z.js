const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split(" ")
  .map((ele) => +ele);
let [N, R, C] = stdin;
N = 2 ** N;

let num = 0;
let answer = 0;

const divide = (x, y, n) => {
  if (x > C || y > R || C >= x + n || R >= y + n) {
    num += n ** 2;
    return;
  }

  if (n === 2) {
    for (let j = y; j < y + 2; j++) {
      for (let i = x; i < x + 2; i++) {
        if (i === C && j === R) {
          answer = num;
          return;
        }
        num++;
      }
    }
  } else {
    divide(x, y, parseInt(n / 2));
    divide(x + parseInt(n / 2), y, parseInt(n / 2));
    divide(x, y + parseInt(n / 2), parseInt(n / 2));
    divide(x + parseInt(n / 2), y + parseInt(n / 2), parseInt(n / 2));
  }
};

divide(0, 0, N);

console.log(answer);
