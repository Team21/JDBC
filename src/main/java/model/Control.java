package model;

import dao.PostgreSQLImpl.PostgreSQLDAOFactoryImpl;
import dao.PostgreSQLImpl.PostgreSQLDeptDAOimpl;
import dao.PostgreSQLImpl.PostgreSQLEmpDAOImpl;
import dao.PostgreSQLImpl.PostgreSQLSalgradeDAOImpl;

import java.sql.*;
import java.util.List;

public class Control {


    public static String getAll() {
        PostgreSQLDAOFactoryImpl postgreSQLDAOFactory = PostgreSQLDAOFactoryImpl.getInstance();
        PostgreSQLEmpDAOImpl postgreSQLEmpDAO = postgreSQLDAOFactory.getEmpDAO();
        PostgreSQLDeptDAOimpl postgreSQLDeptDAOimpl = postgreSQLDAOFactory.getDeptDAO();
        String result = "";
        for (Emp emp : postgreSQLEmpDAO.getAll()) {
            Dept dept = postgreSQLDeptDAOimpl.read(emp.getDeptno());


            result += "EMPNO=" + emp.getEmpno() + " NAME=" + emp.getEname() +
                    " JOB=" + emp.getJob() + " MGR=" + emp.getMgr() +
                    " HIREDATE=" + emp.getHiredate() + " SAL=" + emp.getSal() +
                    " COMM=" + emp.getComm() + " DEPTNO=" + emp.getDeptno() +
                    " DEPTNAME=" + dept.getDname() + " DEPTLOC=" + dept.getLoc().replaceAll(" ", "-") +
                    " SALGRADE=" + emp.getSal() + "<br/>";
        }
        return result;
    }

    public static String getById(int id) {
        PostgreSQLDAOFactoryImpl postgreSQLDAOFactory = PostgreSQLDAOFactoryImpl.getInstance();
        PostgreSQLEmpDAOImpl postgreSQLEmpDAO = postgreSQLDAOFactory.getEmpDAO();
        PostgreSQLDeptDAOimpl postgreSQLDeptDAOimpl = postgreSQLDAOFactory.getDeptDAO();
        String result = "";
        Emp emp = postgreSQLEmpDAO.read(id);
        Dept dept = postgreSQLDeptDAOimpl.read(emp.getDeptno());


        result += "EMPNO=" + emp.getEmpno() + " NAME=" + emp.getEname() +
                " JOB=" + emp.getJob() + " MGR=" + emp.getMgr() +
                " HIREDATE=" + emp.getHiredate() + " SAL=" + emp.getSal() +
                " COMM=" + emp.getComm() + " DEPTNO=" + emp.getDeptno() +
                " DEPTNAME=" + dept.getDname() + " DEPTLOC=" + dept.getLoc().replaceAll(" ", "-") +
                " SALGRADE=" + emp.getSal() + "<br/>";

        return result;
    }

    public static String getByName(String name) {
        PostgreSQLDAOFactoryImpl postgreSQLDAOFactory = PostgreSQLDAOFactoryImpl.getInstance();
        PostgreSQLEmpDAOImpl postgreSQLEmpDAO = postgreSQLDAOFactory.getEmpDAO();
        PostgreSQLDeptDAOimpl postgreSQLDeptDAOimpl = postgreSQLDAOFactory.getDeptDAO();
        String result = "";
        Emp emp = postgreSQLEmpDAO.read(name);
        Dept dept = postgreSQLDeptDAOimpl.read(emp.getDeptno());


        result += "EMPNO=" + emp.getEmpno() + " NAME=" + emp.getEname() +
                " JOB=" + emp.getJob() + " MGR=" + emp.getMgr() +
                " HIREDATE=" + emp.getHiredate() + " SAL=" + emp.getSal() +
                " COMM=" + emp.getComm() + " DEPTNO=" + emp.getDeptno() +
                " DEPTNAME=" + dept.getDname() + " DEPTLOC=" + dept.getLoc().replaceAll(" ", "-") +
                " SALGRADE=" + emp.getSal() + "<br/>";

        return result;
    }
}
