const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const initInput = stdin[0];
const baseString = stdin[1];

const dollarNum = baseString.split("").reduce((acc, crnt) => {
  if (crnt === "$") return acc + 1;
  else return acc;
}, 0);

const times = +stdin[2];

const [start, end] = stdin[3].split(" ").map((ele) => +ele);

let tmpAnswerString = [];

let _replaceDollar = (input) => {
  let tmpStr = "";

  for (let i = 0; i < baseString.length; i++) {
    const crntChar = baseString[i];

    if (crntChar === "$") {
      tmpStr += input;
    } else {
      tmpStr += crntChar;
    }

    // if (input.length * dollarNum + (baseString.length - dollarNum) > end) break;

    if (tmpStr.length > end) break;
  }

  return tmpStr;
};

const recursiveEndless = (N, times, input) => {
  if (times === N) return;

  tmpAnswerString = _replaceDollar(input);
  recursiveEndless(N + 1, times, tmpAnswerString);
  if (input.length * dollarNum + (baseString.length - dollarNum) > end) return;
};

recursiveEndless(0, times, initInput);

if (start <= tmpAnswerString.length && end <= tmpAnswerString.length) {
  console.log(tmpAnswerString.slice(start - 1, end));
} else if (start > tmpAnswerString.length || end > tmpAnswerString.length) {
  const hyphenNum = end - tmpAnswerString.length;

  let slicedString = tmpAnswerString.slice(start - 1);
  console.log(slicedString.concat("-".repeat(hyphenNum)));
}

// console.log("3".repeat(1000000000 - 100000000));

const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const s = stdin[0];
const input = stdin[1];

let repeat = +stdin[2];

const [start, finish] = stdin[3].split(" ").map((ele) => +ele);

const MAX = 31;

let dollar = 0,
  nonDollar = 0;

let tmpArr = new Array(MAX).fill(0);
let found = false;

let answer = "";

function searchChar(level, idx) {
  let pos = 0;
  for (let i = 0; !found && i < input.length; i++) {
    if (input[i] === "$") {
      if (level === 1) {
        if (idx <= pos + cache[level] - 1) {
          found = true;
          answer += s[idx - pos];
        } else {
          pos += cache[level];
        }
      } else if (level > 1) {
        if (idx <= pos + cache[level] - 1) {
          searchChar(level - 1, idx - pos);
        } else {
          pos += cache[level];
        }
      }
    } else {
      if (pos === idx) {
        found = true;
        answer += input[i];
        return;
      } else {
        pos++;
      }
    }
  }
}

function replace() {
  for (let i = 0; i < input.length; i++) {
    if (input[i] === "$") {
      dollar++;
    } else {
      nonDollar++;
    }
  }

  cache[1] = s.length;

  for (let i = 2; i <= repeat; i++) {
    cache[i] = cache[i - 1] * dollar + nonDollar;

    if (cache[i] > finish) {
      repeat = i;
      break;
    }
  }

  for (let i = start - 1; i < finish; i++) {
    let temp = repeat;

    if (i >= cache[temp] * dollar + nonDollar) {
      answer += "-";
      continue;
    }

    while (temp > 1 && cache[temp] > i) {
      temp--;
    }

    found = false;
    searchChar(temp, i);
  }
}

main();

console.log(answer);
