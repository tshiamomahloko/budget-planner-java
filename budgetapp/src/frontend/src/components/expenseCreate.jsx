import "../component-styles/incomeExpense.css";

function App(props) {
  var cat = "";

  function saveCat(strCat){
    cat=strCat;
    document.getElementById("categorydisplay1").innerHTML="Category: "+strCat;
  }

  return (
    <div className="IEMain">

<div className="ieSignUpHeader">Lastly, let's log an expense</div>

      <div  className="incExpCategoryContainer">
    <button onClick={()=>saveCat("Groceries")}className="incExpCategoryBox">
    <div   className="incExpCategoryBoxIconSpace">

        <img  className="incExpCategoryBoxIcon"  src={require("../groceries.png")}></img>
    </div>
     <div   className="incExpCategoryBoxLabel">Groceries</div>
        </button>
    <button onClick={()=>saveCat("Takeaway")}className="incExpCategoryBox"> <div   className="incExpCategoryBoxIconSpace"> <img  className="incExpCategoryBoxIcon"  src={require("../eatingout.png")}></img></div>
     <div   className="incExpCategoryBoxLabel">Takeaway</div></button>
    <button onClick={()=>saveCat("Sports")}className="incExpCategoryBox"> <div   className="incExpCategoryBoxIconSpace"> <img  className="incExpCategoryBoxIcon"  src={require("../sports.png")}></img></div>
     <div   className="incExpCategoryBoxLabel">Sports</div></button>
    <button onClick={()=>saveCat("Fuel")}className="incExpCategoryBox"> <div   className="incExpCategoryBoxIconSpace"><img  className="incExpCategoryBoxIcon"  src={require("../fuel.png")}></img></div>
     <div   className="incExpCategoryBoxLabel">Fuel</div></button>
    <button onClick={()=>saveCat("Insurance")}className="incExpCategoryBox"> <div   className="incExpCategoryBoxIconSpace"> <img  className="incExpCategoryBoxIcon"  src={require("../insure.png")}></img></div>
     <div   className="incExpCategoryBoxLabel">Insurance</div></button>
    <button onClick={()=>saveCat("Rental")}className="incExpCategoryBox"> <div   className="incExpCategoryBoxIconSpace"><img  className="incExpCategoryBoxIcon"  src={require("../rental.png")}></img></div>
     <div   className="incExpCategoryBoxLabel">Rental</div></button>
    <button onClick={()=>saveCat("Services")}className="incExpCategoryBox"> <div   className="incExpCategoryBoxIconSpace"> <img  className="incExpCategoryBoxIcon"  src={require("../elec.png")}></img></div>
     <div   className="incExpCategoryBoxLabel">Services</div></button>
    <button onClick={()=>saveCat("Clothing")}className="incExpCategoryBox"> <div   className="incExpCategoryBoxIconSpace"><img  className="incExpCategoryBoxIcon"  src={require("../clothing.png")}></img></div>
     <div   className="incExpCategoryBoxLabel">Clothing</div></button>
    <button onClick={()=>saveCat("Transport")}className="incExpCategoryBox"> <div   className="incExpCategoryBoxIconSpace"> <img  className="incExpCategoryBoxIcon"  src={require("../transport.png")}></img></div>
     <div   className="incExpCategoryBoxLabel">Transport</div></button>
    <button onClick={()=>saveCat("Debt")}className="incExpCategoryBox"> <div   className="incExpCategoryBoxIconSpace"><img  className="incExpCategoryBoxIcon"  src={require("../debt.png")}></img></div>
     <div   className="incExpCategoryBoxLabel">Debt</div></button>
    </div>
    <div id="categorydisplay1" className="incExpSelection">No Category Selected</div>
    <input placeholder="Expense Value (ZAR)" className="incExpInput" type="number"></input>
    <button onClick={()=>props.FECresponse1()}className="incExpConfirmButton" >Add Expense</button>
    </div>
  );
}

export default App;
