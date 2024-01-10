const N = 6;
const visited = new Array(N + 1).fill(false);
const numOfPlayer = N / 2;
const playerNumArr = [];

const getPair = (L, start, combinationArr, pairArr, visitedArr) => {
  if (L === 2) {
    console.log(pairArr);
  } else {
    for (let i = start; i < combinationArr.length; i++) {
      if (visitedArr[i]) continue;
      visitedArr[i] = true;
      pairArr.push(combinationArr[i]);
      getPair(L + 1, start + 1, combinationArr, pairArr, visitedArr);
      visitedArr[i] = false;
      pairArr.pop();
    }
  }
};

const DFS = (L, start) => {
  if (L === numOfPlayer) {
    getPair(
      0,
      0,
      playerNumArr,
      [],
      new Array(playerNumArr.length + 1).fill(false)
    );
  } else {
    for (let i = start; i <= N; i++) {
      if (visited[i]) continue;

      visited[i] = true;
      playerNumArr.push(i);
      DFS(L + 1, i);

      visited[i] = false;
      playerNumArr.pop();
    }
  }
};

DFS(0, 1);
