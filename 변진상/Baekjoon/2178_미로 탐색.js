const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const [N, M] = stdin[0].split(" ").map((ele) => +ele);
const mapArr = stdin.slice(1).map((ele) => ele.split("").map((ele) => +ele));
const visited = Array.from({ length: N }, () =>
  Array.from({ length: M }, () => false)
);
const cost = Array.from({ length: N }, () =>
  Array.from({ length: M }, () => Number.MAX_SAFE_INTEGER)
);

const dirVec = [
  [-1, 0],
  [1, 0],
  [0, -1],
  [0, 1],
];

const BFS = () => {
  const queue = [[0, 0]];
  cost[0][0] = 1;
  visited[0][0] = true;

  while (queue.length !== 0) {
    const [crnt_y, crnt_x] = queue.shift();

    for (let [dirVec_y, dirVec_x] of dirVec) {
      const [next_y, next_x] = [crnt_y + dirVec_y, crnt_x + dirVec_x];
      if (
        next_y >= N ||
        next_x >= M ||
        next_y < 0 ||
        next_x < 0 ||
        mapArr[next_y][next_x] !== 1 ||
        visited[next_y][next_x] === true
      ) {
        continue;
      }

      cost[next_y][next_x] = Math.min(
        cost[crnt_y][crnt_x] + 1,
        cost[next_y][next_x]
      );
      visited[next_y][next_x] = true;

      queue.push([next_y, next_x]);
    }
  }
};

BFS();

console.log(cost[N - 1][M - 1]);
