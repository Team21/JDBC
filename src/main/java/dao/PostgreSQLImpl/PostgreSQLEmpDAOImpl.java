package dao.PostgreSQLImpl;

import dao.interfaces.EmpDAO;
import model.Emp;
import model.factory.EmpFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PostgreSQLEmpDAOImpl implements EmpDAO {
    private final Connection connection;

    public PostgreSQLEmpDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public Emp create(int empno, String ename, String job, int mgr,
                      Date hiredate, int sal, int comm, int deptno) {
        try {
            String sqlEmp = "INSERT INTO \"emp\" (\"EMPNO\",\"ENAME\",\"JOB\",\"MGR\",\"HIREDATE\",\"SAL\",\"COMM\", \"DEPTNO\") " +
                    "VALUES (?,?,?,?,?,?,?,?);";


            String sqlSelect = "SELECT * FROM \"emp\" WHERE \"ENAME\" = ?";
            try (PreparedStatement stm = connection.prepareStatement(sqlEmp)) {
                stm.setInt(1, empno);
                stm.setString(2, ename);
                stm.setString(3, job);
                stm.setInt(4, mgr);
                stm.setDate(5, hiredate);
                stm.setInt(6, sal);
                stm.setInt(7, comm);
                stm.setInt(8, deptno);
                stm.executeUpdate();
                try (PreparedStatement stm2 = connection.prepareStatement(sqlSelect)) {
                    stm2.setString(1, ename);
                    ResultSet rs2 = stm2.executeQuery();
                    rs2.next();
                    return EmpFactory.getInstance().createEmp(rs2.getInt("EMPNO"),
                            rs2.getString("ENAME"),
                            rs2.getString("JOB"),
                            rs2.getInt("MGR"),
                            rs2.getDate("HIREDATE"),
                            rs2.getInt("SAL"),
                            rs2.getInt("COMM"),
                            rs2.getInt("DEPTNO"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Emp read(int empno) {
        try {
            String sql = "SELECT * FROM \"emp\" WHERE \"EMPNO\" = ?;";
            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setInt(1, empno);
                ResultSet rs = stm.executeQuery();
                rs.next();
                return EmpFactory.getInstance().createEmp(rs.getInt("EMPNO"),
                        rs.getString("ENAME"),
                        rs.getString("JOB"),
                        rs.getInt("MGR"),
                        rs.getDate("HIREDATE"),
                        rs.getInt("SAL"),
                        rs.getInt("COMM"),
                        rs.getInt("DEPTNO"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Emp read(String ename) {
        try {
            String sql = "SELECT * FROM \"emp\" WHERE \"ENAME\" = ?;";
            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setString(1, ename.toUpperCase().trim());
                ResultSet rs = stm.executeQuery();
                rs.next();
                return EmpFactory.getInstance().createEmp(rs.getInt("EMPNO"),
                        rs.getString("ENAME"),
                        rs.getString("JOB"),
                        rs.getInt("MGR"),
                        rs.getDate("HIREDATE"),
                        rs.getInt("SAL"),
                        rs.getInt("COMM"),
                        rs.getInt("DEPTNO"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Emp emp) {
        try {
            String sql = "UPDATE \"emp\" SET \"ENAME\" = ?,\"JOB\" = ?,\"MGR\" = ?, \"HIREDATE\" = ?,\"SAL\" = ?," +
                    "\"COMM\" = ?, \"DEPTNO\" = ? WHERE \"EMPNO\" = ?";
            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setString(1, emp.getEname());
                stm.setString(2, emp.getJob());
                stm.setInt(3, emp.getMgr());
                stm.setDate(4, emp.getHiredate());
                stm.setInt(5, emp.getSal());
                stm.setInt(6, emp.getComm());
                stm.setInt(7, emp.getDeptno());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(int empno) {
        try {
            String sql = "DELETE FROM \"emp\" WHERE \"EMPNO\" = ?";
            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setInt(1, empno);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Emp> getAll() {
        try {
            List<Emp> list = new LinkedList<>();
            String sql = "SELECT * FROM \"emp\"";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    list.add(EmpFactory.getInstance().createEmp(rs.getInt("EMPNO"),
                            rs.getString("ENAME"),
                            rs.getString("JOB"),
                            rs.getInt("MGR"),
                            rs.getDate("HIREDATE"),
                            rs.getInt("SAL"),
                            rs.getInt("COMM"),
                            rs.getInt("DEPTNO")));
                }
            }
            return Collections.unmodifiableList(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    

}
