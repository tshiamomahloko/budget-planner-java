USE BudgetPlannerDB
GO

INSERT INTO [User](UserName, Email, [Password])
VALUES('Maddie', 'maddie.lymei@gmail.com', 'Budgets123#')
GO

INSERT INTO CashFlowType(IncomeAndExpenseID, [Description])
VALUES(1, 'Wage'), (2, 'Pet Expense'), (2, 'Entertainment')
GO

INSERT INTO CashFlow(CashFlowTypeID, UserID, CashFlowName, CashFlowAmount)
VALUES(1, 4, 'BBD Salary', 20000), (5, 4, 'Cat Food', 1200), (4, 4, 'Baby sitting', '200'), (6, 4, 'Recreation', 1000)
GO

INSERT INTO Budget([Description], UserID, StartDate, EndDate)
VALUES('St Lucia', 4, '2022/04/24', '2022/05/01')
GO

INSERT INTO CashFlowBudget(BudgetID, CashFlowID)
Values(5, 8), (5, 10), (5, 11)
GO
