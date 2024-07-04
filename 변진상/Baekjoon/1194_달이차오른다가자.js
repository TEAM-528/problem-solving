const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const [N, M] = stdin[0].split(" ").map((ele) => +ele);
const map = stdin.slice(1).map((ele) => ele.split(""));

const keyMap = Array.from({ length: N }, () =>
  Array.from({ length: M }, () => "000000")
);

const dirVec = [
  [1, 0],
  [-1, 0],
  [0, -1],
  [0, 1],
]; // 상하좌우

const isAccesible = ([y, x], [dY, dX]) =>
  y + dY >= 0 && y + dY < N && x + dX >= 0 && x + dX < M;

const isMinsik = ([y, x]) => map[y][x] === "0";

const isDoor = ([y, x]) => {
  switch (map[y][x]) {
    case "A":
    case "B":
    case "C":
    case "D":
    case "E":
    case "F":
      return true;
    default:
      return false;
  }
};

const isKey = ([y, x]) => {
  switch (map[y][x]) {
    case "a":
    case "b":
    case "c":
    case "d":
    case "e":
    case "f":
      return true;
    default:
      return false;
  }
};

const makeNewKeySet = (prevKeySet, key) => {
  let newKeyArr = prevKeySet.split("");

  const idx = key.charCodeAt() - 97;
  newKeyArr[idx] = 1;

  return newKeyArr.join("");
};

const getMinsikCoords = () => {
  let minsikCoords;

  for (let y = 0; y < N; y++) {
    for (let x = 0; x < M; x++) {
      if (isMinsik([y, x])) {
        minsikCoords = [y, x];
      }
    }
  }

  return [...minsikCoords];
};

const [mY, mX] = getMinsikCoords();

const bfs = ([startY, startX]) => {
  const queue = [];
  queue.push([startY, startX, 0, 0]);
  const visited = Array.from({ length: 64 }, () =>
    Array.from({ length: N }, () => Array.from({ length: M }, () => false))
  );

  visited[0][startY][startX] = true;

  while (queue.length) {
    const [crntY, crntX, cnt, keySet] = queue.shift();

    if (map[crntY][crntX] === "1") {
      return cnt;
    }

    for (let [dY, dX] of dirVec) {
      if (!isAccesible([crntY, crntX], [dY, dX])) continue;

      const [nextY, nextX] = [dY + crntY, dX + crntX];

      if (visited[keySet][nextY][nextX]) continue;
      if (map[nextY][nextX] === "#") continue;

      if (
        map[nextY][nextX] === "." ||
        map[nextY][nextX] === "0" ||
        map[nextY][nextX] === "1"
      ) {
        visited[keySet][nextY][nextX] = true;
        queue.push([nextY, nextX, cnt + 1, keySet]);
      } else if (isKey([nextY, nextX])) {
        const key = 1 << (map[nextY][nextX].charCodeAt() - 97);
        const newKeySet = key | keySet;

        if (!visited[newKeySet][nextY][nextX]) {
          visited[keySet][nextY][nextX] = true;
          visited[newKeySet][nextY][nextX] = true;
          queue.push([nextY, nextX, cnt + 1, newKeySet]);
        }
      } else if (isDoor([nextY, nextX])) {
        const door = 1 << (map[nextY][nextX].toLowerCase().charCodeAt() - 97);

        if (!(keySet & door)) {
          // 키를 가지고 있지 않다면
          continue;
        }

        visited[keySet][nextY][nextX] = true;

        queue.push([nextY, nextX, cnt + 1, keySet]);
      }
    }
  }

  return -1;
};

const answer = bfs([mY, mX]);
console.log(answer);

console.log("a".charCodeAt());
