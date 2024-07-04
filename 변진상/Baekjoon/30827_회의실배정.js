const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => ele.split(" ").map((ele) => +ele));

const [N, K] = stdin[0];
const timeArr = stdin.slice(1);
const lastTimeArrOfRoom = new Array(K).fill(0);

timeArr.sort((a, b) => b[0] - a[0]).sort((a, b) => a[1] - b[1]);

let answer = 0;
console.log(timeArr);
for (let [start, end] of timeArr) {
  for (let i = 0; i < lastTimeArrOfRoom.length; i++) {
    const lastTime = lastTimeArrOfRoom[i];
    if (lastTime >= start) continue;

    lastTimeArrOfRoom[i] = end;

    answer += 1;
    console.log(start, end, lastTimeArrOfRoom);
    // lastTimeArrOfRoom.sort((a, b) => b - a);
    break;
  }
}

console.log(answer);
