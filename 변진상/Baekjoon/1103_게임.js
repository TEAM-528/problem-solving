const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const [N, M] = stdin[0].split(" ");
const mapArr = stdin.slice(1).map((ele) => ele.split(""));
const distMap = Array.from({ length: N }, () =>
  Array.from({ length: M }, () => 0)
);

const visited = Array.from({ length: N }, () =>
  Array.from({ length: M }, () => false)
);

const 상하좌우 = [
  [-1, 0],
  [1, 0],
  [0, -1],
  [0, 1],
];

const isAccessible = (y, x) => {
  if (y < 0 || y >= N || x < 0 || x >= M) return false;
  else return true;
};

const isHole = (y, x) => {
  if (mapArr[y][x] === "H") return true;
  else return false;
};

let answer = Number.MIN_SAFE_INTEGER;
let cycleFlag = false;
let tt = [];

const dfs = (y, x, cnt) => {
  answer = Math.max(answer, cnt);

  const 갈거리 = +mapArr[y][x];

  for (const [dirY, dirX] of 상하좌우) {
    const [nextY, nextX] = [y + dirY * 갈거리, x + dirX * 갈거리];

    if (!isAccessible(nextY, nextX)) continue;
    if (isHole(nextY, nextX)) continue;
    if (distMap[nextY][nextX] > cnt + 1) continue;

    if (visited[nextY][nextX]) {
      cycleFlag = true;
      return;
    } else {
      distMap[y][x] = Math.max(distMap[y][x], cnt + 1);

      visited[nextY][nextX] = true;
      dfs(nextY, nextX, cnt + 1);
      visited[nextY][nextX] = false;
    }
  }
};

dfs(0, 0, 1);

console.log(cycleFlag ? -1 : answer);
