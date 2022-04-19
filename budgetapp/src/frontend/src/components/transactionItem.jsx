import "../component-styles/transactionItem.css";
import React, { useState, useEffect } from 'react';

function App(props) {
 var transamount = props.amount.toString();


  useEffect(() => {    
    if (props.amount<0){
      document.getElementById("transaction"+props.id).style.color="rgba(251, 137, 107, 255)";
      transamount=transamount.substr(1);
      document.getElementById("transaction"+props.id).innerHTML="-R"+transamount;
    }
    else{
      document.getElementById("transaction"+props.id).style.color="rgb(80, 70, 187)";
    }
    
   });

  return (
    <div className="transactionItemMain">
     <div className="transactionItemCategoryIconSpace">
        <img src={require(("../"+props.icon+".png"))} className="transactionItemCategoryIcon"></img>
     </div>
    <div className="transactionItemTitle">{props.title}</div>
    <div className="transactionItemAmount" id={"transaction"+props.id}>{"R"+transamount}</div>
    </div>
  );
}

export default App;
