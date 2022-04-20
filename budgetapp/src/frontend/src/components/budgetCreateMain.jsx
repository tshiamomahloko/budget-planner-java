import "../component-styles/budgetCreateMain.css";
import write from "../write.png";
function App(props) {
  return (
    <div className="budgetCreateMain">
     <button onClick={() => props.FECresponse1()} className="budgetCreateBackButton">
        {"< Back"}
      </button>

    <div className ="budgetCreateBudgetCreationContainer">
      <div className ="budgetCreateBudgetNameInputIconSpace"><img src={write} className ="budgetCreateBudgetNameInputIcon"></img></div>
   <div className="budgetCreateBudgetStartInputTitle">Start Date</div>
   <div className="budgetCreateBudgetEndInputTitle">End Date</div>
    <input placeholder= "Budget Name" className="budgetCreateBudgetNameInput"></input>
      <input type="date" id="1di" placeholder="Start" className="budgetCreateBudgetStartInput"></input>
      <input type="date" placeholder= "End" className="budgetCreateBudgetEndInput"></input>
    </div>
    <button className ="budgetCreateBudgetCreationButton">
      Create Budget
    </button>
    </div>
  );
}

export default App;
