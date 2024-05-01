const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => ele.split(" ").map((ele) => +ele));

const [N, M, K] = stdin[0];
const candyNumArr = [...stdin[1]];
const edgeInfo = stdin.slice(2);

const unionList = new Array(N + 1).fill(0);
for (let i = 0; i <= N; i++) {
  unionList[i] = i;
}

const findRoot = (node) => {
  if (unionList[node] === node) return node;

  return (unionList[node] = findRoot(unionList[node]));
};
const union = (a, b) => {
  let parentA = findRoot(a);
  let parentB = findRoot(b);

  if (parentA < parentB) {
    unionList[parentB] = parentA;
  } else {
    unionList[parentA] = parentB;
  }
};

edgeInfo.forEach(([f1, f2]) => {
  union(f1, f2);
});

const friendNumCostArr = Array.from({ length: N + 1 }, () => [0, 0]);

for (let i = 1; i < N + 1; i++) {
  const root = findRoot(i);
  friendNumCostArr[root][0] += 1;
  friendNumCostArr[root][1] += candyNumArr[i - 1];
}

const dp = Array.from({ length: K }, () => 0);

for (let i = 1; i <= N; i++) {
  if (findRoot(i) !== i) continue;
  for (let j = K - 1; j >= 0; j--) {
    const [friendNum, cost] = friendNumCostArr[i];
    if (j - friendNum >= 0) {
      dp[j] = Math.max(dp[j], dp[j - friendNum] + cost);
    }
  }
}

console.log(Math.max(...dp));
