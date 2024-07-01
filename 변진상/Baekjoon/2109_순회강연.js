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
const payAndDayArr = stdin.slice(1);

const tmpMap = new Map();
let latestDay = 0;

payAndDayArr.forEach(([pay, day]) => {
  if (!tmpMap.has(day)) {
    tmpMap.set(day, [pay]);
  } else {
    tmpMap.set(day, [...tmpMap.get(day), pay]);
  }

  latestDay = Math.max(latestDay, day);
});

const getPlan = () => {
  let totalPay = 0;

  for (let i = latestDay; i > 0; i--) {
    if (!tmpMap.has(i)) continue;
    const sortedPays = tmpMap.get(i).sort((a, b) => b - a);

    totalPay += sortedPays[0];

    if (!tmpMap.has(i - 1) && sortedPays.length > 1) {
      tmpMap.set(i - 1, sortedPays.slice(1));
    } else if (tmpMap.has(i - 1) && sortedPays.length > 1) {
      tmpMap.set(i - 1, [...tmpMap.get(i - 1), ...sortedPays.slice(1)]);
    }
  }

  console.log(totalPay);
};

getPlan();
