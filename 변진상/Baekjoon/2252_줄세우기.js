const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => ele.split(" ").map((ele) => +ele));

const [N, M] = stdin[0];
const edgeInfo = stdin.slice(1);

const adjacencyList = Array.from({ length: N }, () => []);
const indegreeArr = Array.from({ length: N }, () => 0);

const answer = [];

edgeInfo.forEach(([from, to]) => {
  adjacencyList[from - 1].push(to - 1);

  indegreeArr[to - 1]++;
});

const BFS = () => {
  const queue = [];

  indegreeArr.forEach((v, i) => {
    v === 0 && queue.push(i);
  });

  while (queue.length !== 0) {
    const crntNode = queue.shift();
    answer.push(crntNode);
    adjacencyList[crntNode].forEach((v) => {
      indegreeArr[v]--;

      if (indegreeArr[v] === 0) {
        queue.push(v);
      }
    });
  }
};

BFS();

console.log(answer.map((ele) => ele + 1).join(" "));
