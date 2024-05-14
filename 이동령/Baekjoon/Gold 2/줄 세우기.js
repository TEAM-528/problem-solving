const input = require("fs")
  .readFileSync(process.platform === "linux" ? "/dev/stdin" : "input.txt")
  .toString()
  .split("\n");

const [N, childs] = input.map((str) => str.split(" ").map((e) => +e));
const dp = Array.from({ length: N + 1 }, () => 0);
let max = 0;

childs.forEach((e) => {
  dp[e] = Math.max(dp[e], dp[e - 1] + 1);
  max = Math.max(max, dp[e]);
});

console.log(N - max);
// max값을 단순히 Math.max와 spread 연산자를 이용하면 StackSize Esceeded 발생
// 1,000,000까지의 값을 가질 수 있기 때문에 이를 고려하여 max값 지속적으로 tracking
