const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => ele.split(" ").map((ele) => +ele));

const [cityNum] = stdin[0];
const [busNum] = stdin[1];

const edgeInfoArr = stdin.slice(2);

const costArr = Array.from({ length: cityNum + 1 }, () =>
  Array.from({ length: cityNum + 1 }, () => Infinity)
);

edgeInfoArr.forEach(([from, to, cost]) => {
  if (costArr[from][to] === 0) {
    costArr[from][to] = cost;
  } else {
    costArr[from][to] = Math.min(costArr[from][to], cost);
  }
});

for (let i = 0; i <= cityNum; i++) {
  costArr[i][i] = 0;
}

for (let k = 1; k <= cityNum; k++) {
  for (let i = 1; i <= cityNum; i++) {
    for (let j = 1; j <= cityNum; j++) {
      costArr[i][j] = Math.min(costArr[i][k] + costArr[k][j], costArr[i][j]);
    }
  }
}

for (let i = 1; i <= cityNum; i++) {
  for (let j = 1; j <= cityNum; j++) {
    if (costArr[i][j] === Infinity) {
      costArr[i][j] = 0;
    }
  }
}

const answerArr = [];

for (let i = 1; i <= cityNum; i++) {
  answerArr.push(costArr[i].slice(1).join(" "));
}

console.log(answerArr.join("\n"));
