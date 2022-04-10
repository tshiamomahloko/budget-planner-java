USE BudgetPlannerDB
GO

CREATE PROCEDURE SelectUserBudget @UserID int
AS
SELECT b.[description], b.startDate, b.EndDate, ie.[Description], cft.[Description], cf.CashFlowName, cf.CashFlowAmount
FROM Budget b
INNER JOIN CashFlowBudget cfb
ON b.BudgetID = cfb.BudgetID
INNER JOIN CashFlow cf
ON cfb.CashFlowID = cf.CashFlowID
INNER JOIN CashFlowType cft
ON cf.CashFlowTypeID = cft.CashFlowTypeID
INNER JOIN IncomeAndExpense ie
ON ie.IncomeAndExpenseID = cft.IncomeAndExpenseID
WHERE b.UserID = @UserID

EXEC SelectUserBudget @UserID = 1
