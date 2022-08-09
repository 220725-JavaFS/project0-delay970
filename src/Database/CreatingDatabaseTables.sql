CREATE DATABASE test;

ROP TABLE IF EXISTS users cascade;

CREATE TABLE users(account_id serial unique,
	user_id varchar PRIMARY key,
	pass varchar,
	user_permissions SMALLINT
);

DROP TABLE IF EXISTS accounts;

create TABLE accounts(account_id int REFERENCES users(account_id),
	account_type SMALLINT,
	min_balance numeric(14,2),
	approved bool,
	PRIMARY KEY (account_id, account_type)
);

DROP TABLE IF EXISTS permission_codes;

CREATE TABLE permission_codes(code_id serial PRIMARY KEY,
permission_name varchar(10) UNIQUE NOT null
); 

INSERT INTO permission_codes(permission_name) values
('Customer'),
('Employee'),
('Admin');

DROP TABLE IF EXISTS account_type_codes;

CREATE TABLE account_type_codes(code_id serial PRIMARY KEY,
account_type_name varchar(30) UNIQUE NOT null
);

INSERT INTO account_type_codes(account_type_name) values
('Checking'),
('Savings'),
('Money Market'),
('Certificate of Deposit'),
('Auto Loan'),
('Morgage'),
('Student Loan'),
('Personal Loan');

DROP TABLE IF EXISTS loans;

CREATE TABLE loans(account_id int REFERENCES users(account_id),
loan_type SMALLINT REFERENCES account_type_codes(code_id),
apr numeric(5,3),
months SMALLINT,
PRIMARY KEY (account_id, loan_type)
);

ALTER TABLE users ADD FOREIGN KEY (user_permissions) REFERENCES permission_codes(code_id);

ALTER TABLE accounts ADD FOREIGN KEY (account_type) REFERENCES account_type_codes(code_id);

DROP TABLE IF EXISTS transactions;

CREATE TABLE transactions(transaction_id serial PRIMARY KEY,
from_account int REFERENCES users(account_id),
from_account_type SMALLINT REFERENCES account_type_codes(code_id),
amount numeric(14,2),
to_account int REFERENCES users(account_id),
to_account_type SMALLINT REFERENCES account_type_codes(code_id)
);

--TRUNCATE users cascade;

--SELECT * from transactions where to_account = 174 or from_account = 174;
