const fs = require("fs");
const stdin = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((ele) => ele.split(" "));

const N = stdin[0];
const instArr = stdin.slice(1);

class Queue {
  constructor() {
    this.storage = {};
    this.front = 0;
    this.rear = 0;
  }

  getLength() {
    if (this.storage[this.rear] === undefined) {
      return 0;
    } else {
      return this.rear - this.front + 1;
    }
  }

  empty() {
    if (this.getLength() === 0) return 1;
    else return 0;
  }

  getFront() {
    return this.storage[this.front] || -1;
  }

  getBack() {
    return this.storage[this.rear] || -1;
  }

  push(value) {
    if (this.getLength() === 0) {
      this.storage["0"] = value;
    } else {
      this.rear += 1;
      this.storage[this.rear] = value;
    }
  }

  shift() {
    let temp;
    if (this.getLength() === 0) {
      return -1;
    }

    if (this.front === this.rear) {
      temp = this.storage[this.front];
      delete this.storage[this.front];
      this.front = 0;
      this.rear = 0;
    } else {
      temp = this.storage[this.front];
      delete this.storage[this.front];
      this.front += 1;
    }
    return temp;
  }
}

const queue = new Queue();

const answer = [];

for (let inst of instArr) {
  switch (inst[0]) {
    case "push":
      queue.push(inst[1]);
      break;
    case "pop":
      answer.push(queue.shift());
      break;
    case "size":
      answer.push(queue.getLength());
      break;
    case "empty":
      answer.push(queue.empty());
      break;
    case "front":
      answer.push(queue.getFront());
      break;
    case "back":
      answer.push(queue.getBack());
      break;
  }
}

console.log(answer.join("\n"));
