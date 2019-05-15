

-- Drop table
create sequence TESTDB.PUBLIC.hibernate_sequence;
-- DROP TABLE TESTDB.PUBLIC.ACCOUNTS;

CREATE TABLE TESTDB.PUBLIC.ACCOUNTS (
	ID DOUBLE NOT NULL AUTO_INCREMENT,
	SORTCODE VARCHAR(8) NOT NULL,
	ACCOUNTNUMBER VARCHAR(8) NOT NULL,
	ACCOUNTBALANCE DOUBLE,
	CONSTRAINT ACCOUNTS_PK PRIMARY KEY (ID)
);




-- Drop table

-- DROP TABLE TESTDB.PUBLIC."Transaction";

CREATE TABLE TESTDB.PUBLIC."TRANSACTION" (
	ID DOUBLE NOT NULL AUTO_INCREMENT,
	ORIGIN_ACCOUNT VARCHAR(8) NOT NULL,
	DESTINATION_ACCOUNT VARCHAR(8) NOT NULL,
	AMOUNT DOUBLE NOT NULL,
	"DATE" TIMESTAMP NOT NULL,
	CONSTRAINT TRANSACTION_PK PRIMARY KEY (ID)
);



INSERT INTO TESTDB.PUBLIC.ACCOUNTS (SORTCODE,ACCOUNTNUMBER,ACCOUNTBALANCE,ID) VALUES (
'01-01-12','43322122',80000,1);
INSERT INTO TESTDB.PUBLIC.ACCOUNTS (SORTCODE,ACCOUNTNUMBER,ACCOUNTBALANCE,ID) VALUES (
'01-01-13','43322123',90000,2);
