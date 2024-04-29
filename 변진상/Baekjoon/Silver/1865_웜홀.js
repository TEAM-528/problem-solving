const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");
const TC = +stdin[0];
let testCaseArr = stdin.slice(1);
const answer = [];

const bellmanFord = (start, N, edgeList) => {
  const dist = new Array(N + 1).fill(Number.MAX_SAFE_INTEGER);
  dist[start] = 0;
  let isCyclic = false;
  console.log(edgeList);
  for (let i = 1; i <= N; i++) {
    for (const [crntNode, nextNode, cost] of edgeList) {
      if (dist[nextNode] > dist[crntNode] + cost) {
        dist[nextNode] = dist[crntNode] + cost;
        if (i === N) {
          isCyclic = true;
        }
      }
    }
    console.log(dist);
  }

  return isCyclic;
};

for (let caseNumber = 0; caseNumber < TC; caseNumber++) {
  const [N, M, W] = testCaseArr[0].split(" ").map((ele) => +ele);
  const edgeArr = testCaseArr
    .slice(1, 1 + M)
    .map((ele) => ele.split(" ").map((ele) => +ele));
  const warpArr = testCaseArr
    .slice(1 + M, 1 + M + W)
    .map((ele) => ele.split(" ").map((ele) => +ele));
  testCaseArr = testCaseArr.slice(1 + M + W);
  // 케이스 별 입력 처리

  const edgeList = new Array();

  edgeArr.forEach(([from, to, cost]) => {
    edgeList.push([from, to, cost]);
    edgeList.push([to, from, cost]);
  });

  warpArr.forEach(([from, to, cost]) => {
    edgeList.push([from, to, -cost]);
  });

  answer.push(bellmanFord(1, N, edgeList) ? "YES" : "NO");
}

console.log(answer.join("\n"));
