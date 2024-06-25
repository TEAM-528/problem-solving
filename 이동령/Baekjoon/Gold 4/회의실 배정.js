const input = require("fs")
  .readFileSync(process.platform === "linux" ? "/dev/stdin" : "input.txt")
  .toString()
  .trim()
  .split("\n");

const [[N, K], ...inputs] = input.map((str) => str.split(" ").map((e) => +e));
let result = 0;
let rooms = Array.from({ length: K }, () => 0);

inputs.sort((a, b) => a[1] - b[1]);

inputs.forEach(([start, end]) => {
  for (let i = 0; i < K; i++) {
    if (rooms[i] < start) {
      rooms[i] = end;
      result++;
      break;
    }
  }
  rooms.sort((a, b) => b - a);
});

console.log(result);
