import "../component-styles/primaryScreen.css";
import backtemp from "../backtmp.png";
import menu from "../burgermenu.png";
import Transaction from "./transactionItem.jsx";
import user1 from "../user1.png";

function App(props) {

  function MinimizeMenu(){
    document.getElementById("primaryScreenCMP1").style.visibility="hidden";
  }

  function MaximizeMenu(){
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
        <button className="primaryScreenAddBudget">+</button>
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

        <Transaction icon="transport" title="Transport" amount="-R145.60"></Transaction>
        <Transaction icon="groceries" title="Groceries" amount="-R155.00"></Transaction>
        <Transaction icon="eatingout" title="Eating out" amount="-R323.50"></Transaction>
        <Transaction icon="elec" title="Electronics" amount="-R665.00"></Transaction>
        <Transaction icon="sports" title="Sport" amount="-R222.00"></Transaction>
        <Transaction icon="insure" title="Insurance" amount="-R60.00"></Transaction>
        <Transaction icon="salary" title="Salary" amount="R11500.00"></Transaction>

      </div>

      <div className="primaryScreenAddIncomeExpense">+</div>
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
      <button className="primaryScreenSlideOutMenuEditBudgets">
          View or Edit Budgets
      </button>
      <button className="primaryScreenSlideOutMenuExportBudgets">
          Export Budgets
      </button>
      <button  onClick ={()=>MinimizeMenu()} className="primaryScreenSlideOutMenuMinimize">
          {"Close Menu"}
        </button>
      </div>

    </div>
  );
}

export default App;
