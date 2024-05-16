const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const [multitabNum, toolNum] = stdin[0].split(" ").map((ele) => +ele);
const toolArr = stdin[1].split(" ").map((ele) => +ele);
const multitab = [];

let answer = 0;

toolArr.forEach((tool, crntToolIndex) => {
  if (multitab.includes(tool)) {
    return;
  } else {
    if (multitab.length < multitabNum) {
      multitab.push(tool);
    } else {
      let farTool = [0, Number.MIN_SAFE_INTEGER, -1];
      for (let i = 0; i < multitabNum; i++) {
        let toolInMultitab = multitab[i];
        let dist = 0;

        for (let j = crntToolIndex + 1; j < toolNum; j++) {
          if (toolArr[j] === toolInMultitab) {
            break;
          }
          dist += 1;
        }

        if (farTool[1] < dist) {
          farTool[0] = i;
          farTool[1] = dist;
        }
      }

      multitab[farTool[0]] = tool;

      answer += 1;
    }
  }
});

console.log(answer);
