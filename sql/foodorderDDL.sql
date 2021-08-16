DROP TABLE orders;
DROP TABLE menu;
DROP TABLE store;
DROP TABLE customer;

DROP SEQUENCE order_seq;

CREATE SEQUENCE order_seq
MAXVALUE 5000
MINVALUE 4;

SELECT order_seq.nextval FROM dual;

CREATE TABLE customer (
       id            VARCHAR2(50) PRIMARY KEY,
       name          VARCHAR2(50) NOT NULL,
       phone         VARCHAR2(200) NOT NULL,
       address       VARCHAR2(200) NOT NULL
);
CREATE TABLE store (
       storeName     VARCHAR2(200) PRIMARY KEY,
       storeAddress  VARCHAR2(200) NOT NULL,
       storePhone    VARCHAR2(200) NOT NULL
);
CREATE TABLE menu (
       menuName      VARCHAR2(50) PRIMARY KEY,
       menuPrice     NUMBER(10) NOT NULL,
       storeName     VARCHAR2(200) NOT NULL
);
CREATE TABLE orders (
       order_no      NUMBER(5) PRIMARY KEY,
       id            VARCHAR2(50) NOT NULL,
       storeName     VARCHAR2(200) NOT NULL,
       menuName      VARCHAR2(200) NOT NULL,
       payMethod     VARCHAR2(200) NOT NULL
);


ALTER TABLE orders  ADD FOREIGN KEY (id) REFERENCES customer(id);
ALTER TABLE orders  ADD FOREIGN KEY (storeName) REFERENCES store(storeName);
ALTER TABLE orders  ADD FOREIGN KEY (menuName) REFERENCES menu(menuName);
ALTER TABLE menu  ADD FOREIGN KEY (storeName) REFERENCES store(storeName);
