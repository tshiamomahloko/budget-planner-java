import "../component-styles/ssBudgetItem.css";
import React, { useState, useEffect } from 'react';

function App(props) {

  return (
    <div className="ssBudgetItemMain">
    <div className="budgetItemTitle">{props.name}</div>
    <div className="ssBudgetItemAmount" >{props.dateperiod}</div>
    </div>
  );
}

export default App;
