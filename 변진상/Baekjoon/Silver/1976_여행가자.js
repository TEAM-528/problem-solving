const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");
const cityNum = +stdin[0];
const travelCityNum = +stdin[1];
const cityInfoArr = stdin
  .slice(2, 2 + cityNum)
  .map((ele) => ele.split(" ").map((ele) => +ele));
const travelCityArr = stdin
  .slice(2 + cityNum)[0]
  .split(" ")
  .map((ele) => +ele);
const unionList = new Array(cityNum).fill(0);

for (let i = 1; i < cityNum; i++) {
  unionList[i] = i;
}

const findRoot = (node) => {
  if (unionList[node] === node) return node;

  unionList[node] = findRoot(unionList[node]);
  return unionList[node];
};

const compareParent = (a, b) => {
  let parentA = findRoot(a - 1);
  let parentB = findRoot(b - 1);

  if (parentA === parentB) return true;
  else return false;
};

const union = (a, b) => {
  let parentA = findRoot(a);
  let parentB = findRoot(b);

  if (parentA < parentB) {
    unionList[parentB] = parentA;
  } else {
    unionList[parentA] = parentB;
  }
};

for (let i = 0; i < cityNum; i++) {
  for (let j = i + 1; j < cityNum; j++) {
    if (cityInfoArr[i][j] === 1) union(i, j);
  }
}

for (let i = 1; i < travelCityNum; i++) {
  if (!compareParent(travelCityArr[i - 1], travelCityArr[i])) {
    console.log("NO");
    return;
  }
}

console.log("YES");
