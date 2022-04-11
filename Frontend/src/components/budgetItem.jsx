import "../component-styles/budgetItem.css";
import React, { useState, useEffect } from 'react';

function App(props) {
    var selected=false;
    function selectItem(){
        if (!selected){
            selected=true;
        document.getElementById("buttonSelector"+props.id).style.backgroundColor="rgb(98, 0, 255)";
       props.add();
    }
        else{
            selected=false;
            document.getElementById("buttonSelector"+props.id).style.backgroundColor="rgba(188, 139, 201, 0.651)";
            props.rem();
        }
    }

  return (
    <div className="budgetItemMain">
     <div className="budgetItemCategoryIconSpace">
       <button id={"buttonSelector"+props.id} onClick={()=>selectItem()}className="budgetItemSelectionButton"></button>
     </div>
    <div className="budgetItemTitle">{props.name}</div>
    <div className="budgetItemAmount" >{props.dateperiod}</div>
    </div>
  );
}

export default App;
