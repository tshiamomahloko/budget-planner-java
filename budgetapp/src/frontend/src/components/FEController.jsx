import Landing from "./landing.jsx";
import Login from "./loginMain.jsx";
import SignUp from "./signUpMain.jsx";
import PrimaryScreen from "./primaryScreen.jsx";
import BudgetCreate from "./budgetCreateMain.jsx";
import BudgetCreateSignUpOnly from "./budgetCreate.jsx";
import React, { useState } from "react";
import ViewBudgets from "./viewBudgets.jsx";
import ExportBudgets from "./exportBudgets.jsx";
import IncomeCreateMain from "./incomeCreateMain.jsx";
import ExpenseCreateMain from "./expenseCreateMain.jsx";
import IncomeCreate from "./incomeCreate.jsx";
import ExpenseCreate from "./expenseCreate.jsx";

function App() {
  const [screen, setScreen] = useState(0);

  function screenToShow() {
    switch (screen) {
      case 0:
        return (
          <Landing
            FECresponse1={() => setScreen(1)}
            FECresponse2={() => setScreen(3)}
          ></Landing>
        );
      case 1:
        return (
          <Login
            FECresponse1={() => setScreen(0)}
            FECresponse2={() => setScreen(2)}
            FECresponse3={() => setScreen(3)}
          ></Login>
        );
      case 2:
        return (
          <PrimaryScreen
            FECresponse1={() => setScreen(0)}
            FECresponse2={() => setScreen(4)}
            FECresponse3={() => setScreen(6)}
            FECresponse4={() => setScreen(7)}
            FECresponse5={() => setScreen(8)}
          ></PrimaryScreen>
        );
      case 3:
        return (
          <SignUp
            FECresponse1={() => setScreen(0)}
            FECresponse2={() => setScreen(1)}
            FECresponse3={() => setScreen(5)}
          ></SignUp>
        );
      case 4:
        return <BudgetCreate FECresponse1={() => setScreen(2)}></BudgetCreate>;
      case 5:
        return <BudgetCreateSignUpOnly FECresponse1={() => setScreen(10)}></BudgetCreateSignUpOnly>;
      case 6:
        return <ViewBudgets FECresponse1={() => setScreen(2)}></ViewBudgets>;
      case 7:
        return (
          <ExportBudgets FECresponse1={() => setScreen(2)}></ExportBudgets>
        );
      case 8:
        return <ExpenseCreateMain FECresponse1={() => setScreen(2)} FECresponse2={() => setScreen(9)}></ExpenseCreateMain>;
          case 9:
            return <IncomeCreateMain FECresponse1={() => setScreen(2)} FECresponse2={() => setScreen(8)}></IncomeCreateMain>
          case 10:
            return <IncomeCreate FECresponse1={() => setScreen(11)}></IncomeCreate>
          case 11:
            return <ExpenseCreate FECresponse1={() => setScreen(2)}></ExpenseCreate>
      }
  }

  return screenToShow();
}

export default App;
