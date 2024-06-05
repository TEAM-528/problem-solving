const fs = require("fs");
const [A, B] = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split(" ")
  .map((ele) => +ele);

let sumOneToA = ((A ** 2 + A) / 2) % 14579;

let result = sumOneToA;
for (let i = A + 1; i <= B; i++) {
  sumOneToA = (sumOneToA + i) % 14579;
  result = (result * sumOneToA) % 14579;
}

console.log(result % 14579);
