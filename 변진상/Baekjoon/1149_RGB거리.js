const fs = require("fs");
// const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");
const N = +stdin[0];
const rgbArr = stdin.slice(1).map((ele) => ele.split(" ").map((ele) => +ele));
const dp = Array.from({ length: N - 1 }, () => [0, 0, 0]);
dp.unshift([...rgbArr[0]]);

let idx = 0;

while (idx !== rgbArr.length - 1) {
  const prevHouseCosts = dp[idx];

  for (let crntRgbIdx = 0; crntRgbIdx < 3; crntRgbIdx++) {
    // 다음 집의 R, G, B의 모든 경우에서 최소 비용을 소요하는 경로를 탐색
    const prevColorSet = [...prevHouseCosts];
    prevColorSet.splice(crntRgbIdx, 1);
    // 이전 집에서 선택한 색을 선택하지 않으면 색을 칠할 수 있는 조건 만족
    // 이전 집들의 색 중 현재

    dp[idx + 1][crntRgbIdx] =
      Math.min(...prevColorSet) + rgbArr[idx + 1][crntRgbIdx];
  }

  idx++;
}

console.log(Math.min(...dp[dp.length - 1]));
