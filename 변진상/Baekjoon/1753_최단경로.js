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
const [startVertex] = stdin[1];
const edgeInfoArr = stdin.slice(2);

const adjacencyList = Array.from({ length: N + 1 }, () => []);

edgeInfoArr.forEach(([from, to, cost]) => {
  adjacencyList[from].push([to, cost]);
});

adjacencyList.forEach((arr) => arr.sort((a, b) => a[1] - b[1]));

const costArr = new Array(N + 1).fill(Infinity);
const visitedArr = new Array(N + 1).fill(false);

const djikstra = (startVertex) => {
  const queue = [];

  queue.push(startVertex);
  costArr[startVertex] = 0;

  while (queue.length) {
    const crntVertex = queue.shift();
    visitedArr[crntVertex] = true;

    adjacencyList[crntVertex].forEach(([next, cost]) => {
      costArr[next] = Math.min(costArr[crntVertex] + cost, costArr[next]);

      if (!visitedArr[next]) {
        queue.push(next);
      }
    });
  }
};

djikstra(startVertex);

costArr.forEach((ele, idx) => {
  if (ele === Infinity) {
    costArr[idx] = "INF";
  }
});

console.log(costArr.slice(1).join("\n"));
