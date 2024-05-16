// const fs = require("fs");
// const stdin = fs
//   .readFileSync(
//     "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
//   )
//   .toString()
//   .trim()
//   .split("\n");

// const T = +stdin[0];
// const arr1 = stdin[2].split(" ").map((ele) => +ele);
// const arr2 = stdin[4].split(" ").map((ele) => +ele);

// const sumMap1 = new Map();
// const sumMap2 = new Map();

// let answer = 0;

// for (let i = 0; i < arr1.length; i++) {
//   let sum = 0;
//   for (let j = i; j < arr1.length; j++) {
//     sum += arr1[j];

//     if (!sumMap1.has(sum)) {
//       sumMap1.set(sum, 1);
//     } else {
//       sumMap1.set(sum, sumMap1.get(sum) + 1);
//     }
//   }
// }

// for (let i = 0; i < arr2.length; i++) {
//   let sum = 0;
//   for (let j = i; j < arr2.length; j++) {
//     sum += arr2[j];

//     if (!sumMap2.has(sum)) {
//       sumMap2.set(sum, 1);
//     } else {
//       sumMap2.set(sum, sumMap2.get(sum) + 1);
//     }
//   }
// }

// for ([num1, count1] of sumMap1) {
//   let num2 = T - num1;

//   if (sumMap2.has(num2)) {
//     answer += sumMap2.get(num2) * count1;
//   } else {
//     continue;
//   }
// }

// console.log(answer);

const fs = require("fs");
const stdin = fs
  .readFileSync(
    "/Users/byeonjinsang/Desktop/team528_PS/변진상/Baekjoon/Silver/case.txt"
  )
  .toString()
  .trim()
  .split("\n");

const T = +stdin[0];
const arr1 = stdin[2].split(" ").map((ele) => +ele);
const arr2 = stdin[4].split(" ").map((ele) => +ele);

const sumArr1 = [];
const sumArr2 = [];

let answer = 0;

for (let i = 0; i < arr1.length; i++) {
  let sum = 0;
  for (let j = i; j < arr1.length; j++) {
    sum += arr1[j];
    sumArr1.push(sum);
  }
}

for (let i = 0; i < arr2.length; i++) {
  let sum = 0;
  for (let j = i; j < arr2.length; j++) {
    sum += arr2[j];
    sumArr2.push(sum);
  }
}

sumArr2.sort((a, b) => a - b);

const getLowerBound = (arr, targetNum) => {
  let [lt, rt] = [0, arr.length];
  let mid;
  while (lt < rt) {
    mid = Math.floor((rt + lt) / 2);
    if (arr[mid] > targetNum) {
      rt = mid;
    } else {
      lt = mid + 1;
    }
  }
  return mid;
};

const getUpperBound = (arr, targetNum) => {
  let [lt, rt] = [0, arr.length];
  let mid;
  while (lt < rt) {
    mid = Math.floor((rt + lt) / 2);
    if (arr[mid] <= targetNum) {
      lt = mid + 1;
    } else {
      rt = mid;
    }
  }
  return mid;
};

sumArr1.forEach((valueA) => {
  const diff = T - valueA;
  let valueCount = 0;

  if (sumArr2.includes(diff)) {
    valueCount =
      getUpperBound(sumArr2, diff) - getLowerBound(sumArr2, diff) + 1;
  }

  answer += valueCount;
});

console.log(answer);
