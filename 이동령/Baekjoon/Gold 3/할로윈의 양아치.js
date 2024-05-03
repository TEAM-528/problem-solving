const input = require("fs")
  .readFileSync(process.platform === "linux" ? "/dev/stdin" : "input.txt")
  .toString()
  .split("\n");

const [[N, M, K], candies, ...edges] = input.map((str) =>
  str.split(" ").map((e) => +e)
);
const knapsack = {};
const dp = Array.from({ length: K + 1 }, () => 0);
const unionArr = Array.from({ length: N + 1 }, (_, i) => i);

const findRoot = (node) => {
  return unionArr[node] === node
    ? node
    : (unionArr[node] = findRoot(unionArr[node]));
};

const union = (node1, node2) => {
  let [rootA, rootB] = [findRoot(node1), findRoot(node2)];
  if (rootA < rootB) unionArr[rootA] = rootB;
  else unionArr[rootB] = rootA;
};

edges.forEach(([a, b]) => union(a, b));
for (let i = 1; i <= N; i++) {
  let root = findRoot(i);
  if (!knapsack[root]) knapsack[root] = { child: 0, candy: 0 };
  knapsack[root].child++;
  knapsack[root].candy += candies[i - 1];
}

Object.values(knapsack).forEach(({ candy: v, child: w }) => {
  for (let i = K; i >= 1; i--) {
    if (w < i) dp[i] = Math.max(dp[i], v + dp[i - w]);
  }
});

console.log(dp[K]);
