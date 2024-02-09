const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const [villageNum, edgeNum, target] = stdin[0].split(" ").map((ele) => +ele);
const edgeInfoArr = stdin
  .slice(1)
  .map((ele) => ele.split(" ").map((ele) => +ele));

const adjacencyList = Array.from({ length: villageNum + 1 }, () => []);
const counterAdjacencyList = Array.from({ length: villageNum + 1 }, () => []);
const costs = Array.from({ length: villageNum + 1 }, () =>
  new Array(villageNum + 1).fill(Infinity)
);
const counterCosts = Array.from({ length: villageNum + 1 }, () =>
  new Array(villageNum + 1).fill(Infinity)
);

for (let i = 0; i <= villageNum; i++) {
  costs[i][i] = 0;
  counterCosts[i][i] = 0;
}

edgeInfoArr.forEach(([from, to, cost]) => {
  adjacencyList[from].push(to);
  counterAdjacencyList[to].push(from);

  costs[from][to] = cost;
  counterCosts[to][from] = cost;
});

const dijkstra = (startNum, targetNum, adlist, costs) => {
  const queue = [];
  queue.push(startNum);
  const dp = new Array(villageNum + 1).fill(Infinity);
  dp[startNum] = 0;

  while (queue.length > 0) {
    const crntNode = queue.shift();

    for (let nextNode of adlist[crntNode]) {
      if (dp[nextNode] > dp[crntNode] + costs[crntNode][nextNode]) {
        dp[nextNode] = dp[crntNode] + costs[crntNode][nextNode];
        queue.push(nextNode);
      }
    }
  }

  return dp[targetNum];
};

const answer = [];

for (let i = 1; i <= villageNum; i++) {
  answer.push(
    dijkstra(i, target, adjacencyList, costs) +
      dijkstra(i, target, counterAdjacencyList, counterCosts)
  );
}

console.log(Math.max(...answer));
