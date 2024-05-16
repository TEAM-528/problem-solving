const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
stdin.pop();

const chkBalance = (str) => {
  const stack = [];
  let answer = "yes";

  for (let char of str) {
    if (char === "[" || char === "(") {
      stack.push(char);
    } else if (char === "]") {
      if (stack.length === 0) {
        answer = "no";
        return answer;
      }
      if (stack[stack.length - 1] === "[") stack.pop();
      else {
        answer = "no";
        return answer;
      }
    } else if (char === ")") {
      if (stack.length === 0) {
        answer = "no";
        return answer;
      }
      if (stack[stack.length - 1] === "(") stack.pop();
      else {
        answer = "no";
        return answer;
      }
    }
  }

  if (stack.length !== 0) {
    answer = "no";
    return answer;
  }

  return answer;
};

for (let str of stdin) {
  console.log(chkBalance(str));
}
