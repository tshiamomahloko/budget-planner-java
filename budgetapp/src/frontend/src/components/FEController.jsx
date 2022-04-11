import Landing from "./landing.jsx";
import Login from "./loginMain.jsx";
import SignUp from "./signUpMain.jsx";
import PrimaryScreen from "./primaryScreen.jsx";
import BudgetCreate from "./budgetCreate.jsx";

import React, { useState } from "react";

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
        return <PrimaryScreen></PrimaryScreen>;
      case 3:
        return <SignUp  FECresponse1={() => setScreen(0)} FECresponse2={() => setScreen(1)}
        FECresponse3={() => setScreen(2)}></SignUp>;
    }
  }

  return screenToShow();
}

export default App;
