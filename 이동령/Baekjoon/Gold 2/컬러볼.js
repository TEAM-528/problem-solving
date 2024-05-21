const input = require("fs")
  .readFileSync(process.platform === "linux" ? "/dev/stdin" : "input.txt")
  .toString()
  .split("\n");

const [N, ...inputs] = input.map((str) => str.split(" ").map((e) => +e));
const result = Array(N).fill(0);
const colorSum = Array(200001).fill(0);
let [totalSum, start] = [0, 0];
const balls = inputs
  .map(([color, size], index) => {
    return {
      color,
      size,
      index,
    };
  })
  .sort((a, b) => a.size - b.size);

while (start < N) {
  let end = start;
  while (end < N && balls[start].size === balls[end].size) end++;
  for (let i = start; i < end; i++) {
    result[balls[i].index] = totalSum - colorSum[balls[i].color];
  }
  for (let i = start; i < end; i++) {
    totalSum += balls[i].size;
    colorSum[balls[i].color] += balls[i].size;
  }
  start = end;
}

console.log(result.join("\n"));
