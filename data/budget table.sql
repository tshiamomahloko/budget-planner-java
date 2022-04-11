USE [BudgetPlannerDB]
GO

/****** Object:  Table [dbo].[Budget]    Script Date: 2022/04/06 19:15:12 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Budget](
	[BudgetID] [int] IDENTITY(1,1) NOT NULL,
	[Description] [varchar](255) NULL,
	[UserID] [int] NOT NULL,
	[StartDate] [date] NULL,
	[EndDate] [date] NULL,
 CONSTRAINT [PK_Budget] PRIMARY KEY CLUSTERED 
(
	[BudgetID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Budget]  WITH CHECK ADD  CONSTRAINT [FK_Budget_User] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO

ALTER TABLE [dbo].[Budget] CHECK CONSTRAINT [FK_Budget_User]
GO

ALTER TABLE [dbo].[Budget]  WITH CHECK ADD  CONSTRAINT [checkStartDate] CHECK  (([StartDate]<[EndDate]))
GO

ALTER TABLE [dbo].[Budget] CHECK CONSTRAINT [checkStartDate]
GO


