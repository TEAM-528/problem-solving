const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => ele.split(" ").map((ele) => +ele));
const [N, M] = stdin[0];
const map = stdin.slice(1);
let answer = "";

const visited = Array.from({ length: N }, () =>
  Array.from({ length: M }, () => -1)
);

const dirVec = [
  [-1, 0],
  [1, 0],
  [0, -1],
  [0, 1],
];

const getDestination = () => {
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (map[i][j] === 2) return [i, j];
    }
  }
};

const bfs = ([destY, destX]) => {
  const queue = [];

  queue.push([destY, destX]);
  visited[destY][destX] = 0;

  while (queue.length) {
    const [crntY, crntX] = queue.shift();

    for ([dirY, dirX] of dirVec) {
      const [nextY, nextX] = [crntY + dirY, crntX + dirX];
      if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M) continue;
      if (map[nextY][nextX] === 0) {
        visited[nextY][nextX] = 0;
        continue;
      }

      if (visited[nextY][nextX] !== -1) continue;

      visited[nextY][nextX] = visited[crntY][crntX] + 1;

      queue.push([nextY, nextX]);
    }
  }
};

const postProcess = () => {
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (visited[i][j] === -1 && map[i][j] === 1) continue;
      if (visited[i][j] === -1 && map[i][j] === 0) visited[i][j] = 0;
    }
    answer += visited[i].join(" ");
    answer += "\n";
  }
};

bfs(getDestination());

postProcess();

console.log(answer);
