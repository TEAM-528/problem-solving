const stdin = 6;

class Node {
  constructor(data, prev = null, next = null) {
    return { data, prevNode: prev, nextNode: next };
  }
}

class LinkedList {
  constructor() {
    this.start = null;
    this.end = null;
  }

  pushNode(data) {
    let node = new Node(data, (prev = this.end));

    if (this.end === null) {
      this.end = node;
    }
    this.end.nextNode = node;
  }

  popNode() {
    this.end = this.end.prevNode;
  }
}
