var fs = require("fs");

// boj 제출용 코드
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

// 문제 풀이 코드

// 입력값 처리
const N = +input[0];
const graph = input.slice(1).map((string) =>
  string
    .replaceAll(/\s+/g, " ")
    .trim()
    .split(" ")
    .map((e) => +e)
);

let edge = []; // 음수가 아닌 간선 정보 저장
let addEdge = []; // 추가한 간선의 정보를 저장
let total = 0; // MST의 cost를 저장
let parent = Array.from({ length: N }, (_, i) => i); // 각 노드의 부모를 저장
let nodeSet = new Set(); // 연결된 노드를 저장

// Union Find를 위한 함수 선언
const Find = (idx) => {
  if (parent[idx] === idx) return idx;
  parent[idx] = Find(parent[idx]);
  return parent[idx];
};
const Union = (idx1, idx2) => {
  // Union은 연결이 가능하면 T, 아니면 F를 return
  let [root1, root2] = [Find(idx1), Find(idx2)];
  if (root1 === root2) return false;
  parent[root2] = root1;
  return true;
};

// 입력값을 순회하며 정보를 저장
for (let i = 0; i < N; i++) {
  for (let j = 0; j < i; j++) {
    if (graph[i][j] > 0) {
      edge.push({ idx1: i, idx2: j, cost: graph[i][j] });
    } else {
      Union(i, j);
      total -= graph[i][j];
      nodeSet.add(i, j);
    }
  }
}

// Priority Queue를 대신할 배열을 내림차순으로 정렬
edge.sort((a, b) => b.cost - a.cost);

// Kruskal 알고리즘 진행
while (edge.length) {
  let { idx1, idx2, cost } = edge.pop();
  if (Union(idx1, idx2)) {
    total += cost;
    addEdge.push([idx1, idx2]);
    nodeSet.add(idx1, idx2);
    if (nodeSet.length === N) break;
  }
}

// 결과값 출력
console.log(total, addEdge.length);
addEdge.forEach(([idx1, idx2]) => console.log(idx1 + 1, idx2 + 1));
