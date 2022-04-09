import "../component-styles/transactionItem.css";

function App(props) {
  return (
    <div className="transactionItemMain">
     <div className="transactionItemCategoryIconSpace">
        <img src={require(("../"+props.icon+".png"))} className="transactionItemCategoryIcon"></img>
     </div>
    <div className="transactionItemTitle">{props.title}</div>
    <div className="transactionItemAmount">{props.amount}</div>
    </div>
  );
}

export default App;
