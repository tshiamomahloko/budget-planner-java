import "../component-styles/viewBudgets.css";

function App(props) {
  return (
    <div className="viewBudgetsMain">
  <button onClick={() => props.FECresponse1()} className="viewBudgetsBackButton">
        {"< Back"}
      </button>


    </div>
  );
}

export default App;
