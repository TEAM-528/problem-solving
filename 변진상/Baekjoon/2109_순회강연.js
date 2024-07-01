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

const tmpArr = Array.from({ length: 10000 + 1 }, () => []);
let latestDay = 0;

payAndDayArr.forEach(([pay, day]) => {
  tmpArr[day].push(pay);

  latestDay = Math.max(latestDay, day);
});

const payOnDayArr = tmpArr.slice(0, latestDay + 1);

const getPlan = () => {
  let totalPay = 0;

  for (let i = latestDay; i > 0; i--) {
    if (!payOnDayArr[i].length) continue;
    const sortedPays = payOnDayArr[i].sort((a, b) => b - a);

    totalPay += sortedPays[0];

    payOnDayArr[i - 1] = [...payOnDayArr[i - 1], ...sortedPays.slice(1)];
  }

  console.log(totalPay);
};

getPlan();
