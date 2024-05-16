const fs = require("fs");
const [N, M] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split(" ")
  .map((ele) => +ele);
// 입력

const visited = new Array(N + 1).fill(false);
// DFS를 통해 순회하면서 방문했던 수에 대한 기록을 위한 체크 배열
// 만약 1을 방문했다고 하면 visited[1] === true
const answerArr = [];
// 방문한 수를 출력하기 위해 수를 저장하는 배열

const DFS = (L) => {
  if (L === M) {
    // DFS가 원하는 M만큼의 깊이(L)로 방문을 했을 때 그 결과를 출력
    console.log(answerArr.join(" "));
    return;
  }
  {
    for (let i = 1; i < visited.length; i++) {
      if (visited[i] === true) continue;
      // 방문할 i가 이미 방문한 수라면 해당 i를 스킵

      answerArr.push(i);
      // 방문한 수를 정답 배열에 push
      visited[i] = true;
      // 체크 배열에 방문한 수를 true 바꿔 조건문에서 해당 수는 skip할 수 있도록 기록
      DFS(L + 1);
      // DFS를 재귀로 호출해 다음 레벨의 수를 선택한다.

      answerArr.pop();
      // answerArr에 담긴 값을 출력해 재귀가 끝나고 해당 레벨에 해당하는 수를 pop한다.
      visited[i] = false;
      // 해당 숫자의 방문여불르 false로 바꾼다.
    }
  }
};

DFS(0);
