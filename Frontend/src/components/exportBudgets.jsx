import "../component-styles/exportBudgets.css";

function App(props) {
  return (
    <div className="exportBudgetsMain">
  <button onClick={() => props.FECresponse1()} className="exportBudgetsBackButton">
        {"< Back"}
      </button>



    </div>
  );
}

export default App;
