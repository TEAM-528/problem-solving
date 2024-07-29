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

const set = new Set();

const visitedArr = new Array(N).fill(false);
const tmpAnswer = [];
let answer = "";

const dfs = (L) => {
  if (L === M) {
    const str = tmpAnswer.join(" ");

    if (!set.has(str)) {
      set.add(str);

      answer += str + "\n";
    }
    return;
  } else {
    for (let i = 0; i < N; i++) {
      if (visitedArr[i]) continue;
      visitedArr[i] = true;
      tmpAnswer.push(numArr[i]);
      dfs(L + 1);
      tmpAnswer.pop();
      visitedArr[i] = false;
    }
  }
};

dfs(0);

console.log(answer);
