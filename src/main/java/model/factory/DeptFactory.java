package model.factory;

import model.Dept;

public class DeptFactory {

    private static DeptFactory instance;

    public static DeptFactory getInstance() {
        if (instance == null)
            instance = new DeptFactory();
        return instance;
    }


    public Dept createDept(int deptno, String dname, String loc) {
        return new Dept(deptno, dname, loc);
    }
}
