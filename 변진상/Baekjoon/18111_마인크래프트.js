const stdin = "3 4 11\n29 51 54 44\n22 44 32 62\n25 38 16 2".split("\n");
const [M, N, B] = stdin[0].split(" ").map((ele) => +ele);

const land = stdin.slice(1).map((ele) =>
  ele.split(" ").map((ele) => {
    return +ele;
  })
);

const answer = [Infinity, -1];

const flatLand = (targetHeight) => {
  let totalTime = 0;
  let bag = B;
  for (let i = 0; i < M; i++) {
    for (let j = 0; j < N; j++) {
      const diff = land[i][j] - targetHeight;

      if (diff > 0) {
        // 땅이 높아서 까야하는 이유
        totalTime += 2 * diff;
        bag += 1;
      }
    }
  }

  for (let i = 0; i < M; i++) {
    for (let j = 0; j < N; j++) {
      const diff = targetHeight - land[i][j];
      if (diff > 0) {
        // 땅이 낮아서 까야하는 이유
        totalTime += 1 * diff;
        bag -= 1;
      }
    }
  }

  return totalTime;
};

for (let i = 0; i <= 256; i++) {
  if (answer[0] >= flatLand(i)) {
    answer[0] = flatLand(i);
    answer[1] = i;
  }
}

console.log(answer.join(" "));
