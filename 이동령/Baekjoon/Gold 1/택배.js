const input = require("fs")
  .readFileSync(process.platform === "linux" ? "/dev/stdin" : "input.txt")
  .toString()
  .trim()
  .split("\n");

const [[N, M], _, ...inputs] = input.map((str) =>
  str.split(" ").map((e) => +e)
);
let result = 0,
  boxes = [],
  capacity = Array.from({ length: N + 1 }, () => M);
inputs.forEach(([from, to, count]) => {
  boxes.push({ from: from, to: to, count: count });
});
boxes.sort((a, b) => a.to - b.to);

boxes.forEach(({ from, to, count }) => {
  let min = M;
  for (let i = from; i < to; i++) min = Math.min(capacity[i], min, count);
  for (let i = from; i < to; i++) capacity[i] -= min;
  result += min;
});

console.log(result);
