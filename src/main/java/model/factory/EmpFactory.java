package model.factory;

import model.Emp;

import java.sql.Date;

public class EmpFactory {
    private static EmpFactory instance;

    public static EmpFactory getInstance() {
        if (instance == null)
            instance = new EmpFactory();
        return instance;
    }


    public Emp createEmp(int empno, String ename, String job, int mgr, Date hiredate, int sal, int comm, int deptno) {
        return new Emp(empno, ename, job, mgr, hiredate, sal,  comm, deptno);
    }
}
