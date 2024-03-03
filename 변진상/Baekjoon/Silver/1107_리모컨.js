const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const NUM = +stdin[0];
const brokenKeys = stdin[2]?.split(" ").map((ele) => +ele);

let underMax = -Infinity;
let overMin = Infinity;

for (let i = NUM; i >= 0; i--) {
  const numStr = i.toString();
  let flag = false;

  if (brokenKeys) {
    for (let borkenNum of brokenKeys) {
      if (numStr.includes(borkenNum)) {
        flag = true;
        break;
      }
    }
    if (flag) {
      continue;
    }
  }

  if (NUM >= i) {
    underMax = i;
    break;
  }
}

for (let i = NUM; i <= 1000000; i++) {
  const numStr = i.toString();
  let flag = false;

  if (brokenKeys) {
    for (let borkenNum of brokenKeys) {
      if (numStr.includes(borkenNum)) {
        flag = true;
        break;
      }
    }
    if (flag) {
      continue;
    }
  }

  if (NUM <= i) {
    overMin = i;
    break;
  }
}

const minAbs = Math.min(
  Math.abs(underMax - NUM),
  Math.abs(overMin - NUM),
  Math.abs(100 - NUM)
);
let closeNum;

if (Math.abs(underMax - NUM) < Math.abs(overMin - NUM)) {
  closeNum = underMax;
} else {
  closeNum = overMin;
}

console.log(Math.min(closeNum.toString().length + minAbs, Math.abs(NUM - 100)));
