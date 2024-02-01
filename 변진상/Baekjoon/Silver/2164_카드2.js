const fs = require("fs");
const stdin = +fs.readFileSync("/dev/stdin").toString().trim();

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

for (let i = 1; i <= stdin; i++) {
  queue.push(i);
}

while (queue.getLength() !== 1) {
  queue.shift();
  queue.push(queue.shift());
}

console.log(queue.shift());
