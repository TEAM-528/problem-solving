// const fs = require('fs');
// const stdin = fs.readFileSync().toString().trim().split('\n');

// const stdin =
//   "15\npush_back 1\npush_front 2\nfront\nback\nsize\nempty\npop_front\npop_back\npop_front\nsize\nempty\npop_back\npush_front 3\nempty\nfront".split(
//     "\n"
//   );

const stdin =
  "22\nfront\nback\npop_front\npop_back\npush_front 1\nfront\npop_back\npush_back 2\nback\npop_front\npush_front 10\npush_front 333\nfront\nback\npop_back\npop_back\npush_back 20\npush_back 1234\nfront\nback\npop_back\npop_back".split(
    "\n"
  );

const N = stdin[0];
const instArr = stdin.slice(1).map((ele) => ele.split(" "));

class Node {
  constructor(data) {
    this.data = data;
    this.prev = null;
    this.next = null;
  }
}

class Dequeue {
  constructor() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  pushFront(data) {
    const newNode = new Node(data);

    if (this.getSize() === 0) {
      this.head = newNode;
      this.tail = newNode;
    } else {
      newNode.next = this.head;
      this.head.prev = newNode;
      this.head = newNode;
    }

    this.size += 1;
  }

  pushBack(data) {
    const newNode = new Node(data);

    if (this.getSize() === 0) {
      this.head = newNode;
      this.tail = newNode;
    } else {
      newNode.prev = this.tail;
      this.tail.next = newNode;
      this.tail = newNode;
    }

    this.size += 1;
  }

  popFront() {
    if (this.getSize() === 0) {
      return -1;
    } else if (this.getSize() === 1) {
      const poppedItem = this.head.data;
      this.head = null;
      this.tail = null;
      this.size -= 1;
      return poppedItem;
    } else if (this.getSize() === 2) {
      const poppedItem = this.head.data;
      this.head = this.head.next;
      this.tail.prev = null;
      this.size -= 1;
      return poppedItem;
    } else if (this.getSize() > 2) {
      const poppedItem = this.head.data;
      this.head.next.prev = null;
      this.head = this.head.next;
      this.size -= 1;
      return poppedItem;
    }
  }

  popBack() {
    if (this.getSize() === 0) {
      return -1;
    } else if (this.getSize() === 1) {
      const poppedItem = this.tail.data;
      this.head = null;
      this.tail = null;
      this.size -= 1;
      return poppedItem;
    } else if (this.getSize() === 2) {
      const poppedItem = this.tail.data;
      this.tail = this.tail.prev;
      this.head.next = null;
      this.size -= 1;
      return poppedItem;
    } else if (this.getSize() > 2) {
      const poppedItem = this.tail.data;
      this.tail.prev.next = null;
      this.tail = this.tail.prev;
      this.size -= 1;
      return poppedItem;
    }
  }

  getSize() {
    return this.size;
  }

  isEmpty() {
    return this.getSize() === 0 ? 1 : 0;
  }

  getFront() {
    return this.getSize() === 0 ? -1 : this.head.data;
  }

  getBack() {
    return this.getSize() === 0 ? -1 : this.tail.data;
  }
}

const dequeue = new Dequeue();

const answer = [];

for (let inst of instArr) {
  switch (inst[0]) {
    case "push_front":
      dequeue.pushFront(inst[1]);
      break;
    case "push_back":
      dequeue.pushBack(inst[1]);
      break;
    case "pop_front":
      answer.push(dequeue.popFront());
      break;
    case "pop_back":
      answer.push(dequeue.popBack());
      break;
    case "size":
      answer.push(dequeue.getSize());
      break;
    case "empty":
      answer.push(dequeue.isEmpty());
      break;
    case "front":
      answer.push(dequeue.getFront());
      break;
    case "back":
      answer.push(dequeue.getBack());
      break;
  }
}

console.log(answer.join("\n"));
