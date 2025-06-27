BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE customers CASCADE CONSTRAINTS';
EXCEPTION
  WHEN OTHERS THEN
    NULL;
END;
/

BEGIN
  EXECUTE IMMEDIATE 'CREATE TABLE customers (
    customer_id NUMBER PRIMARY KEY,
    customer_name VARCHAR2(100),
    age NUMBER
  )';
EXCEPTION
  WHEN OTHERS THEN
    NULL;
END;
/

CREATE OR REPLACE PROCEDURE AddNewCustomer(
  p_customer_id IN NUMBER,
  p_customer_name IN VARCHAR2,
  p_age IN NUMBER
)
IS
BEGIN
  INSERT INTO customers (customer_id, customer_name, age)
  VALUES (p_customer_id, p_customer_name, p_age);

  COMMIT;
  DBMS_OUTPUT.PUT_LINE('Customer added successfully.');
EXCEPTION
  WHEN DUP_VAL_ON_INDEX THEN
    ROLLBACK;
    DBMS_OUTPUT.PUT_LINE('Insertion failed: Customer ID already exists.');
  WHEN OTHERS THEN
    ROLLBACK;
    DBMS_OUTPUT.PUT_LINE('Insertion failed: ' || SQLERRM);
END;
/
BEGIN
  AddNewCustomer(101, 'Sundar', 35);
END;
/
BEGIN
  AddNewCustomer(101, 'Sundar', 35);
END;
/

