SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = ON;
SET search_path = public, pg_catalog;
SET check_function_bodies = FALSE;
SET client_min_messages = WARNING;
SET row_security = OFF;
SET default_tablespace = '';
SET default_with_oids = FALSE;


DROP TABLE IF EXISTS EMP;
DROP table IF EXISTS DEPT;
DROP TABLE IF EXISTS SALGRADE;


CREATE SEQUENCE IF NOT EXISTS auto_increment
  START WITH 0
  INCREMENT BY 1
  MINVALUE 0
  MAXVALUE 9223372036854775801
  CACHE 1;


CREATE TABLE IF NOT EXISTS DEPT
(
  "DEPTNO" integer                                                         NOT NULL,
  "DNAME" character varying(20)                                            NOT NULL,
  "LOC" character varying(20)                                              NOT NULL,
  CONSTRAINT "DEPT_pkey" PRIMARY KEY ("DEPTNO")
);

CREATE TABLE IF NOT EXISTS EMP
(
  "EMPNO" integer NOT NULL,
  "ENAME" character varying(20)                                           NOT NULL,
  "JOB" character varying(20)                                             NOT NULL,
  "MGR" integer                                                           ,
  "HIREDATE" date                                                         NOT NULL,
  "SAL" integer                                                           NOT NULL,
  "COMM" integer                                                          ,
  "DEPTNO" integer                                                        NOT NULL,
  CONSTRAINT "EMP_pkey" PRIMARY KEY ("EMPNO"),
  FOREIGN KEY ("DEPTNO") REFERENCES DEPT ("DEPTNO")
);


CREATE TABLE IF NOT EXISTS SALGRADE
(
  "GRADE" integer NOT NULL,
  "LOSAL" integer,
  "HISAL" integer,
  CONSTRAINT "SALGRADE_pkey" PRIMARY KEY ("GRADE")
);


ALTER TABLE EMP
  OWNER to postgres;

ALTER TABLE DEPT
  OWNER to postgres;

ALTER TABLE SALGRADE
  OWNER to postgres;


INSERT INTO DEPT VALUES (10, 'ACCOUNTING', 'NEW YORK');
INSERT INTO DEPT VALUES (20, 'RESEARCH', 'DALLAS');
INSERT INTO DEPT VALUES (30, 'SALES', 'CHICAGO');
INSERT INTO DEPT VALUES (40, 'OPERATIONS', 'BOSTON');

INSERT INTO EMP VALUES
  (7369, 'SMITH', 'CLERK', 7902,
   TO_DATE('1980-01-17', 'YYYY-MM-DD'), 800, NULL, 20);
INSERT INTO EMP VALUES
  (7499, 'ALLEN', 'SALESMAN', 7698,
   TO_DATE('1981-2-20', 'YYYY-MM-DD'), 1600, 300, 30);
INSERT INTO EMP VALUES
  (7521, 'WARD', 'SALESMAN', 7698,
   TO_DATE('1981-2-22', 'YYYY-MM-DD'), 1250, 500, 30);
INSERT INTO EMP VALUES
  (7566, 'JONES', 'MANAGER', 7839,
   TO_DATE('1981-4-2', 'YYYY-MM-DD'), 2975, NULL, 20);
INSERT INTO EMP VALUES
  (7654, 'MARTIN', 'SALESMAN', 7698,
   TO_DATE('1981-9-28', 'YYYY-MM-DD'), 1250, 1400, 30);
INSERT INTO EMP VALUES
  (7698, 'BLAKE', 'MANAGER', 7839,
   TO_DATE('1981-5-1', 'YYYY-MM-DD'), 2850, NULL, 30);
INSERT INTO EMP VALUES
  (7782, 'CLARK', 'MANAGER', 7839,
   TO_DATE('1981-6-9', 'YYYY-MM-DD'), 2450, NULL, 10);
INSERT INTO EMP VALUES
  (7788, 'SCOTT', 'ANALYST', 7566,
   TO_DATE('1982-1-9', 'YYYY-MM-DD'), 3000, NULL, 20);
INSERT INTO EMP VALUES
  (7839, 'KING', 'PRESIDENT', NULL,
   TO_DATE('1981-11-17', 'YYYY-MM-DD'), 5000, NULL, 10);
INSERT INTO EMP VALUES
  (7844, 'TURNER', 'SALESMAN', 7698,
   TO_DATE('1981-9-8', 'YYYY-MM-DD'), 1500, 0, 30);
INSERT INTO EMP VALUES
  (7876, 'ADAMS', 'CLERK', 7788,
   TO_DATE('1983-1-12', 'YYYY-MM-DD'), 1100, NULL, 20);
INSERT INTO EMP VALUES
  (7900, 'JAMES', 'CLERK', 7698,
   TO_DATE('1981-12-3', 'YYYY-MM-DD'), 950, NULL, 30);
INSERT INTO EMP VALUES
  (7902, 'FORD', 'ANALYST', 7566,
   TO_DATE('1981-12-3', 'YYYY-MM-DD'), 3000, NULL, 20);
INSERT INTO EMP VALUES
  (7934, 'MILLER', 'CLERK', 7782,
   TO_DATE('1982-1-23', 'YYYY-MM-DD'), 1300, NULL, 10);




INSERT INTO SALGRADE VALUES (1, 700, 1200);
INSERT INTO SALGRADE VALUES (2, 1201, 1400);
INSERT INTO SALGRADE VALUES (3, 1401, 2000);
INSERT INTO SALGRADE VALUES (4, 2001, 3000);
INSERT INTO SALGRADE VALUES (5, 3001, 9999);