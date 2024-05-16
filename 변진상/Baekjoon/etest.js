const solution = (cookie) => {
  let answer = -1;
  const N = cookie.length;
  const dp = Array.from({ length: N + 1 }, () =>
    Array.from({ length: N + 1 }, () => 0)
  );
  let total = 0;

  for (let i = 1; i <= N; i++) {
    let sum = 0;
    for (let j = i; j <= N; j++) {
      sum += cookie[j - 1];
      if (i === 1) total = sum;

      dp[i][j] = sum;
    }
  }

  for (let i = 1; i < N; i++) {
    for (let j = i; j < N; j++) {
      if (dp[i][j] <= answer) continue;
      if (dp[i][j] > total / 2) break;

      let [lt, rt] = [j + 1, N + 1];
      let mid;

      while (lt < rt) {
        mid = Math.floor((rt + lt) / 2);
        const num = dp[j + 1][mid];

        if (dp[i][j] === num) {
          answer = Math.max(answer, num);
          break;
        }

        if (dp[i][j] > num) {
          lt = mid + 1;
        } else {
          rt = mid;
        }
      }
    }
  }

  return answer === -1 ? 0 : answer;
};

console.log(solution([1, 1, 2, 3]));
