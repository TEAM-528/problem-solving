const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => ele.split(" ").map((ele) => +ele));

const N = stdin[0][0];
const edgeInfo = stdin[1];
const cuttingNode = stdin[2][0];

const adjacencyList = Array.from({ length: N }, () => []);
const childCntArr = new Array(N).fill(0);
let leafNodeCnt = 0;
let leafNodeCntOfCutTree = 0;

edgeInfo.forEach((parent, child) => {
  if (parent !== -1) {
    childCntArr[parent] += 1;
    adjacencyList[parent].push(child);
  }
});

const DFS = (node) => {
  const parentOfNode = edgeInfo[node];

  if (parentOfNode !== -1) {
    childCntArr[parentOfNode] -= 1;
  }

  childCntArr[node] = -1;

  if (childCntArr[node] === 0) {
    leafNodeCntOfCutTree += 1;
    return;
  } else {
    adjacencyList[node].forEach((nextNode) => {
      DFS(nextNode);
    });
  }
};
DFS(cuttingNode);

childCntArr.forEach((ele) => {
  if (ele === 0) leafNodeCnt++;
});

console.log(leafNodeCnt);
