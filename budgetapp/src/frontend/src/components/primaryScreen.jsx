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
  const[incomeAll,setIncomeAll]= useState(0);
  const[expenseAll,setExpenseAll]=useState(0);
  const[days,setDays]=useState(0);
  const[remaining,setRemaining]=useState(0);
  useEffect(() => {
  

   
    console.log("call from child: "+props.access);
    const data = {}
    let url = `http://localhost:8080/api/v1/budget/${props.userName}/get-budgets`
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

    setIncomeAll(response.data[0].incomeTotal);
    setExpenseAll(Math.abs(response.data[0].expenseTotal));
    var endDate = new Date(response.data[0].endDate.split("-").reverse().join("-"));
    console.log("end:: "+endDate);
    var today=new Date();
    var one_day=1000*60*60*24;
    setDays(Math.ceil((endDate-today.getTime())/(one_day)));
    var rem = (response.data[0].incomeTotal-Math.abs(response.data[0].expenseTotal))/days;
    if (rem<0){
      rem=0;
    }
    setRemaining(rem);
   for (let i =0; i<response.data[0].cashFlows.length;i++){
     if (!(props.includesCFID(response.data[0].cashFlows[i].id))){
    props.pushCFID(response.data[0].cashFlows[i].id);
    var elem1 = document.createElement("img");
    elem1.src=response.data[0].cashFlows[i].name.toLowerCase()+".png";
    elem1.className="transactionItemCategoryIcon";
    var elem2 = document.createElement("div");
    elem2.className="transactionItemCategoryIconSpace";
    elem2.appendChild(elem1);
    var elem3 = document.createElement("div");
    elem3.className="transactionItemTitle";
    elem3.innerHTML=response.data[0].cashFlows[i].name;
    var elem4 = document.createElement("div");
    elem4.className="transactionItemAmount";
    elem4.innerHTML=response.data[0].cashFlows[i].amount;
    var elem5 = document.createElement("div");
    elem5.className="transactionItemMain";
    elem5.appendChild(elem2);
    elem5.appendChild(elem3);
    elem5.appendChild(elem4);
    document.getElementById("primaryScreenTransactionListContainerIdentifier").appendChild(elem5);
     }
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
          <div className="primaryScreenIncomeDisplay">R{incomeAll}</div>
        </div>
        <div className="primaryScreenExpenseVisualizer">
          <img
            src={backtemp}
            className="primaryScreenIncomeExpenseVisualizerGraphic"
          ></img>
          <div className="primaryScreenExpenseTitle">Expenses</div>
          <div className="primaryScreenExpenseDisplay">R{expenseAll}</div>
        </div>
      </div>

      <div className="primaryScreenDaysRemainingContainer">
        <div className="primaryScreenDaysRemainingCounter">{days}</div>
        <div className="primaryScreenDaysRemainingSubText">Days to go</div>
      </div>

      <div className="primaryScreenAmountRemainingContainer">
        <div className="primaryScreenAmountRemainingCounter">R{remaining}</div>
        <div className="primaryScreenAmountRemainingSubText">Left/day</div>
      </div>

      <div className="primaryScreenTransactionListContainer" id="primaryScreenTransactionListContainerIdentifier">
        <div className="primaryScreenTransactionListTitleBar">
          <div className="primaryScreenTransactionListTitle">Transactions</div>
        </div>
  

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
