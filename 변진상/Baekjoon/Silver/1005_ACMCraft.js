const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const caseNum = +stdin[0];
const answer = [];
let line = 1;

const solution = (buildingNum, ruleNum, costsArr, ruleArr, targetBuilding) => {
  const adjacencyList = Array.from({ length: buildingNum + 1 }, () => []);
  const queue = [];
  const dp = [...costsArr];
  const prevCnt = new Array(buildingNum + 1).fill(0);

  ruleArr.forEach(([from, to]) => {
    adjacencyList[from].push(to);
    prevCnt[to]++;
  });

  for (let i = 1; i <= prevCnt.length; i++) {
    if (prevCnt[i] === 0) queue.push(i);
  }

  const BFS = () => {
    while (queue.length !== 0) {
      const crntNode = queue.shift();

      for (let nextNode of adjacencyList[crntNode]) {
        const costToNextNode = dp[crntNode] + costsArr[nextNode];

        if (costToNextNode > dp[nextNode]) {
          dp[nextNode] = costToNextNode;
        }

        prevCnt[nextNode] -= 1;

        if (prevCnt[nextNode] === 0) queue.push(nextNode);
      }
    }
    answer.push(dp[targetBuilding]);
  };

  BFS();
};

for (let i = 0; i < caseNum; i++) {
  const [buildingNum, ruleNum] = stdin[line].split(" ").map((ele) => +ele);
  const costsArr = stdin[line + 1].split(" ").map((ele) => +ele);
  costsArr.unshift(-1);
  const ruleArr = stdin
    .slice(line + 2, line + 2 + ruleNum)
    .map((ele) => ele.split(" ").map((ele) => +ele));
  const targetBuilding = +stdin[line + 2 + ruleNum];
  line = line + 2 + ruleNum + 1;

  solution(buildingNum, ruleNum, costsArr, ruleArr, targetBuilding);
}

console.log(answer.join("\n"));
