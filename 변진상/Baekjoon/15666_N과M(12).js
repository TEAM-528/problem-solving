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

const [numArr] = stdin.slice(1);
numArr.sort((a, b) => a - b);

const visitedArr = new Array(N).fill(false);
const tmpAnswer = [];
const set = new Set();

let answer = "";

const dfs = (start, L) => {
  if (L === M) {
    const str = tmpAnswer.join(" ");
    if (!set.has(str)) {
      set.add(str);
      answer += str + "\n";
    }
    return;
  } else {
    for (let i = start; i < N; i++) {
      tmpAnswer.push(numArr[i]);
      dfs(i, L + 1);
      tmpAnswer.pop();
    }
  }
};

dfs(0, 0);

console.log(answer);
