import "../component-styles/viewBudgets.css";
import SSBudgetItem from "./ssBudgetItem.jsx";

function App(props) {

    var budget ="";
    var numBudgets=5;

    function setBudget(newBudget,target){
        budget=newBudget;

        for (let i = 0; i < numBudgets; i++) {
                if (i!=target){
                document.getElementById("SSBI"+i).style.backgroundColor="rgb(98, 0, 255)";
                }
                else{
                document.getElementById("SSBI"+i).style.backgroundColor="rgba(188, 139, 201, 0.651)";
                }
        }

        console.log("new budget: "+budget);
    }

  return (
    <div className="viewBudgetsMain">
  <button onClick={() => props.FECresponse1()} className="viewBudgetsBackButton">
        {"< Back"}
      </button>

      <div className="viewBudgetsMain">
  <button onClick={() => props.FECresponse1()} className="viewBudgetsBackButton">
        {"< Back"}
      </button>

        <div className="viewBudgetsTitle">View or Edit Budgets</div>

        <div className="viewBudgetBudgetsContainer">
                {/* <div className="viewBudgetBudgetsContainerTitle">
                    Budgets
                </div> */}
            <SSBudgetItem setSelect={()=>setBudget("January","1")} id={"SSBI"+"1"} name="January" dateperiod="03 Jan 22' - 04 Jan '22"></SSBudgetItem>
            <SSBudgetItem setSelect={()=>setBudget("February","2")} id={"SSBI"+"2"} name="February"  dateperiod="04 Feb 22' - 05 Feb '22"></SSBudgetItem>
            <SSBudgetItem setSelect={()=>setBudget("March","3")} id={"SSBI"+"3"} name="March"  dateperiod="06 Mar 22' - 07 Mar '22"></SSBudgetItem>
            <SSBudgetItem setSelect={()=>setBudget("April","4")} id={"SSBI"+"4"} name="April"  dateperiod="08 Apr 22' - 09 Apr '22"></SSBudgetItem>
            <SSBudgetItem setSelect={()=>setBudget("June","5")} id={"SSBI"+"5"} name="June"  dateperiod="11 Jun 22' - 11 Jun '22"></SSBudgetItem>
        </div>
                       {/* ./ //wrap div and use that as selection modal */}
        <button className="viewBudgetBudgetviewButton">view</button>

    </div>
    </div>
  );
}

export default App;
