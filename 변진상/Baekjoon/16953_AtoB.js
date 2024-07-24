const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split(" ")
  .map((ele) => +ele);

const [A, B] = stdin;

const bfs = (start) => {
  const queue = [];
  let cnt = 1;

  queue.push([start, cnt]);

  while (queue.length) {
    const [crntNum, cnt] = queue.shift();

    if (crntNum === B) {
      return cnt;
    }

    const doubleNum = crntNum * 2;
    const plusOneNum = crntNum * 10 + 1;

    if (doubleNum <= B) {
      queue.push([doubleNum, cnt + 1]);
    }
    if (plusOneNum <= B) {
      queue.push([plusOneNum, cnt + 1]);
    }
  }
};

const answer = bfs(A);

console.log(answer ? answer : -1);
