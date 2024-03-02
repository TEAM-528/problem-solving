var input = require("fs")
  .readFileSync(process.platform === "linux" ? "/dev/stdin" : "input.txt")
  .toString()
  .split("\n");

let [[N, K], [...appliances]] = input.map((str) =>
  str.split(" ").map((e) => +e)
);

let powerBar = new Set();
let index = Array.from({ length: K + 1 }, () => []);
for (let i = appliances.length - 1; i >= 0; i--) index[appliances[i]].push(i);

let counter = 0;
appliances.forEach((e) => {
  if (!powerBar.has(e)) {
      if (powerBar.size < N) powerBar.add(e);
      else {
        let [pop, max] = [0, 0];
        powerBar.forEach((v) => {
          if (index[v].length === 0) [pop, max] = [v, Infinity];
          else if (max < index[v].at(-1)) [pop, max] = [v, index[v].at(-1)];
        });
        powerBar.delete(pop);
        powerBar.add(e);
        counter++;
      }
  }
  index[e].pop();
});

console.log(counter);
