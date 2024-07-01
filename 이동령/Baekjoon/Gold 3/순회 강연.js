const input = require("fs")
  .readFileSync(process.platform === "linux" ? "/dev/stdin" : "input.txt")
  .toString()
  .trim()
  .split("\n");

// 이전 MinHeap 구현 풀이를 참고
class MinHeap {
  constructor() {
    this.heap = [null];
  }

  print() {
    console.log(this.heap.slice(1));
  }

  size() {
    return this.heap.length - 1;
  }

  swap(index1, index2) {
    [this.heap[index1], this.heap[index2]] = [
      this.heap[index2],
      this.heap[index1],
    ];
  }

  getMin() {
    return this.heap[1];
  }

  getSum() {
    return this.heap.slice(1).reduce((res, cur) => res + cur[0], 0);
  }

  push(value) {
    this.heap.push(value);

    let current = this.heap.length - 1;
    let parent = Math.floor(current / 2);

    while (current > 1 && this.heap[current][0] < this.heap[parent][0]) {
      this.swap(current, parent);
      current = parent;
      parent = Math.floor(current / 2);
    }
  }

  pop() {
    if (this.heap.length === 1) return undefined;
    if (this.heap.length === 2) return this.heap.pop();
    let min = this.heap[1];
    this.heap[1] = this.heap.pop();

    let current = 1;

    while (true) {
      let smallest = current;
      let left = current * 2;
      let right = current * 2 + 1;

      if (
        left < this.heap.length &&
        this.heap[smallest][0] > this.heap[left][0]
      )
        smallest = left;
      if (
        right < this.heap.length &&
        this.heap[smallest][0] > this.heap[right][0]
      )
        smallest = right;
      if (smallest !== current) {
        this.swap(current, smallest);
        current = smallest;
      } else break;
    }
    return min;
  }
}

const [N, ...purposal] = input.map((str) => str.split(" ").map((e) => +e));
const minHeap = new MinHeap();
purposal.sort((a, b) => a[1] - b[1]);
purposal.forEach(([p, d]) => {
  minHeap.push([p, d]);
  if (minHeap.size() > d) minHeap.pop();
});

console.log(minHeap.getSum());
