const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const [H, W] = stdin[0].split(" ").map((ele) => +ele);
const board = stdin.slice(1);

const chkArr_B = Array.from({ length: H }, () => []);
const chkArr_A = Array.from({ length: H }, () => []);

let answer = Number.MAX_SAFE_INTEGER;

const getStartWithB = () => {
  const chkArr = chkArr_B;
  let cnt = 1;

  for (let i = 0; i < H; i++) {
    for (let j = 0; j < W; j++) {
      chkArr[i][j] = 0;

      if (cnt % 2 !== 0 && board[i][j] !== "B") {
        chkArr[i][j] = 1;
      } else if (cnt % 2 === 0 && board[i][j] !== "W") {
        chkArr[i][j] = 1;
      }

      cnt += 1;
    }
    if (W % 2 === 0) cnt -= 1;
  }
};

const getStartWithW = () => {
  const chkArr = chkArr_A;
  let cnt = 1;

  for (let i = 0; i < H; i++) {
    for (let j = 0; j < W; j++) {
      chkArr[i][j] = 0;

      if (cnt % 2 !== 0 && board[i][j] !== "W") {
        chkArr[i][j] = 1;
      } else if (cnt % 2 === 0 && board[i][j] !== "B") {
        chkArr[i][j] = 1;
      }

      cnt += 1;
    }
    if (W % 2 === 0) cnt -= 1;
  }
};
getStartWithW();
getStartWithB();

const getSum = (start_i, start_j) => {
  let sum_A = 0;
  let sum_B = 0;

  for (let i = start_i; i < start_i + 8; i++) {
    for (let j = start_j; j < start_j + 8; j++) {
      sum_A += chkArr_A[i][j];
      sum_B += chkArr_B[i][j];
    }
  }

  return Math.min(sum_A, sum_B);
};

for (let i = 0; i <= H - 8; i++) {
  for (let j = 0; j <= W - 8; j++) {
    answer = Math.min(answer, getSum(i, j));
  }
}

console.log(answer);
