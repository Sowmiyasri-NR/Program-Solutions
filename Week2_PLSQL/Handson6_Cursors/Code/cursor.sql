SET SERVEROUTPUT ON;

-- Scenario 1: GenerateMonthlyStatements using an explicit cursor
DECLARE
  CURSOR txn_cursor IS
    SELECT c.CustomerID, c.Name, t.TransactionDate, t.Amount, t.TransactionType
    FROM Customers c
    JOIN Accounts a ON c.CustomerID = a.CustomerID
    JOIN Transactions t ON a.AccountID = t.AccountID
    WHERE EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
      AND EXTRACT(YEAR FROM t.TransactionDate) = EXTRACT(YEAR FROM SYSDATE)
    ORDER BY c.CustomerID;

  v_cust_id Customers.CustomerID%TYPE;
  v_name Customers.Name%TYPE;
  v_date Transactions.TransactionDate%TYPE;
  v_amount Transactions.Amount%TYPE;
  v_type Transactions.TransactionType%TYPE;

BEGIN
  DBMS_OUTPUT.PUT_LINE('--- Monthly Statements ---');
  OPEN txn_cursor;
  LOOP
    FETCH txn_cursor INTO v_cust_id, v_name, v_date, v_amount, v_type;
    EXIT WHEN txn_cursor%NOTFOUND;

    DBMS_OUTPUT.PUT_LINE('Customer: ' || v_name || 
                         ' | Date: ' || TO_CHAR(v_date, 'DD-MON-YYYY') || 
                         ' | ' || v_type || 
                         ' | Amount: ₹' || v_amount);
  END LOOP;
  CLOSE txn_cursor;
END;
/

-- Scenario 2: ApplyAnnualFee using explicit cursor
DECLARE
  CURSOR account_cursor IS
    SELECT AccountID, Balance FROM Accounts;

  v_account_id Accounts.AccountID%TYPE;
  v_balance Accounts.Balance%TYPE;

  annual_fee CONSTANT NUMBER := 100;

BEGIN
  OPEN account_cursor;
  LOOP
    FETCH account_cursor INTO v_account_id, v_balance;
    EXIT WHEN account_cursor%NOTFOUND;

    UPDATE Accounts
    SET Balance = Balance - annual_fee,
        LastModified = SYSDATE
    WHERE AccountID = v_account_id;

    DBMS_OUTPUT.PUT_LINE('Applied ₹' || annual_fee || ' fee to AccountID: ' || v_account_id);
  END LOOP;
  CLOSE account_cursor;

  COMMIT;
END;
/

-- Scenario 3: UpdateLoanInterestRates using explicit cursor
DECLARE
  CURSOR loan_cursor IS
    SELECT LoanID, InterestRate FROM Loans;

  v_loan_id Loans.LoanID%TYPE;
  v_interest Loans.InterestRate%TYPE;

  increment_rate CONSTANT NUMBER := 0.5;

BEGIN
  OPEN loan_cursor;
  LOOP
    FETCH loan_cursor INTO v_loan_id, v_interest;
    EXIT WHEN loan_cursor%NOTFOUND;

    UPDATE Loans
    SET InterestRate = InterestRate + increment_rate
    WHERE LoanID = v_loan_id;

    DBMS_OUTPUT.PUT_LINE('Updated LoanID ' || v_loan_id || ' InterestRate to ' || (v_interest + increment_rate));
  END LOOP;
  CLOSE loan_cursor;

  COMMIT;
END;
/
