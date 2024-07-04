const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => ele.split(" ").map((ele) => +ele));

const [N] = stdin[0];
const map = stdin.slice(1);
const distanceMap = Array.from({ length: N }, () =>
  Array.from({ length: N }, () => 0)
);

let sizeOfShark = 2;
let expCount = 0;
let seconds = 0;

let dirVec = [
  [-1, 0],
  [1, 0],
  [0, -1],
  [0, 1],
]; // 좌우상하

const getInitSharkCoordAndFishCnt = () => {
  let SharkCoord;
  const fishCnt = new Array(7).fill(0);

  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (map[i][j] === 9) {
        SharkCoord = [j, i];
        map[i][j] = 0;
      } else {
        if (map[i][j] !== 0) {
          fishCnt[map[i][j]] += 1;
        }
      }
    }
  }
  return [...SharkCoord, fishCnt];
};

let [sharkX, sharkY, fishCntArr] = getInitSharkCoordAndFishCnt();

const isFishEdible = (x, y, sizeOfShark) => {
  return map[y][x] < sizeOfShark && map[y][x] !== 0;
};

const updateEdibleFishDistanceMap = (x, y) => {
  let [tmpX, tmpY, tmpDistance] = [
    Number.MAX_SAFE_INTEGER,
    Number.MAX_SAFE_INTEGER,
    Number.MAX_SAFE_INTEGER,
  ];
  const queue = [];
  const visited = [];
  let dist = 0;

  queue.push([x, y]);
  distanceMap[y][x] = dist;

  while (queue.length !== 0) {
    const [crntX, crntY] = queue.shift();
    if (visited.includes(`${crntX},${crntY}`)) continue;

    visited.push(`${crntX},${crntY}`);
    for (let [dirX, dirY] of dirVec) {
      const [nextX, nextY] = [crntX + dirX, crntY + dirY];

      if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;

      if (sizeOfShark >= map[nextY][nextX]) {
        if (visited.includes(`${nextX},${nextY}`)) continue;
        queue.push([nextX, nextY]);

        distanceMap[nextY][nextX] = distanceMap[crntY][crntX] + 1;

        if (isFishEdible(nextX, nextY, sizeOfShark)) {
          if (tmpDistance > distanceMap[nextY][nextX]) {
            tmpX = nextX;
            tmpY = nextY;
            tmpDistance = distanceMap[nextY][nextX];
          } else if (tmpDistance === distanceMap[nextY][nextX]) {
            if (tmpY > nextY) {
              tmpX = nextX;
              tmpY = nextY;
              tmpDistance = distanceMap[nextY][nextX];
            } else if (tmpY === nextY) {
              if (tmpX > nextX) {
                tmpX = nextX;
                tmpY = nextY;
                tmpDistance = distanceMap[nextY][nextX];
              }
            }
          }
        }
      }
    }
  }

  return [tmpX, tmpY, tmpDistance];
};

const isAnyFishEdible = (currentSize) => {
  for (let i = currentSize - 1; i >= 0; i--) {
    if (fishCntArr[i] > 0) return true;
  }

  return false;
};

// 맵을 돌며 먹을 수 있는 물고기인지 체크
// 잡아 먹을 물고기가 있는지 체크 -> 없으면 엄마 호출 및 종료
// 먹을 수 있는 물고기 좌표 체크 후 거리 계산
// 방문 -> 잡아 먹기

const goAndEatFish = (nextX, nextY, dist) => {
  expCount += 1;
  seconds += dist;

  if (expCount === sizeOfShark) {
    sizeOfShark += 1;
    expCount = 0;
  }

  fishCntArr[map[nextY][nextX]] -= 1;
  map[nextY][nextX] = 0;

  sharkX = nextX;
  sharkY = nextY;
};

while (true) {
  if (!isAnyFishEdible(sizeOfShark)) break;

  let [tmpX, tmpY, tmpDistance] = updateEdibleFishDistanceMap(sharkX, sharkY);

  if (tmpX < 0 || tmpY < 0 || tmpX >= N || tmpY >= N) break;
  goAndEatFish(tmpX, tmpY, tmpDistance);
}

console.log(seconds);
