const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const N = +stdin[0];
const coordsArr = stdin
  .slice(1)
  .map((ele) => ele.split(" ").map((ele) => +ele))
  .sort((a, b) => a[1] - b[1])
  .sort((a, b) => a[0] - b[0]);

console.log(coordsArr.map((ele) => ele.join(" ")).join("\n"));
