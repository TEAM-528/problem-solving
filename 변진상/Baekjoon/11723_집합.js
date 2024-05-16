const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");
const N = +stdin[0];
const instArr = stdin.slice(1).map((ele) => ele.split(" "));
let sSet = new Set();

const answerArr = [];

const add = (x) => {
  sSet.add(x);
};

const remove = (x) => {
  if (sSet.has(x)) {
    sSet.delete(x);
  }
};

const check = (x) => {
  if (sSet.has(x)) return 1;
  else return 0;
};

const toggle = (x) => {
  if (sSet.has(x)) {
    sSet.delete(x);
  } else {
    sSet.add(x);
  }
};

const all = () => {
  for (let i = 1; i <= 20; i++) {
    sSet.add(i);
  }
};

const empty = () => {
  sSet = new Set([]);
};

instArr.forEach(([inst, x]) => {
  console.log(inst);
  switch (inst) {
    case "add":
      add(x);
      break;
    case "remove":
      remove(x);
      break;
    case "check":
      answerArr.push(check(x));
      break;
    case "toggle":
      toggle(x);
      break;
    case "all":
      all();
      break;
    case "empty":
      empty();
      break;
  }
});

console.log(answerArr.join(" "));
