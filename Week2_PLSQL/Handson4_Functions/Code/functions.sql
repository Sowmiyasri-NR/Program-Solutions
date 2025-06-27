SET SERVEROUTPUT ON;

BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Transactions CASCADE CONSTRAINTS';
  EXECUTE IMMEDIATE 'DROP TABLE Loans CASCADE CONSTRAINTS';
  EXECUTE IMMEDIATE 'DROP TABLE Accounts CASCADE CONSTRAINTS';
  EXECUTE IMMEDIATE 'DROP TABLE Customers CASCADE CONSTRAINTS';
  EXECUTE IMMEDIATE 'DROP TABLE Employees CASCADE CONSTRAINTS';
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE Customers (
  CustomerID NUMBER PRIMARY KEY,
  Name VARCHAR2(100),
  DOB DATE,
  Balance NUMBER,
  LastModified DATE
);

CREATE TABLE Accounts (
  AccountID NUMBER PRIMARY KEY,
  CustomerID NUMBER,
  AccountType VARCHAR2(20),
  Balance NUMBER,
  LastModified DATE,
  FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Transactions (
  TransactionID NUMBER PRIMARY KEY,
  AccountID NUMBER,
  TransactionDate DATE,
  Amount NUMBER,
  TransactionType VARCHAR2(10),
  FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

CREATE TABLE Loans (
  LoanID NUMBER PRIMARY KEY,
  CustomerID NUMBER,
  LoanAmount NUMBER,
  InterestRate NUMBER,
  StartDate DATE,
  EndDate DATE,
  FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Employees (
  EmployeeID NUMBER PRIMARY KEY,
  Name VARCHAR2(100),
  Position VARCHAR2(50),
  Salary NUMBER,
  Department VARCHAR2(50),
  HireDate DATE
);

INSERT INTO Customers VALUES (1, 'John Doe', TO_DATE('1985-05-15', 'YYYY-MM-DD'), 1000, SYSDATE);
INSERT INTO Customers VALUES (2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 1500, SYSDATE);

INSERT INTO Accounts VALUES (1, 1, 'Savings', 1000, SYSDATE);
INSERT INTO Accounts VALUES (2, 2, 'Checking', 1500, SYSDATE);

INSERT INTO Transactions VALUES (1, 1, SYSDATE, 200, 'Deposit');
INSERT INTO Transactions VALUES (2, 2, SYSDATE, 300, 'Withdrawal');

INSERT INTO Loans VALUES (1, 1, 5000, 5, SYSDATE, ADD_MONTHS(SYSDATE, 60));

INSERT INTO Employees VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));
INSERT INTO Employees VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));

COMMIT;
/

CREATE OR REPLACE FUNCTION CalculateAge(p_dob DATE)
RETURN NUMBER
IS
  v_age NUMBER;
BEGIN
  v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
  RETURN v_age;
END;
/

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
  p_loan_amount IN NUMBER,
  p_interest_rate IN NUMBER,
  p_years IN NUMBER
) RETURN NUMBER
IS
  v_monthly_rate NUMBER := p_interest_rate / (12 * 100);
  v_months NUMBER := p_years * 12;
  v_installment NUMBER;
BEGIN
  v_installment := p_loan_amount * v_monthly_rate / (1 - POWER(1 + v_monthly_rate, -v_months));
  RETURN ROUND(v_installment, 2);
END;
/

CREATE OR REPLACE FUNCTION HasSufficientBalance(
  p_account_id IN NUMBER,
  p_amount IN NUMBER
) RETURN BOOLEAN
IS
  v_balance NUMBER;
BEGIN
  SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_account_id;
  RETURN v_balance >= p_amount;
EXCEPTION
  WHEN NO_DATA_FOUND THEN RETURN FALSE;
  WHEN OTHERS THEN RETURN FALSE;
END;
/

-- Test CalculateAge
DECLARE
  v_age NUMBER;
BEGIN
  SELECT CalculateAge(DOB) INTO v_age FROM Customers WHERE CustomerID = 1;
  DBMS_OUTPUT.PUT_LINE('Customer Age: ' || v_age);
END;
/

-- Test CalculateMonthlyInstallment
DECLARE
  v_emi NUMBER;
BEGIN
  v_emi := CalculateMonthlyInstallment(5000, 5, 5);
  DBMS_OUTPUT.PUT_LINE('Monthly Installment: ' || v_emi);
END;
/

-- Test HasSufficientBalance
DECLARE
  v_result BOOLEAN;
BEGIN
  v_result := HasSufficientBalance(1, 800);
  IF v_result THEN
    DBMS_OUTPUT.PUT_LINE('Sufficient balance');
  ELSE
    DBMS_OUTPUT.PUT_LINE('Insufficient balance');
  END IF;
END;
/
