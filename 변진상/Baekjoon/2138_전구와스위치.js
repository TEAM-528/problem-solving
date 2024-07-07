// const fs = require("fs");
// const { CLIENT_RENEG_LIMIT } = require("tls");
// const stdin = fs
//   .readFileSync(
//     "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
//   )
//   .toString()
//   .trim()
//   .split("\n");

// const N = +stdin[0];

// const initialState = parseInt(stdin[1], 2);
// const goalState = parseInt(stdin[2], 2);

// let answer = Number.MAX_SAFE_INTEGER;

// const dfs = (idx, cnt, initState) => {
//   if (initState === goalState) {
//     answer = Math.min(answer, cnt);
//     return;
//   }

//   if (idx === N) {
//     return;
//   }

//   if (idx === 0) {
//     const nextState = initState ^ (1 << (N - idx - 1)) ^ (1 << (N - idx - 2));
//     console.log(nextState);
//     dfs(idx + 1, cnt, initState);
//     dfs(idx + 1, cnt + 1, nextState);
//   } else if (idx === N - 1) {
//     const nextState = initState ^ (1 << (N - idx)) ^ (1 << (N - idx - 1));
//     dfs(idx + 1, cnt, initState);
//     dfs(idx + 1, cnt + 1, nextState);
//   } else {
//     const nextState =
//       initState ^
//       (1 << (N - idx - 2)) ^
//       (1 << (N - idx - 1)) ^
//       (1 << (N - idx));

//     if (
//       (nextState & (1 << (N - idx - 2))) ===
//       (goalState & (1 << (N - idx - 2)))
//     ) {
//       dfs(idx + 1, cnt + 1, nextState);
//     } else {
//       dfs(idx + 1, cnt, initState);
//     }
//   }
// };

// dfs(0, 0, initialState);

// console.log(answer === Number.MAX_SAFE_INTEGER ? -1 : answer);

const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const N = +stdin[0];
let initialState = stdin[1];
const goalState = stdin[2];

const flip = (state, idx) => {
  if (idx === 0) {
    return (
      (state[0] === "1" ? "0" : "1") +
      (state[1] === "1" ? "0" : "1") +
      state.slice(2)
    );
  } else if (idx === state.length - 1) {
    return (
      state.slice(0, idx - 1) +
      (state[idx - 1] === "1" ? "0" : "1") +
      (state[idx] === "1" ? "0" : "1")
    );
  } else {
    return (
      state.slice(0, idx - 1) +
      (state[idx - 1] === "1" ? "0" : "1") +
      (state[idx] === "1" ? "0" : "1") +
      (state[idx + 1] === "1" ? "0" : "1") +
      state.slice(idx + 2)
    );
  }
};

let firstStringFlipped = flip(initialState, 0);

let answer = initialState === goalState ? 0 : Number.MAX_SAFE_INTEGER;

let cnt1 = 0;
for (let i = 1; i < N; i++) {
  const flippedStr = flip(initialState, i);

  if (flippedStr[i - 1] === goalState[i - 1]) {
    cnt1 += 1;
    initialState = flippedStr;

    if (initialState === goalState) {
      answer = Math.min(answer, cnt1);
      break;
    }
  }
}

let cnt2 = 1;
for (let i = 1; i < N; i++) {
  const flippedStr = flip(firstStringFlipped, i);

  if (flippedStr[i - 1] === goalState[i - 1]) {
    cnt2 += 1;
    firstStringFlipped = flippedStr;

    if (firstStringFlipped === goalState) {
      answer = Math.min(answer, cnt2);
      break;
    }
  }
}

console.log(answer === Number.MAX_SAFE_INTEGER ? -1 : answer);
