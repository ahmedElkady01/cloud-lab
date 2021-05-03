const express = require("express");
const app = express();
const PORT = process.env.PORT || 7000;

app.get("/", (req, res) => {
  res.send("<h2> WELCOME TO THE CALCULATOR</h2>");
});

app.get("/calc", (req, res) => {
  try {
    let operation = req.query.operation.toLowerCase();
    let nOne = parseInt(req.query.numberone);
    let nTwo = parseInt(req.query.numbertwo);
    let result;

    switch (operation) {
      case "add":
        result = add(nOne, nTwo);
        break;
      case "sub":
        result = sub(nOne, nTwo);
        break;
      case "mul":
        result = mul(nOne, nTwo);
        break;
      case "div":
        result = div(nOne, nTwo);
        break;
      default: result = "Xx-- THE FUNCTION YOU ARE TRYING TO ACCESS DOES NOT EXIST --xX"
        break;
    }
if (isNaN(result))
    res.send(`<h2> ${result}</h2>`);
    res.send(`<h2> ANSWER  = ${result}</h2>`);
  } catch (error) {
      res.send(`<h2> An error has occured ----> = ${error}</h2>`);
    }
});

let add = (nOne, nTwo) => nOne + nTwo;
let mul = (nOne, nTwo) => nOne * nTwo;
let div = (nOne, nTwo) => nOne / nTwo;
let sub = (nOne, nTwo) => nOne - nTwo;

app.listen(PORT, () => {
  console.log(`App is listning on PORT ${PORT}`);
});
