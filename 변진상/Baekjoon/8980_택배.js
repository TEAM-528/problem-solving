const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => ele.split(" ").map((ele) => +ele));

const [N, C] = stdin[0];
const [M] = stdin[1];
const infoArr = stdin.slice(2);
const villageArr = Array.from({ length: N + 1 }, () =>
  Array.from({ length: N + 1 }, () => 0)
);
const truck = new Array(N + 1).fill(0);

infoArr.sort((a, b) => a[1] - b[1]).sort((a, b) => a[0] - b[0]);

infoArr.forEach(([from, to, weight]) => {
  villageArr[from][to] = weight;
});

let loadedWeight = 0;
let answer = 0;

for (let crntVillage = 1; crntVillage <= N; crntVillage++) {
  loadedWeight -= truck[crntVillage];

  truck[crntVillage] = 0;
  for (let to = crntVillage + 1; to <= N; to++) {
    if (villageArr[crntVillage][to] === 0) continue;

    if (loadedWeight >= C) continue;
    let weight = villageArr[crntVillage][to];

    for (let from = crntVillage + 1; from < to; from++) {
      const tmpWeight = villageArr[from][to];

      if (loadedWeight + weight > C) {
        console.log(loadedWeight, weight, C);
        const diff = C - loadedWeight;
        weight = diff;
        console.log(diff);
      }

      if (tmpWeight > weight) {
        console.log(crntVillage, from, to, weight, tmpWeight);
        weight = 0;
        break;
      }
    }
    console.log(crntVillage, to, weight);
    if (loadedWeight + weight > C) {
      const diff = C - weight;
      loadedWeight += diff;
      truck[to] = diff;
      answer += diff;
    } else {
      loadedWeight += weight;
      truck[to] = weight;
      answer += weight;
    }
  }
}

console.log(answer);
