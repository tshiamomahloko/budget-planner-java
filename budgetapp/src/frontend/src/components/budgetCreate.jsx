import "../component-styles/budgetCreate.css";
import write from "../write.png";
import axios from 'axios'

function App(props) {
  let createPost = () => {
    let budgetData = {
      name: document.getElementById('budgetNameID').value,
      startDate: document
        .getElementById('1di')
        .value.split('-')
        .reverse()
        .join('-'),
      endDate: document
        .getElementById('endDateID')
        .value.split('-')
        .reverse()
        .join('-')
    }

    let url = `http://localhost:8080/api/v1/budget/${props.userName}/add-budget`
    const options = {
      method: 'POST',
      headers: {
        'content-type': 'application/json',
        Authorization: `Bearer ${props.access}`
      },
      data: budgetData,
      url
    }
    axios(options)
      .then((response) => {
        alert('Budget Created!')
        props.FECresponse1()
      })
      .catch((error) => {
        alert('Could not create a budget system on lunch!')
      })
  }
  return (
    <div className="budgetCreateMain">
      <div className="budgetCreateSignUpOnlyTitle">First up, let's create a budget</div>
    <div className ="budgetCreateBudgetCreationContainer">
      <div className ="budgetCreateBudgetNameInputIconSpace"><img src={write} className ="budgetCreateBudgetNameInputIcon"></img></div>
   <div className="budgetCreateBudgetStartInputTitle">Start Date</div>
   <div className="budgetCreateBudgetEndInputTitle">End Date</div>
    <input id="budgetNameID" placeholder= "Budget Name" className="budgetCreateBudgetNameInput"></input>
      <input id="1di" type="date" placeholder="Start" className="budgetCreateBudgetStartInput"></input>
      <input id="endDateID" type="date" placeholder= "End" className="budgetCreateBudgetEndInput"></input>
    </div>
    <button onClick={()=>createPost()}className ="budgetCreateBudgetCreationButton">
      Create Budget
    </button>
    </div>
  );
}

export default App;
