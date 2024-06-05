// const fs = require("fs");
// const stdin = fs
//   .readFileSync(
//     "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
//   )
//   .toString()
//   .trim()
//   .split("\n");

// const N = +stdin[0];
// const book = stdin[1];
// const answer = [];

// const sentenceArr = book.split(/[.|?|!]/).map((ele) => ele.trim().split(" "));
// const digitRegex = /[0-9]/;

// sentenceArr.forEach((sentence) => {
//   let cnt = 0;
//   let flag = true;

//   sentence.forEach((word) => {
//     if (!word[0]) {
//       flag = false;
//       return;
//     }
//     if (word[0] === word[0].toUpperCase() && !word.match(digitRegex)) {
//       cnt += 1;
//     }
//   });

//   if (flag) {
//     answer.push(cnt);
//   }
// });

// console.log(answer.join("\n"));

const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];
rl.on("line", function (line) {
  input.push(line);
}).on("close", function () {
  const N = +input[0];
  const book = input[1];
  const answer = [];

  const sentenceArr = book.split(/[.|?|!]/).map((ele) => ele.trim().split(" "));
  const digitRegex = /[0-9]/;

  sentenceArr.forEach((sentence) => {
    let cnt = 0;
    let flag = true;

    sentence.forEach((word) => {
      if (!word[0]) {
        flag = false;
        return;
      }
      if (word[0] === word[0].toUpperCase() && !word.match(digitRegex)) {
        cnt += 1;
      }
    });

    if (flag) {
      answer.push(cnt);
    }
  });

  console.log(answer.join("\n"));

  process.exit();
});
