const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const [M, N] = stdin[0].split(" ").map((ele) => +ele);
const tomatoInfoArr = stdin
  .slice(1)
  .map((ele) => ele.split(" ").map((ele) => +ele));
const dirVector = [
  [-1, 0],
  [1, 0],
  [0, -1],
  [0, 1],
];
const queue = [];
let cnt = 0;

const isAbleEveryTomatoesRipen = () => {
  let flag = true;

  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (tomatoInfoArr[i][j] === 0) {
        flag = false;
      }
    }
  }
  return flag;
};

const getRipeTomatoesCoordsArr = () => {
  const coordsArr = [];

  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (tomatoInfoArr[i][j] === 1) {
        coordsArr.push(`${i} ${j}`);
      }
    }
  }

  return coordsArr;
};

const BFS = () => {
  queue.push(getRipeTomatoesCoordsArr());
  while (queue.length !== 0) {
    const ripeTomatoesArr = queue.shift();
    const tmpArr = [];
    for (let tomato of ripeTomatoesArr) {
      let [tomato_y, tomato_x] = tomato.split(" ").map((ele) => +ele);
      for (let [x, y] of dirVector) {
        if (
          tomato_x + x < 0 ||
          tomato_x + x >= M ||
          tomato_y + y < 0 ||
          tomato_y + y >= N
        )
          continue;
        if (tomatoInfoArr[tomato_y + y][tomato_x + x] === 1) continue;
        if (tomatoInfoArr[tomato_y + y][tomato_x + x] === 0) {
          tomatoInfoArr[tomato_y + y][tomato_x + x] = 1;
          tmpArr.push(`${tomato_y + y} ${tomato_x + x}`);
        }
      }
    }
    if (tmpArr.length === 0) return;
    queue.push(tmpArr);

    cnt += 1;
  }
};

BFS();

console.log(isAbleEveryTomatoesRipen(tomatoInfoArr) ? cnt : -1);
