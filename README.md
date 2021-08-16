# 🍱 OrderSystem
> User를 고객, 점주, 관리자로 분류하여 
> - 고객은 원하는 음식을 찾아 주문하고, 
> - 점주는 메뉴 추가/수정/삭제 등을 통해 가게를 운영하고 
> - 관리자는 전체 DB에 접근하여 조회/추가/수정/삭제할 수 있는
> 음식 주문 시스템
</br>

# 1. Data Modeling
** 기환님이 그려주신 파일이 안열려서 제가 못 넣었어요ㅜ
</br>

# 2. SQL
## DDL
~~~sql
DROP TABLE orders;
DROP TABLE menu;
DROP TABLE store;
DROP TABLE customer;

CREATE SEQUENCE order_seq
MAXVALUE 5000
MINVALUE 4;

SELECT order_seq.nextval FROM dual;

CREATE TABLE CUSTOMER (
       id            VARCHAR2(50) PRIMARY KEY,
       name          VARCHAR2(50) NOT NULL,
       phone         VARCHAR2(200) NOT NULL,
       address       VARCHAR2(200) NOT NULL
);
CREATE TABLE STORE (
       storeName     VARCHAR2(200) PRIMARY KEY,
       storeAddress  VARCHAR2(200) NOT NULL,
       storePhone    VARCHAR2(200) NOT NULL
);
CREATE TABLE MENU (
       menuName      VARCHAR2(50) PRIMARY KEY,
       menuPrice     NUMBER(10) NOT NULL,
       storeName     VARCHAR2(200) NOT NULL
);
CREATE TABLE ORDERS (
       order_no      NUMBER(5) PRIMARY KEY,
       id            VARCHAR2(50) NOT NULL,
       storeName     VARCHAR2(200) NOT NULL,
       menuName      VARCHAR2(200) NOT NULL,
       payMethod     VARCHAR2(200) NOT NULL
);


ALTER TABLE ORDERS  ADD FOREIGN KEY (id) REFERENCES customer(id);
ALTER TABLE ORDERS  ADD FOREIGN KEY (storeName) REFERENCES store(storeName);
ALTER TABLE ORDERS  ADD FOREIGN KEY (menuName) REFERENCES menu(menuName);
ALTER TABLE MENU  ADD FOREIGN KEY (storeName) REFERENCES store(storeName);
~~~

## DML
~~~sql
-- Customer table INSERT
INSERT INTO customer VALUES('jm123', '김지명', '01055236548', '경기도 부천시');
INSERT INTO customer VALUES('kh345', '이기환', '01099548765', '경기도 용인');
INSERT INTO customer VALUES('js4545', '배지수', '01085465215', '인천시 부평');

-- Store table INSERT;
INSERT INTO store VALUES('족발야시장', '부천시 도약로', '0323245875');
INSERT INTO store VALUES('탕가네', '용인시 수지구', '0316548978');
INSERT INTO store VALUES('곱창이야기', '인천시 청라', '0323256548');
INSERT INTO store VALUES('BHC', '용인시', '0313546878');

-- Menu table INSERT
INSERT INTO menu VALUES('불족', 26000, '족발야시장');
INSERT INTO menu VALUES('김치찌개', 8000, '탕가네');
INSERT INTO menu VALUES('곱창', 21000, '곱창이야기');
INSERT INTO menu VALUES('치킨', 23000, 'BHC');

-- Orders table INSERT
INSERT INTO orders VALUES(1, 'jm123', '족발야시장', '불족', '현금');
INSERT INTO orders VALUES(2, 'kh345', '탕가네', '김치찌개', '카드');
INSERT INTO orders VALUES(3, 'js4545', '곱창이야기', '곱창', '삼성페이');
INSERT INTO orders VALUES(4, 'js4545', 'BHC', '치킨', '카드');

COMMIT;
~~~
</br>

# 3. Class Diagram
![classDiagram](https://user-images.githubusercontent.com/68639271/129512850-980dc859-6428-4d11-9ba8-34dd909b8dc1.gif)

