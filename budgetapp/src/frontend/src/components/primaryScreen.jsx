import "../component-styles/primaryScreen.css";
import backtemp from "../backtmp.png";
import menu from "../burgermenu.png";
import Transaction from "./transactionItem.jsx";
import user1 from "../user1.png";

function App(props) {
  console.log(props)

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
          April
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

      <div className="primaryScreenTransactionListContainer">
        <div className="primaryScreenTransactionListTitleBar">
          <div className="primaryScreenTransactionListTitle">Transactions</div>
        </div>

        <Transaction id="1" icon="transport" title="Transport" amount="-145.60"></Transaction>
        <Transaction id="2" icon="groceries" title="Groceries" amount="-155.00"></Transaction>
        <Transaction id="3" icon="eatingout" title="Eating out" amount="-323.50"></Transaction>
        <Transaction id="4" icon="elec" title="Electronics" amount="-665.00"></Transaction>
        <Transaction id="5" icon="sports" title="Sport" amount="-222.00"></Transaction>
        <Transaction id="6" icon="insure" title="Insurance" amount="-60.00"></Transaction>
        <Transaction id="7" icon="salary" title="Salary" amount="11500.00"></Transaction>

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
