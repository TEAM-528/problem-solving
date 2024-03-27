const fs = require("fs");
const [x, y, d, t] = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split(" ")
  .map((ele) => +ele);

const calcDist = (x, y) => Math.sqrt(x ** 2 + y ** 2);

const getSec = (dist, jumpDistance, jumpTime) => {
  let result = 0;
  let jumpCnt = parseInt(dist / jumpDistance);
  let restTime = dist % jumpDistance;
  let oneMoreJumpRestDist = (jumpCnt + 1) * jumpDistance - dist;

  if (jumpDistance <= jumpTime) {
    return dist;
  } else if (dist < jumpDistance) {
    return Math.min(jumpTime + oneMoreJumpRestDist, jumpTime * 2, dist);
  } else {
    result = Math.min(
      jumpCnt * jumpTime + restTime,
      (jumpCnt + 1) * jumpTime + oneMoreJumpRestDist,
      (jumpCnt + 1) * jumpTime
    );

    return result;
  }
};

console.log(getSec(calcDist(x, y), d, t));
