const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const [N, M] = stdin[0].split(" ").map((ele) => +ele);
let redX, redY, blueX, blueY;
const board = stdin.slice(1).map((ele, rIdx) => ele.split(""));

board.forEach((ele, rIdx) =>
  ele.forEach((ele, cIdx) => {
    if (ele === "O") {
      [holeY, holeX] = [rIdx, cIdx];
    } else if (ele === "R") {
      [redY, redX] = [rIdx, cIdx];
    } else if (ele === "B") {
      [blueY, blueX] = [rIdx, cIdx];
    }
  })
);

// const rVisited = Array.from({ length: N }, () =>
//   Array.from({ length: M }, () => false)
// );
// const bVisited = Array.from({ length: N }, () =>
//   Array.from({ length: M }, () => false)
// );

const visited = [];

const dirVec = {
  left: [0, -1],
  right: [0, 1],
  up: [-1, 0],
  down: [1, 0],
};

const getOrder = ([rx, ry], [bx, by], dir) => {
  let firstMove = { x: rx, y: ry, color: "red" },
    lastMove = { x: bx, y: by, color: "blue" };

  if (
    dir === "left" &&
    lastMove.x < firstMove.x &&
    lastMove.y === firstMove.y
  ) {
    (firstMove = { x: bx, y: by, color: "blue" }),
      (lastMove = { x: rx, y: ry, color: "red" }); // 빨간 공보다 왼쪽에 파란공이 있으면 파란공이 먼저 움직여야하기 때문에 스왑
  } else if (
    dir === "right" &&
    lastMove.x > firstMove.x &&
    lastMove.y === firstMove.y
  ) {
    (firstMove = { x: bx, y: by, color: "blue" }),
      (lastMove = { x: rx, y: ry, color: "red" }); // 빨간 공보다 오른쪽에 파란공이 있으면 파란공이 먼저 움직여야하기 때문에 스왑
  } else if (
    dir === "up" &&
    lastMove.y < firstMove.y &&
    lastMove.x === firstMove.x
  ) {
    (firstMove = { x: bx, y: by, color: "blue" }),
      (lastMove = { x: rx, y: ry, color: "red" }); // 빨간 공보다 위쪽에 파란공이 있으면 파란공이 먼저 움직여야하기 때문에 스왑
  } else if (
    dir === "down" &&
    lastMove.y > firstMove.y &&
    lastMove.x === firstMove.x
  ) {
    (firstMove = { x: bx, y: by, color: "blue" }),
      (lastMove = { x: rx, y: ry, color: "red" }); // 빨간 공보다 위쪽에 파란공이 있으면 파란공이 먼저 움직여야하기 때문에 스왑
  }

  return [firstMove, lastMove];
};

const isWall = (x, y, dirX, dirY) => board[y + dirY][x + dirX] === "#";

const isHole = (x, y) => board[y][x] === "O";

const move = ([rx, ry], [bx, by], dir) => {
  let [firstMove, lastMove] = getOrder([rx, ry], [bx, by], dir);
  let [dirY, dirX] = dirVec[dir];
  let firstBallInfo = { isInHole: false, color: "" };
  let lastBallInfo = { isInHole: false, color: "" };

  while (true) {
    if (isWall(firstMove.x, firstMove.y, dirX, dirY)) {
      break;
    } else {
      firstMove.x += dirX;
      firstMove.y += dirY;

      if (isHole(firstMove.x, firstMove.y)) {
        if (firstMove.color === "blue") {
          firstBallInfo.color = "blue";
        } else if (firstMove.color === "red") {
          firstBallInfo.color = "red";
        }
        firstBallInfo.isInHole = true;
        break;
      }
    }
  }

  while (true) {
    if (
      isWall(lastMove.x, lastMove.y, dirX, dirY) ||
      (!firstBallInfo.isInHole &&
        firstMove.y === lastMove.y + dirY &&
        firstMove.x === lastMove.x + dirX)
    ) {
      break;
    } else {
      lastMove.x += dirX;
      lastMove.y += dirY;

      if (isHole(lastMove.x, lastMove.y)) {
        if (lastMove.color === "blue") {
          lastBallInfo.color = "blue";
        } else if (lastMove.color === "red") {
          lastBallInfo.color = "red";
        }
        lastBallInfo.isInHole = true;
        break;
      }
    }
  }

  if (
    (firstBallInfo.isInHole && firstBallInfo.color === "blue") ||
    (lastBallInfo.isInHole && lastBallInfo.color === "blue")
  ) {
    return false;
  }

  if (
    (firstBallInfo.isInHole &&
      firstBallInfo.color === "red" &&
      !lastBallInfo.isInHole) ||
    (lastBallInfo.isInHole &&
      lastBallInfo.color === "red" &&
      !firstBallInfo.isInHole)
  ) {
    return true;
  }

  if (firstMove.color === "red") {
    return [
      [firstMove.x, firstMove.y],
      [lastMove.x, lastMove.y],
    ];
  } else {
    return [
      [lastMove.x, lastMove.y],
      [firstMove.x, firstMove.y],
    ];
  }
};

const bfs = () => {
  const queue = [];
  queue.push([
    [
      [redX, redY],
      [blueX, blueY],
    ],
  ]);
  let cnt = 0;

  visited.push(queue[0]);

  while (queue.length !== 0) {
    const crntCoordsArr = queue.shift();
    if (cnt >= 10) return -1;
    cnt += 1;
    const resultArr = [];
    for (let crntCoord of crntCoordsArr) {
      for (let dir in dirVec) {
        const result = move(...crntCoord, dir);
        // true = > 빨간 공이 들어감, false = > 파란 공이 들어감, 둘 다 아니면 마지막 공들의 좌표
        // 배열의 첫 인자가 red의 [x, y];
        if (result === true) {
          return cnt;
        } else if (result === false) {
          continue;
        } else {
          if (visited.includes(`${result}`)) {
            continue;
          }
          visited.push(`${result}`);
          resultArr.push(result);
        }
      }
    }

    queue.push(resultArr);
  }
};

console.log(bfs());
