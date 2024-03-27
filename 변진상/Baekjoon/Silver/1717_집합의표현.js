const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

// const readline = require("readline");
// const rl = readline.createInterface({
//   input: process.stdin,
//   output: process.stdout,
// });
// let stdin = [];

const solution = (stdin) => {
  const [N, M] = stdin[0].split(" ").map((ele) => +ele);
  const instArr = stdin
    .slice(1)
    .map((ele) => ele.split(" ").map((ele) => +ele));

  const unionList = new Array(N + 1).fill(0).map((v, i) => i);

  const answer = [];

  const getRoot = (node) => {
    if (unionList[node] === node) return node;
    unionList[node] = getRoot(unionList[node]);
    return unionList[node];
  };

  const union = (a, b) => {
    const rootA = getRoot(a);
    const rootB = getRoot(b);
    console.log(unionList);

    if (rootA < rootB) {
      unionList[rootB] = rootA;
    } else {
      unionList[rootA] = rootB;
    }
  };

  const isSameRoot = (a, b) => {
    const rootA = getRoot(a);
    const rootB = getRoot(b);

    if (rootA === rootB) return true;
    else return false;
  };

  instArr.forEach(([inst, i, j]) => {
    if (inst === 0) {
      union(i, j);
    } else {
      if (isSameRoot(i, j)) {
        answer.push("YES");
      } else {
        answer.push("NO");
      }
    }
  });

  console.log(answer.join("\n"));
};

// rl.on("line", function (line) {
//   stdin.push(line);
// }).on("close", function () {
//   solution(stdin);
//   process.exit();
// });

solution(stdin);
