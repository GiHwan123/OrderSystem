-- Customer table INSERT
INSERT INTO customer VALUES('jm123', '������', '01055236548', '��⵵ ��õ��');
INSERT INTO customer VALUES('kh345', '�̱�ȯ', '01099548765', '��⵵ ����');
INSERT INTO customer VALUES('js4545', '������', '01085465215', '��õ�� ����');

-- Store table INSERT;
INSERT INTO store VALUES('���߾߽���', '��õ�� �����', '0323245875');
INSERT INTO store VALUES('������', '���ν� ������', '0316548978');
INSERT INTO store VALUES('��â�̾߱�', '��õ�� û��', '0323256548');
INSERT INTO store VALUES('BHC', '���ν�', '0313546878');

-- Menu table INSERT
INSERT INTO menu VALUES('����', 26000, '���߾߽���');
INSERT INTO menu VALUES('��ġ�', 8000, '������');
INSERT INTO menu VALUES('��â', 21000, '��â�̾߱�');
INSERT INTO menu VALUES('ġŲ', 23000, 'BHC');

-- Orders table INSERT
INSERT INTO orders VALUES(1, 'jm123', '���߾߽���', '����', '����');
INSERT INTO orders VALUES(2, 'kh345', '������', '��ġ�', 'ī��');
INSERT INTO orders VALUES(3, 'js4545', '��â�̾߱�', '��â', '�Ｚ����');
INSERT INTO orders VALUES(4, 'js4545', 'BHC', 'ġŲ', 'ī��');

COMMIT;