const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const [N, M] = stdin[0].split(" ").map((ele) => +ele);
const mapArr = stdin.slice(1).map((ele) => ele.split(""));

const dirVec = [
  [-1, 0],
  [1, 0],
  [0, -1],
  [0, 1],
];

const canGo = (y, x) => !(y < 0 || y >= N || x < 0 || x >= M);
const visited = new Array("Z".charCodeAt() - "A".charCodeAt() + 2).fill(false);
let answer = 0;

const dfs = (y, x, cnt) => {
  answer = Math.max(cnt, answer);
  visited[mapArr[y][x].charCodeAt()] = true;

  for (const [dirY, dirX] of dirVec) {
    const [nY, nX] = [y + dirY, x + dirX];

    if (!canGo(nY, nX)) continue;

    const nextChar = mapArr[nY][nX].charCodeAt();

    if (visited[nextChar]) continue;
    dfs(nY, nX, cnt + 1);
    visited[nextChar] = false;
  }
};

dfs(0, 0, 1);

console.log(answer);
