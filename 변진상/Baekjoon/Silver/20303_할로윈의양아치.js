const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => ele.split(" ").map((ele) => +ele));

const [N, M, K] = stdin[0];
const candyNumArr = [...stdin[1]];
const edgeInfo = stdin.slice(2);
const visited = new Array(N).fill(false);

const adjacencyList = Array.from({ length: N + 1 }, () => []);

edgeInfo.forEach(([from, to]) => {
  adjacencyList[from].push(to);
  adjacencyList[to].push(from);
});

const bfs = (startPoint) => {
  const queue = [];
  let nodeCnt = 0;
  let cost = 0;

  queue.push(startPoint);

  while (queue.length !== 0) {
    const crntNode = queue.shift();
    visited[crntNode] = true;
    cost += candyNumArr[crntNode - 1];
    nodeCnt += 1;

    for (let nextNode of adjacencyList[crntNode]) {
      if (visited[nextNode]) continue;
      queue.push(nextNode);
    }
  }

  return [nodeCnt, cost];
};

const friendNumCostArr = [];

for (let i = 1; i <= N; i++) {
  if (visited[i]) continue;
  friendNumCostArr.push(bfs(i));
}

const dp = Array.from({ length: K }, () => 0);

for (let idx = 0; idx < friendNumCostArr.length; idx++) {
  for (let i = K - 1; i >= 0; i--) {
    const [friendNum, cost] = friendNumCostArr[idx];
    if (i - friendNum >= 0) {
      dp[i] = Math.max(dp[i], dp[i - friendNum] + cost);
    }
  }
}

console.log(Math.max(...dp));
