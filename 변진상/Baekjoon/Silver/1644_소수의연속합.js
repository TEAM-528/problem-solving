const fs = require("fs");
const stdin = +fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim();

const getPrimeArr = () => {
  const arr = new Array(stdin + 1).fill(true);
  const N = Math.ceil(Math.sqrt(stdin + 1));
  const result = [];

  arr[0] = false;
  arr[1] = false;

  for (let i = 2; i <= N; i++) {
    for (let j = 2; i * j < arr.length; j++) {
      arr[i * j] = false;
    }
  }

  arr.forEach((ele, idx) => {
    if (ele) {
      result.push(idx);
    }
  });

  return result;
};

if (stdin === 1) {
  console.log(0);
} else {
  const primeArr = getPrimeArr();

  let [lt, rt] = [0, 0];
  let sum = primeArr[lt];
  let answer = 0;

  while (lt <= rt) {
    if (sum <= stdin) {
      if (sum === stdin) answer += 1;
      rt++;
      sum += primeArr[rt];
      if (rt >= primeArr.length) {
        break;
      }
    } else if (sum > stdin) {
      sum -= primeArr[lt];
      lt++;
    }
  }

  console.log(answer);
}
