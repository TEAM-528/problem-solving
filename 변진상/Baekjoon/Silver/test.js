/* readline Module */

// 문제 풀이
function solution(input) {
  // 답변 출력, list
  const stdin = input.split("\n");

  class MinHeap {
    constructor() {
      this.heap = [null];
    }

    size() {
      return this.heap.length - 1;
    }

    getMin() {
      return this.heap[1] ? this.heap[1] : null;
    }

    swap(a, b) {
      [this.heap[a], this.heap[b]] = [this.heap[b], this.heap[a]];
    }

    heappush(value) {
      this.heap.push(value);
      let curIdx = this.heap.length - 1;
      let parIdx = (curIdx / 2) >> 0;

      while (curIdx > 1 && this.heap[parIdx][1] > this.heap[curIdx][1]) {
        this.swap(parIdx, curIdx);
        curIdx = parIdx;
        parIdx = (curIdx / 2) >> 0;
      }
    }

    heappop() {
      const min = this.heap[1];
      if (this.heap.length <= 2) this.heap = [null];
      else this.heap[1] = this.heap.pop();

      let curIdx = 1;
      let leftIdx = curIdx * 2;
      let rightIdx = curIdx * 2 + 1;

      if (!this.heap[leftIdx]) return min;
      if (!this.heap[rightIdx]) {
        if (this.heap[leftIdx][1] < this.heap[curIdx][1]) {
          this.swap(leftIdx, curIdx);
        }
        return min;
      }

      while (
        this.heap[leftIdx][1] < this.heap[curIdx][1] ||
        this.heap[rightIdx][1] < this.heap[curIdx][1]
      ) {
        const minIdx =
          this.heap[leftIdx][1] > this.heap[rightIdx][1] ? rightIdx : leftIdx;
        this.swap(minIdx, curIdx);
        curIdx = minIdx;
        leftIdx = curIdx * 2;
        rightIdx = curIdx * 2 + 1;
      }

      return min;
    }
  }

  const [V, E, start, end, money] = stdin[0].split(" ").map((ele) => +ele);
  const edgeInfo = stdin
    .slice(1)
    .map((ele) => ele.split(" ").map((ele) => +ele));

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
    heap.heappush([start, 0]);

    while (heap.size() !== 0) {
      const [crntVertex, crntCost] = heap.heappop();

      if (dist[crntVertex] != crntCost) continue;

      for (let [nextVertex, nextCost] of adjacencyList[crntVertex]) {
        const totalCostToNextVertex = crntCost + nextCost;

        if (nextCost <= maximum && totalCostToNextVertex < dist[nextVertex]) {
          dist[nextVertex] = totalCostToNextVertex;
          heap.heappush([nextVertex, totalCostToNextVertex]);
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
}

/* readline Module */
const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];
let stdin = "";

rl.on("line", function (line) {
  input.push(line); // 입력받은 여러줄, line
  // rl.close()이 없기에 계속 입력, 로컬에서 입력을 중지할려면 입력을 한 후 'ctrl + D'을 통해 입력 종료
}).on("close", function () {
  // 형변환, 구분자(띄어쓰기)기준으로 배열에 삽입
  stdin = input.join("\n");
  solution(stdin); // 문제 풀이 함수 호출
  process.exit(); // 프로세스 종료
});
