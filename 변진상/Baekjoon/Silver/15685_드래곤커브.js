const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const N = +stdin[0];
const curveArr = stdin.slice(1).map((ele) => ele.split(" ").map((ele) => +ele));

const map = Array.from({ length: 101 }, () => new Array(101).fill(false));

const directionVector = [
  [0, 1], // 우
  [-1, 0], // 상
  [0, -1], // 좌
  [1, 0], // 하
];

const getTotalDirectionArrByGen = (startDirection, generation) => {
  const directionArr = [startDirection];

  for (let gen = 0; gen < generation; gen++) {
    for (let i = directionArr.length - 1; i >= 0; i--) {
      directionArr.push((directionArr[i] + 1) % 4);
    }
  }

  return directionArr;
};

const getDragonCoord = (startX, startY, directionArr) => {
  let coordArr = [[0, 0]];

  for (const dir of directionArr) {
    coordArr.push([
      coordArr[coordArr.length - 1][0] + directionVector[dir][0],
      coordArr[coordArr.length - 1][1] + directionVector[dir][1],
    ]);
  }

  coordArr = coordArr.map(([y, x]) => [y + startY, x + startX]);

  return coordArr;
};

const getCntRect = (map) => {
  let cnt = 0;
  for (let y = 0; y < 100; y++) {
    for (let x = 0; x < 100; x++) {
      if (map[y][x] && map[y + 1][x] && map[y][x + 1] && map[y + 1][x + 1]) {
        cnt += 1;
      }
    }
  }
  return cnt;
};

for (const curve of curveArr) {
  const [x, y, d, g] = curve;

  for (const [point_y, point_x] of getDragonCoord(
    x,
    y,
    getTotalDirectionArrByGen(d, g)
  )) {
    map[point_y][point_x] = true;
  }
}

console.log(map);

console.log(getCntRect(map));
