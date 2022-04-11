import "../component-styles/budgetItem.css";
import React, { useState, useEffect } from 'react';

function App(props) {

  return (
    <div className="budgetItemMain">
     <div className="budgetItemCategoryIconSpace">
       <button onClick={()=>props.setSelect()}className="budgetItemSelectionButton"></button>
     </div>
    <div className="budgetItemTitle">{props.name}</div>
    <div className="budgetItemAmount" >{props.dateperiod}</div>
    </div>
  );
}

export default App;
