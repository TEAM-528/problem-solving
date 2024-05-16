const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const [numOfNode, numOfPD] = stdin[0].split(" ").map((ele) => +ele);
const setListArr = stdin.slice(1).map((ele) =>
  ele
    .split(" ")
    .slice(1)
    .map((ele) => +ele)
);

const adjacencyList = Array.from({ length: numOfNode + 1 }, () => []);

const inDegreeArr = Array.from({ length: numOfNode + 1 }, () => 0);

setListArr.forEach((setList) => {
  for (let i = 0; i < setList.length - 1; i++) {
    const fromNode = setList[i];
    const toNode = setList[i + 1];

    if (!adjacencyList[fromNode].includes(toNode)) {
      adjacencyList[fromNode].push(toNode);
      // 중복 제거
      inDegreeArr[toNode] += 1;
    }
  }
});

const BFS = (startNodesList) => {
  const queue = startNodesList;
  const answer = [];
  let visitedCnt = 0;

  while (queue.length !== 0) {
    const crntStartNode = queue.pop();
    visitedCnt += 1;
    answer.push(crntStartNode);

    for (const nextNode of adjacencyList[crntStartNode]) {
      inDegreeArr[nextNode] -= 1;
      if (inDegreeArr[nextNode] === 0) {
        // 진입차수가 0이 되어 해당 노드까지의 위상 정렬이 완료되었고
        queue.push(nextNode);
        // 큐에 올라가 다음 노드로의 출발 노드의 후보가 될 수 있도록 함
      }
    }
  }

  return visitedCnt !== numOfNode ? 0 : answer.join("\n");
};

const startList = [];

inDegreeArr.forEach((v, i) => {
  if (i === 0) return;
  if (v === 0) {
    startList.push(i);
  }
});

console.log(BFS(startList));
