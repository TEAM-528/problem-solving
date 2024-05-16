const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const [N, M] = stdin[0].split(" ").map((ele) => +ele);
const nonListenArr = stdin.slice(1, N + 1);
const nonSeenArr = new Set(stdin.slice(N + 1));
let answer = [];

nonListenArr.forEach((ele) => {
  if (nonSeenArr.has(ele)) {
    answer.push(ele);
  }
});

answer = answer.sort();

console.log(`${answer.length}\n${answer.join("\n")}`);
