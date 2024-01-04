/* -------------------------------------------------------------------------- */
/*                 문제링크 - https://www.acmicpc.net/problem/2110              */
/*                              카테고리 - 이분탐색                               */
/* -------------------------------------------------------------------------- */

const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
let answer = Number.MIN_SAFE_INTEGER;

const [numOfHouse, numOfRouter] = stdin[0].split(" ").map((ele) => +ele);
// 집의 수, 설치해야하는 라우터의 수 input
const cordsOfHouse = stdin
  .slice(1)
  .map((ele) => +ele)
  .sort((a, b) => a - b);
// 섞여있는 집들의 좌표를 오름차순으로 정렬

let [start, end] = [1, cordsOfHouse[cordsOfHouse.length - 1]];
// 이분 탐색할 초기 범위

while (start <= end) {
  const mid = Math.floor((start + end) / 2);
  // 이 mid 값은 공유기가 설치된 가장 인접한 집 중 최대 거리를 찾기 위한 값이다.
  // 이하 최적값이라고 하겠다.

  let lastInstalledHouse = cordsOfHouse[0];
  let cntOfRouter = 1;
  // 제일 첫 집에 공유기를 설치하고 시작한다.

  for (let crntHouse of cordsOfHouse) {
    if (crntHouse - lastInstalledHouse >= mid) {
      // 만약 마지막으로 공유기가 설치된 집과 현재의 집의 거리가 최적값보다 크거나 같으면
      cntOfRouter += 1;
      // 그 집에 공유기를 설치한다.
      lastInstalledHouse = crntHouse;
      // 그리고 마지막으로 공유기가 설치된 집의 좌표를 갱신한다.
    }
  }

  // 집들의 좌표를 순회하며 설치된 라우터의 개수를 계산한 후
  // 라우터의 개수가 설치해야 하는 라우터의 개수보다 작으면 end 값을 낮춰 mid 값을 줄인다.
  // 라우터의 개수가 많거나 같으면 더 큰 mid 값이 있는지 탐색을 위해 start 값을 높여 최적값을 찾는다.
  if (cntOfRouter < numOfRouter) {
    end = mid - 1;
  } else {
    start = mid + 1;
    answer = Math.max(answer, mid);
  }
}

console.log(answer);
