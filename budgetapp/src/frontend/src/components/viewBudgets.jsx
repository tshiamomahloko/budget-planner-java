import "../component-styles/viewBudgets.css";
import SSBudgetItem from "./ssBudgetItem.jsx";
import EditBudget from "./budgetEdit.jsx";
import React, { useState } from "react";

function App(props) {

    var budget ="";
    var numBudgets=5;
    const [screen, setScreen] = useState(0);
    const [vexisting, setExisting] = useState("");
    const [vstart, setStart] = useState("");
    const [vend, setEnd] = useState("");

    function setBudget(newBudget,target,bStart, bEnd){
        budget=newBudget;

        for (let i = 1; i < numBudgets+1; i++) {
                if (i!==target){
                document.getElementById("SSBI"+i).style.backgroundColor="rgb(230, 230, 230)";
                }
                else{
                document.getElementById("SSBI"+i).style.backgroundColor="rgba(188, 139, 201, 0.651)";
                }
        }
        setExisting(budget);
        setStart(bStart);
        setEnd(bEnd);
        console.log("new budget: "+budget);
    }



    function microControllerScreenReturn(){
      switch(screen){
        case 0:
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
                         <div className="viewBudgetBudgetsContainerTitle">
                            Select a budget to edit it
                        </div> 
                    <button onClick={()=>setBudget("January","1","2022-01-02","2022-03-12")} className="viewBudgetBudgetsContainerItemOutline" id={"SSBI"+"1"} ><SSBudgetItem name="January" dateperiod="03-01-22 -> 04-01-22"></SSBudgetItem></button>
                    <button onClick={()=>setBudget("February","2","12-01-22","12-02-22")} className="viewBudgetBudgetsContainerItemOutline" id={"SSBI"+"2"} ><SSBudgetItem  name="February"  dateperiod="01-01-22 -> 04-01-22"></SSBudgetItem></button>
                    <button onClick={()=>setBudget("March","3","13-01-22","13-02-22")} className="viewBudgetBudgetsContainerItemOutline" id={"SSBI"+"3"} ><SSBudgetItem  name="March"  dateperiod="04-01-22 -> 12-01-22"></SSBudgetItem></button>
                    <button onClick={()=>setBudget("April","4","14-01-22","14-02-22")} className="viewBudgetBudgetsContainerItemOutline" id={"SSBI"+"4"} ><SSBudgetItem  name="April"  dateperiod="05-01-22 -> 16-01-22"></SSBudgetItem></button>
                    <button onClick={()=>setBudget("June","5","21-01-22","15-02-22")} className="viewBudgetBudgetsContainerItemOutline" id={"SSBI"+"5"} ><SSBudgetItem name="June"  dateperiod="21-01-22 -> 13-02-22"></SSBudgetItem></button>
                </div>
                               {/* ./ //wrap div and use that as selection modal */}
                <button onClick={()=>setScreen(1)} className="viewBudgetBudgetviewButton">Edit Budget</button>
        
            </div>
            </div>
          );
        case 1:
          return <EditBudget existing={vexisting} start={vstart} end={vend} FECresponse1={()=>setScreen(0)}></EditBudget>;
      }
    }

  return (
   microControllerScreenReturn()
  );
}

export default App;
