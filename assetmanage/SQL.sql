CREATE TABLE dept_A(
NAME  CHAR(50),
number  INT,
sinleprice  INT,
);
CREATE TABLE dept_B(
NAME  CHAR(50),
number  INT,
sinleprice  INT
);
CREATE TABLE dept_C(
NAME  CHAR(50),
number  INT,
sinleprice  INT
);
CREATE TABLE apply(
NAME  CHAR(40),
number  INT,
user_name  CHAR(40)
);

INSERT INTO empuser
VALUE('111','123','A');
INSERT INTO empuser
VALUE('222','123','A');
INSERT INTO empuser
VALUE('333','123','B');
INSERT INTO empuser
VALUE('444','123','C');
INSERT INTO empuser
VALUE('555','123','A');
INSERT INTO empuser
VALUE('666','123','B');
INSERT INTO empuser
VALUE('777','123','B');
INSERT INTO empuser
VALUE('888','123','C');
INSERT INTO empuser
VALUE('999','123','A');
INSERT INTO empuser
VALUE('000','123','C');


INSERT INTO adminuser
VALUE('001','123','小刘');
INSERT INTO adminuser
VALUE('002','123','小吴');
INSERT INTO adminuser
VALUE('003','123','小李');
INSERT INTO adminuser
VALUE('004','123','小钱');


INSERT INTO storekeeper
VALUE('100','123','老大爷');



