const fs = require("fs");
const [r1, c1, r2, c2] = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split(" ")
  .map((ele) => +ele);

const dirVector = [
  [-1, 0],
  [0, -1],
  [1, 0],
  [0, 1],
];

const getFullMap = () => {
  const map = Array.from({ length: 1000 }, () => new Array(1000).fill(0));
  let [x, y] = [5000, 5000];

  let numCount = 1;
  let numCntByLevel = 1;

  // while (numCount <= 10001) {
  //   if (numCntByLevel === 1) {
  //     map[y][x] = numCount;
  //     numCount += 1;
  //     numCntByLevel = 8;
  //   } else {
  //     for (let i = 0; i < parseInt(numCntByLevel / 4) - 1; i++) {
  //       map[y][x] = numCount;
  //       numCount += 1;
  //       y -= 1;
  //     }
  //     for (let i = 0; i < parseInt(numCntByLevel / 4) - 1; i++) {
  //       map[y][x] = numCount;
  //       numCount += 1;
  //       x -= 1;
  //     }
  //     for (let i = 0; i < parseInt(numCntByLevel / 4) - 1; i++) {
  //       map[y][x] = numCount;
  //       numCount += 1;
  //       y += 1;
  //     }
  //     for (let i = 0; i < parseInt(numCntByLevel / 4) - 1; i++) {
  //       map[y][x] = numCount;
  //       numCount += 1;
  //       x += 1;
  //     }
  //   }
  //   numCntByLevel += 8;
  //   x += 1;
  // }

  console.log(map);
};

getFullMap();
