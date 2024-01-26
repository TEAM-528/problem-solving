const stdin = "5 5 1 3 10\n1 2 5\n2 3 5\n1 4 1\n4 5 6\n5 3 1".split("\n");
const [V, E, start, end, money] = stdin[0].split(" ").map((ele) => +ele);
const edgeInfo = stdin.slice(1).map((ele) => ele.split(" ").map((ele) => +ele));

const dp = Array.from({ length: V }, () => new Array(V).fill(Infinity));
dp.map((v, i) => {
  v[i] = 0;
});

const queue = [];
queue.push(start);

const adjacencyList = Array.from({ length: V }, () => []);

edgeInfo.forEach(([from, to, cost]) => {
  adjacencyList[from - 1].push([to, cost]);
  adjacencyList[to - 1].push([from, cost]);
});

const BFS = () => {
  while (queue.length !== 0) {
    const currentV = queue.shift();

    for (connectedVertexInfo of adjacencyList[currentV - 1]) {
      const [vertex, cost] = connectedVertexInfo;
      
      dp[currentV-1][vertex-1] = 

    }

  }
};

BFS()