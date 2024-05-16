const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const [peopleNum, partyNum] = stdin[0].split(" ").map((ele) => +ele);
const peopleWhoKnowTheFact = stdin[1]
  .split(" ")
  .map((ele) => +ele)
  .slice(1);

const chkArr = Array.from({ length: peopleNum + 1 }, () => false);

for (let num of peopleWhoKnowTheFact) {
  chkArr[num] = true;
}

const edgeInfo = stdin.slice(2).map((ele) =>
  ele
    .split(" ")
    .slice(1)
    .map((ele) => +ele)
);

const adjacencyList = Array.from({ length: peopleNum + 1 }, () => []);

const getPair = (candidateArr) => {
  const tmp = [];
  const visited = new Array(candidateArr.length + 1).fill(false);

  const DFS = (L, idx) => {
    if (L === 2) {
      let [from, to] = tmp;
      adjacencyList[candidateArr[from - 1]].push(candidateArr[to - 1]);
      adjacencyList[candidateArr[to - 1]].push(candidateArr[from - 1]);
      return;
    } else {
      for (let i = idx; i < visited.length; i++) {
        if (visited[i] === true) continue;

        visited[i] = true;
        tmp.push(i);
        DFS(L + 1, i);
        tmp.pop();
        visited[i] = false;
      }
    }
  };

  DFS(0, 1);
};

edgeInfo.forEach((party) => {
  if (party.length < 2) {
    return;
  }
  getPair(party);
});

let queue = [...peopleWhoKnowTheFact];

const BFS = () => {
  while (queue.length !== 0) {
    const crntNode = queue.shift();
    chkArr[crntNode] = true;

    for (let nextNode of adjacencyList[crntNode]) {
      if (chkArr[nextNode] === false) {
        chkArr[nextNode] = true;
        queue.push(nextNode);
      }
    }
  }
};

BFS();

let answer = partyNum;

edgeInfo.forEach((party) => {
  for (let personNum of party) {
    if (chkArr[personNum]) {
      answer -= 1;
      return;
    }
  }
});

console.log(answer);
