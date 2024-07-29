const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split(" ")
  .map((ele) => +ele);

const [N, M] = stdin;

const answer = [];

let outAnswer = "";

const dfs = (start, L) => {
  if (L === M) {
    outAnswer += answer.join(" ") + "\n";
  } else {
    for (let i = start; i <= N; i++) {
      answer.push(i);
      dfs(i, L + 1);
      answer.pop(i);
    }
  }
};

dfs(1, 0);

console.log(outAnswer);
