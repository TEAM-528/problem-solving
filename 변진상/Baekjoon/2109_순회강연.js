const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/case.txt"
  )
  .toString()
  .trim()
  .split("\n")
  .map((ele) => ele.split(" ").map((ele) => +ele));

const [N] = stdin[0];
let latestDay = 0;

const payAndDayArr = stdin.slice(1).sort((a, b) => {
  latestDay = Math.max(latestDay, a[1], b[1]);
  return b[1] - a[1];
});

class PriorityQueue {
  constructor() {
    this.heap = [];
  }

  // 부모, 왼쪽 자식, 오른쪽 자식 인덱스를 계산하는 메서드들
  getParentIndex(index) {
    return Math.floor((index - 1) / 2);
  }

  getLeftChildIndex(index) {
    return 2 * index + 1;
  }

  getRightChildIndex(index) {
    return 2 * index + 2;
  }

  // 요소를 스왑하는 메서드
  swap(index1, index2) {
    [this.heap[index1], this.heap[index2]] = [
      this.heap[index2],
      this.heap[index1],
    ];
  }

  // 요소를 추가하는 메서드
  push(element) {
    this.heap.push(element);
    this.heapifyUp();
  }

  // 요소를 제거하는 메서드
  pop() {
    if (this.heap.length === 0) return null;
    if (this.heap.length === 1) return this.heap.pop();

    const root = this.heap[0];
    this.heap[0] = this.heap.pop();
    this.heapifyDown();

    return root;
  }

  // 요소를 위로 올리며 힙 정렬
  heapifyUp() {
    let index = this.heap.length - 1;
    while (index > 0) {
      const parentIndex = this.getParentIndex(index);

      if (this.heap[parentIndex] >= this.heap[index]) break;

      this.swap(parentIndex, index);
      index = parentIndex;
    }
  }

  // 요소를 아래로 내리며 힙 정렬
  heapifyDown() {
    let index = 0;
    const length = this.heap.length;

    while (true) {
      const leftChildIndex = this.getLeftChildIndex(index);
      const rightChildIndex = this.getRightChildIndex(index);
      let largest = index;

      if (
        leftChildIndex < length &&
        this.heap[leftChildIndex] > this.heap[largest]
      ) {
        largest = leftChildIndex;
      }

      if (
        rightChildIndex < length &&
        this.heap[rightChildIndex] > this.heap[largest]
      ) {
        largest = rightChildIndex;
      }

      if (largest === index) break;

      this.swap(index, largest);
      index = largest;
    }
  }

  // 큐가 비어 있는지 확인
  isEmpty() {
    return this.heap.length === 0;
  }

  // 큐의 크기 반환
  size() {
    return this.heap.length;
  }

  // 큐의 요소를 배열로 반환
  toArray() {
    return [...this.heap];
  }
}

const getPlan = () => {
  let totalPay = 0;

  const MaxHeap = new PriorityQueue();

  let payIdx = 0;

  for (let day = latestDay; day > 0; day--) {
    while (payIdx < payAndDayArr.length && payAndDayArr[payIdx][1] === day) {
      MaxHeap.push(payAndDayArr[payIdx][0]);

      payIdx += 1;
    }

    totalPay += MaxHeap.pop();
  }

  console.log(totalPay);
};

getPlan();
