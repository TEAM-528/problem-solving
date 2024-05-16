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
const candies = [...stdin[1]];
const edges = stdin.slice(2);

const dp = Array.from({ length: K }, () => 0);
const knapsack = Array.from({ length: N + 1 }, () => [0, 0]);

const unionArr = new Array(N + 1).fill(0);
for (let i = 0; i <= N; i++) {
  unionArr[i] = i;
}

const findRoot = (node) => {
  if (unionArr[node] === node) return node;
  return (unionArr[node] = findRoot(unionArr[node]));
};

const union = (node1, node2) => {
  let rootA = findRoot(node1);
  let rootB = findRoot(node2);
  if (rootA < rootB) unionArr[node2] = node1;
  else unionArr[node1] = node2;
};

edges.forEach(([a, b]) => {
  union(a, b);
});

for (let i = 1; i <= N; i++) {
  const root = findRoot(i);
  knapsack[root][0] += 1;
  knapsack[root][1] += candies[i - 1];
}

for (let i = 1; i <= N; i++) {
  if (findRoot(i) !== i) continue;
  const [w, v] = knapsack[i];
  for (let j = K - 1; j >= 0; j--) {
    if (w <= j) dp[j] = Math.max(dp[j], v + dp[j - w]);
  }
}

console.log(Math.max(...dp));
