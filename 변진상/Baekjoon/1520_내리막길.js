const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const [V, H] = stdin[0].split(" ").map((ele) => +ele);
const map = stdin.slice(1).map((ele) => ele.split(" ").map((ele) => +ele));
let dp = Array.from({ length: V }, () => new Array(H).fill(-1));
const dirVector = [
  [-1, 0],
  [1, 0],
  [0, -1],
  [0, 1],
];

const DFS = (x, y) => {
  if (x === H - 1 && y === V - 1) {
    return 1;
  }
  if (dp[y][x] !== -1) return dp[y][x];

  dp[y][x] = 0;

  for ([vectorY, vectorX] of dirVector) {
    if (
      x + vectorX < 0 ||
      x + vectorX >= H ||
      y + vectorY < 0 ||
      y + vectorY >= V
    )
      continue;
    if (map[y][x] > map[y + vectorY][x + vectorX]) {
      dp[y][x] += DFS(x + vectorX, y + vectorY);
      console.log(dp);
    }
  }

  return dp[y][x];
};

DFS(0, 0);

console.log(DFS(0, 0));
