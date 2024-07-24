const { createPublicKey } = require("crypto");
const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const baseStr = stdin[0];
const explosiveStr = stdin[1];

const stack = [];

const isExplosiveStrInStack = (idx, stack) => {
  if (idx < explosiveStr.length - 1) return false;
  for (let i = 0; i < explosiveStr.length; i++) {
    if (explosiveStr[i] !== stack[idx - explosiveStr.length + 1 + i]) {
      return false;
    }
  }

  return true;
};

let idx = 0;

for (let i = 0; i < baseStr.length; i++) {
  const char = baseStr[i];
  stack[idx] = char;

  if (isExplosiveStrInStack(idx, stack)) {
    idx -= explosiveStr.length;
  }
  idx++;
}

console.log(idx === 0 ? "FRULA" : stack.slice(0, idx).join(""));
