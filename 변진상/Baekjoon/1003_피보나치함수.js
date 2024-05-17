const fs = require("fs");

const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => +ele);

let answer = "";

const dp_one = [0, 1, 1].concat(new Array(39).fill(0));
const dp_zero = [1, 0, 1].concat(new Array(39).fill(0));

for (let i = 2; i < dp_zero.length; i++) {
  dp_zero[i] = dp_zero[i - 1] + dp_zero[i - 2];
  dp_one[i] = dp_one[i - 1] + dp_one[i - 2];
}

stdin.slice(1).forEach((ele) => {
  answer += `${dp_zero[ele]} ${dp_one[ele]}\n`;
});

console.log(answer);
