package dao.interfaces;

import model.Dept;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface DeptDAO {

    Dept create(int deptno, String dname, String loc) throws SQLException;

    Dept read(int deptno) throws SQLException;

    Dept read(String dname) throws SQLException;

    void update(Dept dept) throws SQLException;

    void delete(int deptno) throws SQLException;

    List<Dept> getAll()throws SQLException;

}
