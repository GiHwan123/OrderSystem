# ๐ฑ OrderSystem
> User๋ฅผ ๊ณ ๊ฐ, ์ ์ฃผ, ๊ด๋ฆฌ์๋ก ๋ถ๋ฅํ์ฌ 
> - ๊ณ ๊ฐ์ ์ํ๋ ์์์ ์ฐพ์ ์ฃผ๋ฌธํ๊ณ , 
> - ์ ์ฃผ๋ ๋ฉ๋ด ์ถ๊ฐ/์์ /์ญ์  ๋ฑ์ ํตํด ๊ฐ๊ฒ๋ฅผ ์ด์ํ๊ณ  
> - ๊ด๋ฆฌ์๋ ์ ์ฒด DB์ ์ ๊ทผํ์ฌ ์กฐํ/์ถ๊ฐ/์์ /์ญ์ ํ  ์ ์๋
> ์์ ์ฃผ๋ฌธ ์์คํ
</br>

# 1. Data Modeling
![ERD](https://user-images.githubusercontent.com/57335699/129515004-fef085b2-e5a5-44e6-b9e5-9f7155d850b1.PNG)
</br>

# 2. SQL
## DDL
~~~sql
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
~~~

## DML
~~~sql
-- Customer table INSERT
INSERT INTO customer VALUES('jm123', '๊น์ง๋ช', '01055236548', '๊ฒฝ๊ธฐ๋ ๋ถ์ฒ์');
INSERT INTO customer VALUES('kh345', '์ด๊ธฐํ', '01099548765', '๊ฒฝ๊ธฐ๋ ์ฉ์ธ');
INSERT INTO customer VALUES('js4545', '๋ฐฐ์ง์', '01085465215', '์ธ์ฒ์ ๋ถํ');

-- Store table INSERT;
INSERT INTO store VALUES('์กฑ๋ฐ์ผ์์ฅ', '๋ถ์ฒ์ ๋์ฝ๋ก', '0323245875');
INSERT INTO store VALUES('ํ๊ฐ๋ค', '์ฉ์ธ์ ์์ง๊ตฌ', '0316548978');
INSERT INTO store VALUES('๊ณฑ์ฐฝ์ด์ผ๊ธฐ', '์ธ์ฒ์ ์ฒญ๋ผ', '0323256548');
INSERT INTO store VALUES('BHC', '์ฉ์ธ์', '0313546878');

-- Menu table INSERT
INSERT INTO menu VALUES('๋ถ์กฑ', 26000, '์กฑ๋ฐ์ผ์์ฅ');
INSERT INTO menu VALUES('๊น์น์ฐ๊ฐ', 8000, 'ํ๊ฐ๋ค');
INSERT INTO menu VALUES('๊ณฑ์ฐฝ', 21000, '๊ณฑ์ฐฝ์ด์ผ๊ธฐ');
INSERT INTO menu VALUES('์นํจ', 23000, 'BHC');

-- Orders table INSERT
INSERT INTO orders VALUES(1, 'jm123', '์กฑ๋ฐ์ผ์์ฅ', '๋ถ์กฑ', 'ํ๊ธ');
INSERT INTO orders VALUES(2, 'kh345', 'ํ๊ฐ๋ค', '๊น์น์ฐ๊ฐ', '์นด๋');
INSERT INTO orders VALUES(3, 'js4545', '๊ณฑ์ฐฝ์ด์ผ๊ธฐ', '๊ณฑ์ฐฝ', '์ผ์ฑํ์ด');
INSERT INTO orders VALUES(4, 'js4545', 'BHC', '์นํจ', '์นด๋');

COMMIT;
~~~
</br>

# 3. Class Diagram
![classDiagram](https://user-images.githubusercontent.com/68639271/129512850-980dc859-6428-4d11-9ba8-34dd909b8dc1.gif)

