const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("");

const stack = [];
const getTop = () => {
  return stack[stack.length - 1];
};

let answer = "";

const postFix = () => {
  for (let i = 0; i < stdin.length; i++) {
    const crntChar = stdin[i];

    if ("+-*/()".includes(crntChar)) {
      // 연산자
      if (stack.length === 0) {
        stack.push(crntChar);
        continue;
      }

      if (crntChar === "(") {
        stack.push(crntChar);
      } else if (crntChar === ")") {
        while (stack.length !== 0 && getTop() !== "(") {
          answer += stack.pop();
        }

        if (getTop() !== ")") {
          stack.pop();
        }
      } else if ("*/".includes(crntChar)) {
        while (stack.length !== 0 && "*/".includes(getTop())) {
          answer += stack.pop();
        }

        stack.push(crntChar);
      } else if ("+-".includes(crntChar)) {
        while (
          (stack.length !== 0 && "+-".includes(getTop())) ||
          (stack.length !== 0 && "*/".includes(getTop()))
        ) {
          answer += stack.pop();
        }

        stack.push(crntChar);
      }
    } else {
      // 피연산자
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
