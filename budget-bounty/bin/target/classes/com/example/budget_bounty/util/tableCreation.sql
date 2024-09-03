-- Author: Vaishali

SELECT instance_name FROM v$instance;

-- User table
CREATE TABLE Users (
                       user_id int PRIMARY KEY,
                       name VARCHAR2(50) NOT NULL,
                       username VARCHAR2(50) NOT NULL,
                       password VARCHAR2(50) NOT NULL,
                       phone_number VARCHAR2(15) NOT NULL UNIQUE,
                       email VARCHAR2(100) NOT NULL UNIQUE,
                       role int DEFAULT 0
);
ALTER TABLE Users ADD CONSTRAINT unique_username UNIQUE (username);

INSERT INTO Users (user_id, name, username, password, phone_number, email, role)
VALUES (1, 'Alice Smith', 'alice.smith', 'alice1', '7268936786', 'alice.smith@example.com', 0);

INSERT INTO Users (user_id, name, username, password, phone_number, email, role)
VALUES (2, 'Bob Johnson', 'bob.johnson', 'bob1', '9926745386', 'bob.johnson@example.com', 0);

INSERT INTO Users (user_id, name, username, password, phone_number, email, role)
VALUES (3, 'Carol Williams', 'carol.williams', 'carol1', '9892683672', 'carol.williams@example.com', 0);

INSERT INTO Users (user_id, name, username, password, phone_number, email, role)
VALUES (4, 'David Brown', 'david.brown', 'david1', '9978936287', 'david.brown@example.com', 0);

INSERT INTO Users (user_id, name, username, password, phone_number, email, role)
VALUES (5, 'Eve Davis', 'eve.davis', 'password202', '9912242765', 'eve.davis@example.com', 0);

SELECT * FROM Users;

-- Bank table
CREATE TABLE Bank (
                      bank_id int PRIMARY KEY,
                      bank_name varchar2(50),
                      IFSC varchar2(11),
                      account_number varchar(50) UNIQUE,
                      upi_id varchar(50) UNIQUE,
                      account_type varchar(50),
                      balance number,
                      user_id int,
                      CONSTRAINT fk_user
                          FOREIGN KEY (user_id)
                              REFERENCES Users(user_id),
                      CONSTRAINT upi_or_accnumber
                          CHECK (upi_id IS NOT NULL OR account_number IS NOT NULL)
);

INSERT INTO Bank (bank_id, bank_name, ifsc, account_number, upi_id, account_type, balance, user_id)
VALUES (1, 'HDFC Bank', 'IFSC0001', '123456789012', NULL, 'Savings', 10000, 1);

INSERT INTO Bank (bank_id, bank_name, ifsc, account_number, upi_id, account_type, balance, user_id)
VALUES (2, 'ICICI Bank', 'IFSC0002', NULL, 'bob@okicicibank', 'Checking', 100000, 2);

INSERT INTO Bank (bank_id, bank_name, ifsc, account_number, upi_id, account_type, balance, user_id)
VALUES (3, 'SBI Bank', 'IFSC0003', '987654321098', NULL, 'Savings', 2000, 3);

INSERT INTO Bank (bank_id, bank_name, ifsc, account_number, upi_id, account_type, balance, user_id)
VALUES (4, 'HDFC Bank', 'IFSC0004', NULL, 'david@okhdfcbank', 'Business', 30000, 4);

INSERT INTO Bank (bank_id, bank_name, ifsc, account_number, upi_id, account_type, balance, user_id)
VALUES (5, 'ICICI Bank', 'IFSC0005', '555555555555', 'eve@okicicibank', 'Checking', 50000, 5);

SELECT * FROM Bank;

-- Scheduler table
CREATE TABLE Scheduler (
                           scheduler_id INT PRIMARY KEY,
                           user_id INT,
                           bill_type VARCHAR2(100) NOT NULL, -- e.g., electricity, rent, broadband
                           customer_id INT, -- for services like electricity/gas
                           bill_name VARCHAR2(100), -- user-defined name for the bill
                           payee_acc VARCHAR2(100), -- account for rent-type payments
                           amount NUMBER NOT NULL,
                           due_date DATE NOT NULL,
                           scheduled_date DATE,
                           is_recurring NUMBER(1) DEFAULT 0, -- 0 for FALSE, 1 for TRUE
                           frequency VARCHAR2(20), -- e.g., monthly, quarterly, yearly
                           end_after INT, -- end after a certain number of payments
                           end_by DATE, -- end after a specific date
                           is_paid NUMBER(1) DEFAULT 0, -- 0 for FALSE, 1 for TRUE
                           CONSTRAINT fk_user_schedule
                               FOREIGN KEY (user_id)
                                   REFERENCES Users(user_id),
                           CONSTRAINT check_freq_if_recurring
                               CHECK (is_recurring = 0 OR (is_recurring = 1 AND frequency IS NOT NULL))
);

INSERT INTO Scheduler (scheduler_id, user_id, bill_type, customer_id, bill_name, payee_acc, amount, due_date, scheduled_date, is_recurring, frequency, end_after, end_by, is_paid)
VALUES (1, 2, 'electricity', 1001, 'Monthly Electric Bill', '1234567890', 150.00, TO_DATE('2024-09-01', 'YYYY-MM-DD'), TO_DATE('2024-08-01', 'YYYY-MM-DD'), 1, 'monthly', NULL, NULL, 0);

INSERT INTO Scheduler (scheduler_id, user_id, bill_type, customer_id, bill_name, payee_acc, amount, due_date, scheduled_date, is_recurring, frequency, end_after, end_by, is_paid)
VALUES (2, 4, 'rent', 2001, 'Monthly Rent Payment', NULL, 1200.00, TO_DATE('2024-09-05', 'YYYY-MM-DD'), TO_DATE('2024-08-05', 'YYYY-MM-DD'), 0, NULL, NULL, NULL, 0);

-- Transaction table
CREATE TABLE Transaction (
                             transaction_id INT PRIMARY KEY,
                             user_id INT,
                             transaction_date DATE,
                             transaction_name VARCHAR2(50),
                             from_account_number VARCHAR2(50),
                             to_account_number VARCHAR2(50),
                             transaction_type VARCHAR2(50), -- credited/debited
                             amount NUMBER,
                             reference_number VARCHAR2(50) UNIQUE,
                             CONSTRAINT fk_transaction_user
                                 FOREIGN KEY (user_id)
                                     REFERENCES Users(user_id)
);

SELECT * FROM Transaction;
-- Author: Vaishali

SELECT instance_name FROM v$instance;

-- User table
CREATE TABLE Users (
                       user_id int PRIMARY KEY,
                       name VARCHAR2(50) NOT NULL,
                       username VARCHAR2(50) NOT NULL,
                       password VARCHAR2(50) NOT NULL,
                       phone_number VARCHAR2(15) NOT NULL UNIQUE,
                       email VARCHAR2(100) NOT NULL UNIQUE,
                       role int DEFAULT 0
);
ALTER TABLE Users ADD CONSTRAINT unique_username UNIQUE (username);

INSERT INTO Users (user_id, name, username, password, phone_number, email, role)
VALUES (1, 'Alice Smith', 'alice.smith', 'alice1', '7268936786', 'alice.smith@example.com', 0);

INSERT INTO Users (user_id, name, username, password, phone_number, email, role)
VALUES (2, 'Bob Johnson', 'bob.johnson', 'bob1', '9926745386', 'bob.johnson@example.com', 0);

INSERT INTO Users (user_id, name, username, password, phone_number, email, role)
VALUES (3, 'Carol Williams', 'carol.williams', 'carol1', '9892683672', 'carol.williams@example.com', 0);

INSERT INTO Users (user_id, name, username, password, phone_number, email, role)
VALUES (4, 'David Brown', 'david.brown', 'david1', '9978936287', 'david.brown@example.com', 0);

INSERT INTO Users (user_id, name, username, password, phone_number, email, role)
VALUES (5, 'Eve Davis', 'eve.davis', 'password202', '9912242765', 'eve.davis@example.com', 0);

SELECT * FROM Users;

-- Bank table
CREATE TABLE Bank (
                      bank_id int PRIMARY KEY,
                      bank_name varchar2(50),
                      IFSC varchar2(11),
                      account_number varchar(50) UNIQUE,
                      upi_id varchar(50) UNIQUE,
                      account_type varchar(50),
                      balance number,
                      user_id int,
                      CONSTRAINT fk_user
                          FOREIGN KEY (user_id)
                              REFERENCES Users(user_id),
                      CONSTRAINT upi_or_accnumber
                          CHECK (upi_id IS NOT NULL OR account_number IS NOT NULL)
);

INSERT INTO Bank (bank_id, bank_name, ifsc, account_number, upi_id, account_type, balance, user_id)
VALUES (1, 'HDFC Bank', 'IFSC0001', '123456789012', NULL, 'Savings', 10000, 1);

INSERT INTO Bank (bank_id, bank_name, ifsc, account_number, upi_id, account_type, balance, user_id)
VALUES (2, 'ICICI Bank', 'IFSC0002', NULL, 'bob@okicicibank', 'Checking', 100000, 2);

INSERT INTO Bank (bank_id, bank_name, ifsc, account_number, upi_id, account_type, balance, user_id)
VALUES (3, 'SBI Bank', 'IFSC0003', '987654321098', NULL, 'Savings', 2000, 3);

INSERT INTO Bank (bank_id, bank_name, ifsc, account_number, upi_id, account_type, balance, user_id)
VALUES (4, 'HDFC Bank', 'IFSC0004', NULL, 'david@okhdfcbank', 'Business', 30000, 4);

INSERT INTO Bank (bank_id, bank_name, ifsc, account_number, upi_id, account_type, balance, user_id)
VALUES (5, 'ICICI Bank', 'IFSC0005', '555555555555', 'eve@okicicibank', 'Checking', 50000, 5);

SELECT * FROM Bank;

-- Scheduler table
CREATE TABLE Scheduler (
                           scheduler_id INT PRIMARY KEY,
                           user_id INT,
                           bill_type VARCHAR2(100) NOT NULL, -- e.g., electricity, rent, broadband
                           customer_id INT, -- for services like electricity/gas
                           bill_name VARCHAR2(100), -- user-defined name for the bill
                           payee_acc VARCHAR2(100), -- account for rent-type payments
                           amount NUMBER NOT NULL,
                           due_date DATE NOT NULL,
                           scheduled_date DATE,
                           is_recurring NUMBER(1) DEFAULT 0, -- 0 for FALSE, 1 for TRUE
                           frequency VARCHAR2(20), -- e.g., monthly, quarterly, yearly
                           end_after INT, -- end after a certain number of payments
                           end_by DATE, -- end after a specific date
                           is_paid NUMBER(1) DEFAULT 0, -- 0 for FALSE, 1 for TRUE
                           CONSTRAINT fk_user_schedule
                               FOREIGN KEY (user_id)
                                   REFERENCES Users(user_id),
                           CONSTRAINT check_freq_if_recurring
                               CHECK (is_recurring = 0 OR (is_recurring = 1 AND frequency IS NOT NULL))
);

INSERT INTO Scheduler (scheduler_id, user_id, bill_type, customer_id, bill_name, payee_acc, amount, due_date, scheduled_date, is_recurring, frequency, end_after, end_by, is_paid)
VALUES (1, 2, 'electricity', 1001, 'Monthly Electric Bill', '1234567890', 150.00, TO_DATE('2024-09-01', 'YYYY-MM-DD'), TO_DATE('2024-08-01', 'YYYY-MM-DD'), 1, 'monthly', NULL, NULL, 0);

INSERT INTO Scheduler (scheduler_id, user_id, bill_type, customer_id, bill_name, payee_acc, amount, due_date, scheduled_date, is_recurring, frequency, end_after, end_by, is_paid)
VALUES (2, 4, 'rent', 2001, 'Monthly Rent Payment', NULL, 1200.00, TO_DATE('2024-09-05', 'YYYY-MM-DD'), TO_DATE('2024-08-05', 'YYYY-MM-DD'), 0, NULL, NULL, NULL, 0);

-- Transaction table
CREATE TABLE Transaction (
                             transaction_id INT PRIMARY KEY,
                             user_id INT,
                             transaction_date DATE,
                             transaction_name VARCHAR2(50),
                             from_account_number VARCHAR2(50),
                             to_account_number VARCHAR2(50),
                             transaction_type VARCHAR2(50), -- credited/debited
                             amount NUMBER,
                             reference_number VARCHAR2(50) UNIQUE,
                             CONSTRAINT fk_transaction_user
                                 FOREIGN KEY (user_id)
                                     REFERENCES Users(user_id)
);

SELECT * FROM Transaction;
