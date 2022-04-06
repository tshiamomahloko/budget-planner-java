SET IDENTITY_INSERT [User] ON
INSERT INTO [User](UserID, UserName, Email, [Password])
VALUES(3, 'John Doe', 'Johnd@domain.com', 'P@ssw0rd')
SET IDENTITY_INSERT [User] OFF
GO

SET IDENTITY_INSERT Budget ON
INSERT INTO Budget (BudgetID, [Description], [UserID], StartDate, EndDate)
VALUES(3, 'Monthly budget', 3, '2022/04/06', '2022/04/30')
SET IDENTITY_INSERT Budget OFF
GO

SET IDENTITY_INSERT IncomeAndExpense ON
INSERT INTO IncomeAndExpense(IncomeAndExpenseID, [Description])
VALUES(3, 'EFT UFS PAYROLL')
SET IDENTITY_INSERT IncomeAndExpense OFF
GO

SET IDENTITY_INSERT CashFlowType ON
INSERT INTO CashFlowType(CashFlowTypeID, IncomeAndExpenseID, [Description])
VALUES(3, 3, 'Salary')
SET IDENTITY_INSERT CashFlowType OFF
GO

SET IDENTITY_INSERT CashFlow ON
INSERT INTO CashFlow(CashFlowID, CashFlowName, UserID, CashFlowAmount, CashFlowTypeID)
VALUES(3, 'Income Salary', 3,  34000, 3)
SET IDENTITY_INSERT CashFlow OFF
GO

INSERT INTO CashFlowBudget(BudgetID, CashFlowID)
VALUES(3, 3)
