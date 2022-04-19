import "../component-styles/budgetCreate.css";
import write from "../write.png";
function App(props) {
  return (
    <div className="budgetCreateMain">
      <div className="budgetCreateSignUpOnlyTitle">First up, let's create a budget</div>
    <div className ="budgetCreateBudgetCreationContainer">
      <div className ="budgetCreateBudgetNameInputIconSpace"><img src={write} className ="budgetCreateBudgetNameInputIcon"></img></div>
   <div className="budgetCreateBudgetStartInputTitle">Start Date</div>
   <div className="budgetCreateBudgetEndInputTitle">End Date</div>
    <input placeholder= "Budget Name" className="budgetCreateBudgetNameInput"></input>
      <input type="date" placeholder="Start" className="budgetCreateBudgetStartInput"></input>
      <input type="date" placeholder= "End" className="budgetCreateBudgetEndInput"></input>
    </div>
    <button onClick={()=>props.FECresponse1()}className ="budgetCreateBudgetCreationButton">
      Create Budget
    </button>
    </div>
  );
}

export default App;
