const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("");

const stack = [];
const getTop = () => {
  return stack[stack.length - 1];
};

let answer = "";

const postFix = () => {
  for (let i = 0; i < stdin.length; i++) {
    const crntChar = stdin[i];

    if ("+-*/()".includes(crntChar)) {
      if (crntChar === "(") {
        stack.push(crntChar);
      } else if (crntChar === ")") {
        while (stack.lenth !== 0 && getTop() !== "(") {
          answer += stack.pop();
        }
        stack.pop();
      } else if ("*/".includes(crntChar)) {
        while (stack.lenth !== 0 && "*/".includes(getTop())) {
          answer += stack.pop();
        }
        stack.push(crntChar);
      } else {
        while (
          (stack.lenth !== 0 && "*/".includes(getTop())) ||
          (stack.lenth !== 0 && "+-".includes(getTop()))
        ) {
          answer += stack.pop();
        }
        stack.push(crntChar);
      }
    } else {
      answer += crntChar;
    }

    if (i === stdin.length - 1) {
      while (stack.length) {
        answer += stack.pop();
      }
    }
  }
};

postFix();

console.log(answer);
