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