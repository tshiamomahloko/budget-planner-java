import "../component-styles/incomeExpense.css";

function App(props) {
  var cat = "";

  function saveCat(strCat){
    cat=strCat;
    document.getElementById("categorydisplay2").innerHTML="Category: "+strCat;
  }

  return (
    <div className="IEMain">

<button  className="incomeSelectorA">Income</button>
<button onClick={() => props.FECresponse2()} className="expenseSelectorI">Expense</button>

   <button onClick={() => props.FECresponse1()} className="exportBudgetsBackButton">
        {"< Back"}
      </button>
      <div  className="incExpCategoryContainer">
    <button onClick={()=>saveCat("Salary")}className="incExpCategoryBoxI">
    <div   className="incExpCategoryBoxIconSpace">

        <img  className="incExpCategoryBoxIcon"  src={require("../salary.png")}></img>
    </div>
     <div   className="incExpCategoryBoxLabel">Salary</div>
        </button>
    <button onClick={()=>saveCat("Dividends")}className="incExpCategoryBoxI"> <div   className="incExpCategoryBoxIconSpace"> <img  className="incExpCategoryBoxIcon"  src={require("../dividends.png")}></img></div>
     <div   className="incExpCategoryBoxLabel">Dividends</div></button>
    <button onClick={()=>saveCat("Interest")}className="incExpCategoryBoxI"> <div   className="incExpCategoryBoxIconSpace"> <img  className="incExpCategoryBoxIcon"  src={require("../interest.png")}></img></div>
     <div   className="incExpCategoryBoxLabel">Interest</div></button>
    <button onClick={()=>saveCat("Gift")}className="incExpCategoryBoxI"> <div   className="incExpCategoryBoxIconSpace"><img  className="incExpCategoryBoxIcon"  src={require("../gift.png")}></img></div>
     <div   className="incExpCategoryBoxLabel">Gift</div></button>
    <button onClick={()=>saveCat("Savings")}className="incExpCategoryBoxI"> <div   className="incExpCategoryBoxIconSpace"> <img  className="incExpCategoryBoxIcon"  src={require("../savings.png")}></img></div>
     <div   className="incExpCategoryBoxLabel">Savings</div></button>
   
    </div>
    <div id="categorydisplay2" className="incExpSelectionI">No Category Selected</div>
    <input placeholder="Income Value (ZAR)" className="incExpInput" type="number"></input>
    <button className="incExpConfirmButton" >Add Income</button>
    </div>
  );
}

export default App;
