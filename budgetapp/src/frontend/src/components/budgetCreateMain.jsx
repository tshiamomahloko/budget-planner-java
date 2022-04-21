import '../component-styles/budgetCreateMain.css'
import write from '../write.png'
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
    <div className='budgetCreateMain'>
      <button
        onClick={() => props.FECresponse1()}
        className='budgetCreateBackButton'
      >
        {'< Back'}
      </button>

      <div className='budgetCreateBudgetCreationContainer'>
        <div className='budgetCreateBudgetNameInputIconSpace'>
          <img src={write} className='budgetCreateBudgetNameInputIcon'></img>
        </div>
        <div className='budgetCreateBudgetStartInputTitle'>Start Date</div>
        <div className='budgetCreateBudgetEndInputTitle'>End Date</div>
        <input
          placeholder='Budget Name'
          id='budgetNameID'
          className='budgetCreateBudgetNameInput'
        ></input>
        <input
          type='date'
          id='1di'
          placeholder='Start'
          className='budgetCreateBudgetStartInput'
        ></input>
        <input
          id='endDateID'
          type='date'
          placeholder='End'
          className='budgetCreateBudgetEndInput'
        ></input>
      </div>
      <button
        onClick={() => {
          createPost()
        }}
        className='budgetCreateBudgetCreationButton'
      >
        Create Budget
      </button>
    </div>
  )
}

export default App
