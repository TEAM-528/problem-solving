const fs = require("fs");
const N = +fs.readFileSync("/dev/stdin").toString().trim();

let answer = 1;

let cnt = 1;

while (N > 0) {
  N -= cnt;
  if (cnt === 1) {
    cnt = 6;
  } else {
    cnt += 6;
  }

  answer += 1;
}

console.log(answer);
