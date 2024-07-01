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
    this.queue = [];
  }

  // 요소 추가 (push)
  push(element, priority) {
    const node = { element };
    this.queue.push(node);
    this.queue.sort((a, b) => b.element - a.element);
  }

  // 요소 제거 (pop)
  pop() {
    if (this.queue.length === 0) return null;
    return this.queue.shift().element;
  }

  // 큐가 비어있는지 확인
  isEmpty() {
    return this.queue.length === 0;
  }

  // 큐의 크기를 확인
  size() {
    return this.queue.length;
  }

  // 큐의 모든 요소를 배열로 반환
  toArray() {
    return this.queue.map((node) => node.element);
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
