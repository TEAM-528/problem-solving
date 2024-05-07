const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => ele.split(" ").map((ele) => +ele));

const N = stdin[0][0];
const poleArr = stdin[1];
const LISArr = [];

const BinSearch = (num) => {
  let [lt, rt] = [0, LISArr.length - 1];

  while (lt < rt) {
    let mid = Math.floor((lt + rt) / 2);

    if (LISArr[mid] < num) {
      lt = mid + 1;
    } else {
      rt = mid;
    }
  }
  return rt;
};

const LIS = () => {
  poleArr.forEach((poleNum) => {
    if (LISArr.length === 0 || LISArr[LISArr.length - 1] < poleNum) {
      LISArr.push(poleNum);
    } else {
      LISArr[BinSearch(poleNum)] = poleNum;
    }
  });
};

LIS();

LISArr;
console.log(N - LISArr.length);
