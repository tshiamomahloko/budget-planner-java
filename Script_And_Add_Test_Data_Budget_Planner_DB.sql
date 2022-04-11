DROP DATABASE IF EXISTS [BudgetPlannerDB];
GO

CREATE DATABASE [BudgetPlannerDB]
GO

USE [BudgetPlannerDB]

/****** Object: User [bbdgpdb] Script Date: 2022/04/06 15:13:28 ******/

CREATE USER [bbdgpdb] FOR LOGIN [bbdgpdb] WITH DEFAULT_SCHEMA=[dbo]
GO

ALTER ROLE [db_owner] ADD MEMBER [bbdgpdb]
GO

/****** Object: Table [dbo].[Budget] Script Date: 2022/04/06 15:13:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Budget](
[BudgetID] [int] IDENTITY(1,1) NOT NULL,
[BudgetName][varchar](200) NOT NULL,
[UserID] [int] NOT NULL,
[StartDate] [date] NULL,
[EndDate] [date] NULL,
CONSTRAINT [PK_Budget] PRIMARY KEY CLUSTERED
(
[BudgetID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object: Table [dbo].[CashFlow] Script Date: 2022/04/06 15:13:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[CashFlow](
[CashFlowID] [int] IDENTITY(1,1) NOT NULL,
[CashFlowTypeID] [int] NULL,
[UserID] [int] NULL,
[CashFlowName] [varchar](255) NULL,
[CashFlowAmount] [decimal](9, 2) NULL,
CONSTRAINT [PK_CashFlow] PRIMARY KEY CLUSTERED
(
[CashFlowID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object: Table [dbo].[CashFlowBudget] Script Date: 2022/04/06 15:13:29 ******/

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[CashFlowBudget](
[BudgetID] [int] NOT NULL,
[CashFlowID] [int] NOT NULL,
CONSTRAINT [PK_CashFlowBudget] PRIMARY KEY CLUSTERED
(
[BudgetID] ASC,
[CashFlowID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object: Table [dbo].[CashFlowType] Script Date: 2022/04/06 15:13:29 ******/

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[CashFlowType](
[CashFlowTypeID] [int] IDENTITY(1,1) NOT NULL,
[IncomeAndExpenseID] [int] NOT NULL,
[Description] [varchar](255) NULL,
CONSTRAINT [PK_CashFlowType] PRIMARY KEY CLUSTERED
(
[CashFlowTypeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object: Table [dbo].[IncomeAndExpense] Script Date: 2022/04/06 15:13:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[IncomeAndExpense](
[IncomeAndExpenseID] [int] IDENTITY(1,1) NOT NULL,
[Description] [varchar](255) NULL,
CONSTRAINT [PK_IncomeAndExpenses] PRIMARY KEY CLUSTERED
(
[IncomeAndExpenseID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object: Table [dbo].[User] Script Date: 2022/04/06 15:13:29 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[User](
[UserID] [int] IDENTITY(1,1) NOT NULL,
[UserName] [varchar](255) NULL,
[Email] [varchar](255) NULL,
[Password] [varchar](255) NULL,
CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED
(
[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Budget] ON
 

INSERT [dbo].[Budget] ([BudgetID], [UserID], [BudgetName], [StartDate], [EndDate]) VALUES (1, 1,'January', CAST(N'2022-01-01' AS Date), CAST(N'2022-01-31' AS Date))
SET IDENTITY_INSERT [dbo].[Budget] OFF
GO
SET IDENTITY_INSERT [dbo].[CashFlow] ON
 
INSERT [dbo].[CashFlow] ([CashFlowID], [CashFlowTypeID], [UserID], [CashFlowName], [CashFlowAmount]) VALUES (1, 1, 1, N'Ayesha Salary ', CAST(5000.00 AS Decimal(9, 2)))
SET IDENTITY_INSERT [dbo].[CashFlow] OFF
GO
INSERT [dbo].[CashFlowBudget] ([BudgetID], [CashFlowID]) VALUES (1, 1)
GO
SET IDENTITY_INSERT [dbo].[CashFlowType] ON

INSERT [dbo].[CashFlowType] ([CashFlowTypeID], [IncomeAndExpenseID], [Description]) VALUES (1, 1, N'Salary ')
INSERT [dbo].[CashFlowType] ([CashFlowTypeID], [IncomeAndExpenseID], [Description]) VALUES (2, 2, N'Fuel Expense')
SET IDENTITY_INSERT [dbo].[CashFlowType] OFF
GO
SET IDENTITY_INSERT [dbo].[IncomeAndExpense] ON

INSERT [dbo].[IncomeAndExpense] ([IncomeAndExpenseID], [Description]) VALUES (1, N'Income')
INSERT [dbo].[IncomeAndExpense] ([IncomeAndExpenseID], [Description]) VALUES (2, N'Expense')
SET IDENTITY_INSERT [dbo].[IncomeAndExpense] OFF
GO

SET IDENTITY_INSERT [dbo].[User] ON

 

INSERT [dbo].[User] ([UserID], [UserName], [Email], [Password]) VALUES (1, N'Ayesha', N'ayesha@bbd.co.za', N'1111')
SET IDENTITY_INSERT [dbo].[User] OFF
GO

ALTER TABLE [dbo].[Budget] WITH CHECK ADD CONSTRAINT [FK_Budget_User] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO

ALTER TABLE [dbo].[Budget] CHECK CONSTRAINT [FK_Budget_User]
GO

ALTER TABLE [dbo].[CashFlow] WITH CHECK ADD CONSTRAINT [FK_CashFlow_CashFlowType] FOREIGN KEY([CashFlowTypeID])
REFERENCES [dbo].[CashFlowType] ([CashFlowTypeID])
GO

ALTER TABLE [dbo].[CashFlow] CHECK CONSTRAINT [FK_CashFlow_CashFlowType]
GO
ALTER TABLE [dbo].[CashFlow] WITH CHECK ADD CONSTRAINT [FK_CashFlow_User] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO

ALTER TABLE [dbo].[CashFlow] CHECK CONSTRAINT [FK_CashFlow_User]
GO
ALTER TABLE [dbo].[CashFlowBudget] WITH CHECK ADD CONSTRAINT [FK_CashFlowBudget_Budget] FOREIGN KEY([BudgetID])
REFERENCES [dbo].[Budget] ([BudgetID])
GO

ALTER TABLE [dbo].[CashFlowBudget] CHECK CONSTRAINT [FK_CashFlowBudget_Budget]
GO

ALTER TABLE [dbo].[CashFlowBudget] WITH CHECK ADD CONSTRAINT [FK_CashFlowBudget_CashFlow] FOREIGN KEY([CashFlowID])
REFERENCES [dbo].[CashFlow] ([CashFlowID])
GO

ALTER TABLE [dbo].[CashFlowBudget] CHECK CONSTRAINT [FK_CashFlowBudget_CashFlow]
GO

ALTER TABLE [dbo].[CashFlowType] WITH CHECK ADD CONSTRAINT [FK_CashFlowType_IncomeAndExpense] FOREIGN KEY([IncomeAndExpenseID])
REFERENCES [dbo].[IncomeAndExpense] ([IncomeAndExpenseID])
GO

ALTER TABLE [dbo].[CashFlowType] CHECK CONSTRAINT [FK_CashFlowType_IncomeAndExpense]
GO

ALTER TABLE [dbo].[Budget] WITH CHECK ADD CONSTRAINT [checkStartDate] CHECK (([StartDate]<[EndDate]))
GO

ALTER TABLE [dbo].[Budget] CHECK CONSTRAINT [checkStartDate]
GO

ALTER DATABASE [BudgetPlannerDB] SET READ_WRITE
GO

USE BudgetPlannerDB
GO 

INSERT INTO [User](UserName, Email, [Password])
VALUES('Maddie', 'maddie.lymei@gmail.com', 'Budgets123#'),('Sashen', 'sashenn@bbd.co.za', 'S@shIsKing321')
GO
 

INSERT INTO CashFlowType(IncomeAndExpenseID, [Description])
VALUES(1, 'Wage'), (2, 'Pet Expense'), (2, 'Entertainment')
GO
 
INSERT INTO CashFlow(CashFlowTypeID, UserID, CashFlowName, CashFlowAmount)
VALUES(1, 2, 'BBD Salary', 20000), (3, 2, 'Cat Food', 1200), (2, 2, 'Baby sitting', 200), (4, 2, 'Recreational Fun', 1000), (1, 3, 'BBD Salary', 22000), (2, 3, 'Comedian', 0.50), (4, 2, 'Dinner', 350)
GO 

INSERT INTO Budget(BudgetName, UserID, StartDate, EndDate)
VALUES('St Lucia', 2, '2022/04/24', '2022/05/01'),('Dezzember', 3, '2022/12/01', '2022/12/30')
GO

INSERT INTO CashFlowBudget(BudgetID, CashFlowID)
Values(2, 2), (2, 3), (2, 4), (2,5), (3,6), (3,7), (3,8)
GO
