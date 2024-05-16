const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const N = +stdin[0];
const edgeInfo = stdin.slice(1).map((ele) => ele.split(" ").map((ele) => +ele));

const adjacencyList = Array.from({ length: N + 1 }, () => new Array());

const visited = new Array(N + 1);

let jointVertex = 0;
let tmpCnt = 0;

edgeInfo.forEach((ele, idx) => {
  if (ele.length > tmpCnt) {
    tmpCnt = ele.length;
    jointVertex = ele[0];
  }

  for (let i = 1; i < ele.length - 1; i += 2) {
    adjacencyList[ele[0]].push([ele[i], ele[i + 1]]);
  }
});

visited[jointVertex] = true;
const costsArr = [];

const DFS = (node, costSum) => {
  if (adjacencyList[node].length === 1) costsArr[node] = costSum;
  for (const [nextNode, cost] of adjacencyList[node]) {
    if (visited[nextNode]) continue;
    visited[nextNode] = true;
    DFS(nextNode, costSum + cost);
  }

  return costSum;
};

DFS(jointVertex, 0);
costsArr.sort((a, b) => b - a);

console.log(costsArr[0] + costsArr[1]);
