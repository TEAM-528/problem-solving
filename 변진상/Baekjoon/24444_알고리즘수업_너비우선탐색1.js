// const fs = require("fs");
// const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
// const stdin = "5 5 1\n1 4\n1 2\n2 3\n2 4\n3 4\n".split('\n');
// const stdin =
//   "10 11 1\n1 9\n1 2\n1 4\n4 6\n2 5\n5 7\n7 9\n9 10\n3 8\n2 3\n6 7".split(
//     "\n"
//   );
// const [N, M, R] = stdin[0].split(" ").map((ele) => +ele);
// const edgeInfoArr = stdin
//   .slice(1)
//   .map((ele) => ele.split(" ").map((ele) => +ele))
//   .sort((a, b) => a[1] - b[1])
//   .sort((a, b) => a[0] - b[0]);

// const queue = [];
// const visited = new Array(N + 1).fill(false);
// visited[0] = true;
// const checkArr = new Array(N + 1).fill(0);

// const adjacentList = new Map();

// edgeInfoArr.forEach(([fromV, toV]) => {
//   if (!adjacentList.has(fromV)) {
//     adjacentList.set(fromV, []);
//   }
//   if (!adjacentList.has(toV)) {
//     adjacentList.set(toV, []);
//   }

//     adjacentList.set(fromV, [...adjacentList.get(fromV), toV]);

//     adjacentList.set(toV, [...adjacentList.get(toV), fromV]);

// });

// let cnt = 1;

// const BFS = (V) => {
//   visited[V] = true;
//   queue.push(V);
//   checkArr[V] = cnt;
//   cnt += 1;
//   while (queue.length !== 0) {
//     const crntVertex = queue.shift();

//     if (!adjacentList.has(crntVertex)) continue;
//     const adjacentVertexInfo = Array.from(adjacentList.get(crntVertex)).sort((a,b)=> a-b);

//     for (let vertex of adjacentVertexInfo){
//       if (visited[vertex]) continue;

//       visited[vertex] = true;
//       queue.push(vertex);
//       checkArr[vertex] = cnt;
//       cnt += 1;
//     }
//   }
// };

// BFS(R);

// console.log(checkArr);

const [N, M, R] = stdin[0].split(" ").map((ele) => +ele);
const edgeInfoArr = stdin
  .slice(1)
  .map((ele) => ele.split(" ").map((ele) => +ele));

const queue = [];
const visited = new Array(N + 1).fill(false);
visited[0] = true;
const checkArr = new Array(N + 1).fill(0);
const adjacentList = Array.from({ length: N + 1 }, () => []);

edgeInfoArr.forEach(([fromV, toV]) => {
  adjacentList[fromV].push(toV);
  adjacentList[toV].push(fromV);
});

adjacentList.forEach((ele) => ele.sort((a, b) => a - b));
console.log(adjacentList);

let cnt = 1;

const BFS = (V) => {
  visited[V] = true;
  queue.push(V);
  checkArr[V] = cnt;
  cnt += 1;

  while (queue.length !== 0) {
    const crntVertex = queue.shift();

    for (let vertex of adjacentList[crntVertex]) {
      if (visited[vertex]) continue;

      visited[vertex] = true;
      queue.push(vertex);
      checkArr[vertex] = cnt;
      cnt += 1;
    }
  }
};

BFS(R);

const answer = [];
for (let i = 1; i <= N; i++) {
  answer.push(checkArr[i]);
}
console.log(answer.join("\n"));
