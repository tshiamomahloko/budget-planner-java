ALTER TABLE Budget
ADD CONSTRAINT checkStartDate CHECK (StartDate < EndDate)
