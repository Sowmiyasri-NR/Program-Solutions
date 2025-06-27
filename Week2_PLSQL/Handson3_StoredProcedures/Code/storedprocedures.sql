BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE employees CASCADE CONSTRAINTS';
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

BEGIN
  EXECUTE IMMEDIATE 'CREATE TABLE employees (
    emp_id NUMBER PRIMARY KEY,
    emp_name VARCHAR2(100),
    department_id NUMBER,
    salary NUMBER
  )';
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

INSERT INTO employees VALUES (1, 'Anu', 2, 30000);
INSERT INTO employees VALUES (2, 'Ravi', 2, 35000);
INSERT INTO employees VALUES (3, 'Meena', 1, 28000);
COMMIT;
/

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
  p_department_id IN NUMBER,
  p_bonus_percent IN NUMBER
)
IS
BEGIN
  UPDATE employees
  SET salary = salary + (salary * p_bonus_percent / 100)
  WHERE department_id = p_department_id;

  COMMIT;
  DBMS_OUTPUT.PUT_LINE('Employee bonuses updated successfully.');
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    DBMS_OUTPUT.PUT_LINE('Error updating bonuses: ' || SQLERRM);
END;
/

BEGIN
  UpdateEmployeeBonus(2, 10);
END;
/
BEGIN
  ProcessMonthlyInterest;
END;
/
BEGIN
  TransferFunds(101, 102, 500);
END;
/
