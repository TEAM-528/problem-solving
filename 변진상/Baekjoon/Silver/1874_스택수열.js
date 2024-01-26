const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const N = +stdin[0];
const numArr = stdin.slice(1).map((ele) => +ele);

const ascNumArr = new Array(N).fill(1).map((v, i) => i + 1);
const stack = [];

let answer = [];

const getTop = () => stack[stack.length - 1];

let targetIdx = 0;
let ascIdx = 0;

while (ascIdx !== N || targetIdx !== N) {
  if (getTop() === numArr[targetIdx]) {
    stack.pop();
    answer.push("-");
    targetIdx += 1;
  } else {
    stack.push(ascNumArr[ascIdx]);
    answer.push("+");
    ascIdx += 1;
  }

  if (ascIdx === N && getTop() !== numArr[targetIdx]) {
    answer = "NO";
    break;
  }
}
console.log(answer === "NO" ? answer : answer.join("\n"));
