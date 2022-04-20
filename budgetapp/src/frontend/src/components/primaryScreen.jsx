import "../component-styles/primaryScreen.css";
import backtemp from "../backtmp.png";
import menu from "../burgermenu.png";
import Transaction from "./transactionItem.jsx";
import user1 from "../user1.png";
import React, { useState, useEffect } from 'react';
import axios from 'axios'
import ReactDOM from 'react-dom'

function App(props) {

  const [budgetName, setBudgetName] = useState("");

  useEffect(() => {
  
    console.log("call from child: "+props.access);
    const data = {}
    let url = `http://localhost:8080/api/v1/budget/${props.customerID}`
    const options = {
      method: 'GET',
      headers: { 'content-type': 'application/json',
      Authorization: `Bearer ${props.access}`  },
      data: data,
      url
    }
    axios(options).then((response) => {
     console.log(response);

    setBudgetName(response.data[0].name);


   for (let i =0; i<response.data[0].cashFlows.length;i++){
    var div1 = 



  }


    }).catch((error)=>{
      console.log(error)
    })
  });

 
  function MinimizeMenu(){
    document.getElementById("primaryScreenCMP1").style.visibility="hidden";
    document.getElementById("primaryScreenCMP2").style.visibility="hidden";
  }

  function MaximizeMenu(){
    document.getElementById("primaryScreenCMP2").style.visibility="visible";
    document.getElementById("primaryScreenCMP1").style.visibility="visible";
  }


  return (
    <div className="primaryScreenMain">
      <div className="primaryScreenMenuBar">
        <button onClick ={()=>MaximizeMenu()} className="primaryScreenBurgerMenuButton">
          <img src={menu} className="primaryScreenBurgerMenuIcon"></img>
        </button>
        <button className="primaryScreenBudgetDropdownContainer">
          {budgetName}
        </button>
        <button onClick={() => props.FECresponse2()}className="primaryScreenAddBudget">+</button>
      </div>
      <div className="primaryScreenIncomeExpenseVisualizer">
        <div className="primaryScreenIncomeVisualizer">
          <div className="primaryScreenIncomeTitle">Income</div>
          <div className="primaryScreenIncomeDisplay">R25 200.00</div>
        </div>
        <div className="primaryScreenExpenseVisualizer">
          <img
            src={backtemp}
            className="primaryScreenIncomeExpenseVisualizerGraphic"
          ></img>
          <div className="primaryScreenExpenseTitle">Expenses</div>
          <div className="primaryScreenExpenseDisplay">R25 000.00</div>
        </div>
      </div>

      <div className="primaryScreenDaysRemainingContainer">
        <div className="primaryScreenDaysRemainingCounter">5</div>
        <div className="primaryScreenDaysRemainingSubText">Days to go</div>
      </div>

      <div className="primaryScreenAmountRemainingContainer">
        <div className="primaryScreenAmountRemainingCounter">R40.00</div>
        <div className="primaryScreenAmountRemainingSubText">Left/day</div>
      </div>

      <div className="primaryScreenTransactionListContainer" id="primaryScreenTransactionListContainerIdentifier">
        <div className="primaryScreenTransactionListTitleBar">
          <div className="primaryScreenTransactionListTitle">Transactions</div>
        </div>

        <Transaction id="1" icon="transport" title="Test" amount="-145.60"></Transaction>
  

      </div>

      <button onClick={() => props.FECresponse5()} className="primaryScreenAddIncomeExpense">+</button>
      <div id="primaryScreenCMP1" className="primaryScreenSlideOutMenu">
      <div className="primaryScreenSlideOutMenuTitle">
        Paulo N.
        </div>
        <button onClick={() => props.FECresponse1()} className="primaryScreenSlideOutMenuSignOut">
          {"Sign out"}
        </button>
      
      <div className="primaryScreenSlideOutMenuIconSpace">
      <img src={user1} className="primaryScreenSlideOutMenuIcon">
      </img>
      </div>
      <button onClick={() => props.FECresponse3()} className="primaryScreenSlideOutMenuEditBudgets">
          View or Edit Budgets
      </button>
      <button onClick={() => props.FECresponse4()} className="primaryScreenSlideOutMenuExportBudgets">
          Export Budgets
      </button>
      <button  onClick ={()=>MinimizeMenu()} className="primaryScreenSlideOutMenuMinimize">
          {"Close Menu"}
        </button>
      </div>
      <div id="primaryScreenCMP2" className="primaryScreenSlideOutMenuShade">
        </div>
    </div>
  );
}//make menu opposite opaque

//transactions positive orangy neg blue 

export default App;
