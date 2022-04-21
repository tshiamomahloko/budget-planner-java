import '../component-styles/exportBudgets.css'
import BudgetItem from './budgetItem.jsx'

function App(props) {
  var list = []

  function addSelect(strChange) {
    list.push(strChange)
  }

  function remSelect(strChange) {
    list = list.filter((item) => {
      return item !== strChange
    })
  }

  return (
    <div className='exportBudgetsMain'>
      <button
        onClick={() => props.FECresponse1()}
        className='exportBudgetsBackButton'
      >
        {'< Back'}
      </button>

      <div className='exportBudgetsTitle'>Export Budgets</div>

      <div className='exportBudgetBudgetsContainer'>
        {/* <div className="exportBudgetBudgetsContainerTitle">
                    Budgets
                </div> */}
        <BudgetItem
          add={() => addSelect('January')}
          rem={() => remSelect('January')}
          id='1'
          name='January'
          dateperiod="03 Jan 22' - 04 Jan '22"
        ></BudgetItem>
        <BudgetItem
          add={() => addSelect('February')}
          rem={() => remSelect('February')}
          id='2'
          name='February'
          dateperiod="04 Feb 22' - 05 Feb '22"
        ></BudgetItem>
        <BudgetItem
          add={() => addSelect('March')}
          rem={() => remSelect('March')}
          id='3'
          name='March'
          dateperiod="06 Mar 22' - 07 Mar '22"
        ></BudgetItem>
        <BudgetItem
          add={() => addSelect('April')}
          rem={() => remSelect('April')}
          id='4'
          name='April'
          dateperiod="08 Apr 22' - 09 Apr '22"
        ></BudgetItem>
        <BudgetItem
          add={() => addSelect('June')}
          rem={() => remSelect('June')}
          id='5'
          name='June'
          dateperiod="11 Jun 22' - 11 Jun '22"
        ></BudgetItem>
      </div>

      <button className='exportBudgetBudgetExportButton'>Export</button>
    </div>
  )
}

export default App
