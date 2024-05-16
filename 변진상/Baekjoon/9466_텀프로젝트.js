const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const caseNum = +stdin[0];
const caseArr = stdin.slice(1);
const answerArr = [];

for (let i = 0; i < caseNum; i++) {
  const N = +caseArr[i * 2];
  const studentArr = caseArr[i * 2 + 1].split(" ").map((ele) => +ele);
  const visited = new Array(N + 1).fill(false);
  let answer = 0;

  const dfs = (nodeNum, visitedRoute) => {
    if (visited[nodeNum]) {
      const cuttingPoint = visitedRoute.indexOf(nodeNum);
      const cutRoute = visitedRoute.slice(cuttingPoint);
      if (cuttingPoint !== -1) answer += cutRoute.length;
      return;
    } else {
      visited[nodeNum] = true;
      visitedRoute.push(nodeNum);
      dfs(studentArr[nodeNum - 1], visitedRoute);
    }
  };

  studentArr.forEach((v, i) => {
    if (visited[v]) return;
    dfs(v, []);
  });

  answerArr.push(N - answer);
}

console.log(answerArr.join("\n"));
