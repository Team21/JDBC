package dao.interfaces;

import model.Emp;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

public interface EmpDAO {

    Emp create(int empno, String ename, String job, int mgr, Date hiredate, int sal, int comm, int deptno) throws SQLException;

    Emp read(int empno) throws SQLException;

    Emp read(String ename) throws SQLException;

    void update(Emp emp) throws SQLException;

    void delete(int empno) throws SQLException;

    List<Emp> getAll()throws SQLException;

}
