// 입력을 받아오기 위해 아래 두줄의 코드를 사용하세요. (수정 금지)
const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

class MinHeap {
  constructor() {
    this.heap = [];
  }

  size() {
    return this.heap.length;
  }

  getLeftChildIndex(parentIndex) {
    return 2 * parentIndex + 1;
  }

  getRightChildIndex(parentIndex) {
    return 2 * parentIndex + 2;
  }

  getParentIndex(childIndex) {
    return Math.floor((childIndex - 1) / 2);
  }

  swap(index1, index2) {
    const temp = this.heap[index1];
    this.heap[index1] = this.heap[index2];
    this.heap[index2] = temp;
  }

  peek() {
    if (this.heap.length === 0) {
      return null;
    }
    return this.heap[0];
  }

  insert(value) {
    this.heap.push(value);
    this.heapifyUp();
  }

  heapifyUp() {
    let currentIndex = this.heap.length - 1;
    while (currentIndex > 0) {
      const parentIndex = this.getParentIndex(currentIndex);
      if (this.heap[parentIndex][1] > this.heap[currentIndex][1]) {
        this.swap(parentIndex, currentIndex);
        currentIndex = parentIndex;
      } else {
        break;
      }
    }
  }

  remove() {
    if (this.heap.length === 0) {
      return null;
    }
    if (this.heap.length === 1) {
      return this.heap.pop();
    }
    const removedValue = this.heap[0];
    this.heap[0] = this.heap.pop();
    this.heapifyDown();
    return removedValue;
  }

  heapifyDown() {
    let currentIndex = 0;
    while (this.getLeftChildIndex(currentIndex) < this.heap.length) {
      const leftChildIndex = this.getLeftChildIndex(currentIndex);
      const rightChildIndex = this.getRightChildIndex(currentIndex);
      const smallerChildIndex =
        rightChildIndex < this.heap.length &&
        this.heap[rightChildIndex][1] < this.heap[leftChildIndex][1]
          ? rightChildIndex
          : leftChildIndex;
      if (this.heap[currentIndex][1] > this.heap[smallerChildIndex][1]) {
        this.swap(currentIndex, smallerChildIndex);
        currentIndex = smallerChildIndex;
      } else {
        break;
      }
    }
  }
}

const [V, E, start, end, money] = stdin[0].split(" ").map((ele) => +ele);
const edgeInfo = stdin.slice(1).map((ele) => ele.split(" ").map((ele) => +ele));

const adjacencyList = Array.from({ length: V + 1 }, () => []);
const costs = [];
const heap = new MinHeap();

edgeInfo.forEach(([from, to, cost]) => {
  adjacencyList[from].push([to, cost]);
  adjacencyList[to].push([from, cost]);
  costs.push(cost);
});

const dijkstra = (maximum) => {
  const dist = new Array(V + 1).fill(Infinity);
  dist[start] = 0;
  heap.insert([start, 0]);

  while (heap.size() !== 0) {
    const [crntVertex, crntCost] = heap.remove();

    if (dist[crntVertex] != crntCost) continue;

    for (let [nextVertex, nextCost] of adjacencyList[crntVertex]) {
      const totalCostToNextVertex = crntCost + nextCost;

      if (nextCost <= maximum && totalCostToNextVertex < dist[nextVertex]) {
        dist[nextVertex] = totalCostToNextVertex;
        heap.insert([nextVertex, totalCostToNextVertex]);
      }
    }
  }

  return dist[end] <= money;
};

costs.sort((a, b) => a - b);

let [lt, rt] = [0, costs.length - 1];
let answer = Infinity;

while (lt <= rt) {
  const mid = Math.floor((lt + rt) / 2);
  if (dijkstra(costs[mid])) {
    if (costs[mid] < answer) {
      answer = costs[mid];
    }
    rt = mid - 1;
  } else {
    lt = mid + 1;
  }
}

console.log(answer === Infinity ? -1 : answer);

// ------
// ------
// ------
// ------
// ------
// ------
// ------
// const edgeInfo = stdin.slice(1).map((ele) => ele.split(" ").map((ele) => +ele));
// const answer = [];

// const dp = Array(V).fill(Infinity);
// const minCostDp = Array(V).fill(Number.MIN_SAFE_INTEGER);

// class Queue {
//   constructor() {
//     this.storage = {};
//     this.front = 0;
//     this.rear = 0;
//   }

//   getLength() {
//     if (this.storage[this.rear] === undefined) {
//       return 0;
//     } else {
//       return this.rear - this.rear + 1;
//     }
//   }

//   push(value) {
//     if (this.getLength() === 0) {
//       this.storage["0"] = value;
//     } else {
//       this.rear += 1;
//       this.storage[this.rear] = value;
//     }
//   }

//   shift() {
//     let temp;
//     if (this.front === this.rear) {
//       temp = this.storage[this.front];
//       delete this.storage[this.front];
//       this.front = 0;
//       this.rear = 0;
//     } else {
//       temp = this.storage[this.front];
//       delete this.storage[this.front];
//       this.front += 1;
//     }
//     return temp;
//   }
// }

// const queue = new Queue();

// const adjacencyList = Array.from({ length: V }, () => []);

// edgeInfo.forEach(([from, to, cost]) => {
//   adjacencyList[from - 1].push([to, cost]);
// });

// const BFS = () => {
//   queue.push(start);
//   dp[start - 1] = 0;

//   while (queue.getLength !== 0) {
//     const currentV = queue.shift();
//     if (currentV === undefined) return;

//     for (let connectedVertexInfo of adjacencyList[currentV - 1]) {
//       const [vertex, cost] = connectedVertexInfo;
//       queue.push(vertex);

//       if (money >= dp[currentV - 1] + cost) {
//         dp[vertex - 1] = Math.min(dp[currentV - 1] + cost, dp[vertex - 1]);
//         minCostDp[vertex - 1] = Math.max(cost, minCostDp[currentV - 1]);
//         if (vertex === end) answer.push(minCostDp[end - 1]);
//       }
//     }
//   }
// };

// BFS();
// console.log(dp[end - 1] === Infinity ? -1 : Math.min(answer));
