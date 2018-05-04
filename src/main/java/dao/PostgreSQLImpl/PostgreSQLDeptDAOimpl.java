package dao.PostgreSQLImpl;

import dao.interfaces.DeptDAO;
import model.Dept;
import model.factory.DeptFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PostgreSQLDeptDAOimpl implements DeptDAO {
    private final Connection connection;

    public PostgreSQLDeptDAOimpl(Connection connection) {
        this.connection = connection;
    }

    public Dept create(int deptno, String dname, String loc) {
        try {
            String sql = "INSERT INTO \"dept\" (\"DEPTNO\",\"DNAME\",\"LOC\") VALUES (?,?,?);";
            String sqlSelect = "SELECT * FROM \"dept\" WHERE \"DNAME\" = ?";
            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setInt(1, deptno);
                stm.setString(2, dname);
                stm.setString(3, loc);
                stm.executeUpdate();
                try (PreparedStatement stm2 = connection.prepareStatement(sqlSelect)) {
                    stm2.setString(1, dname);
                    ResultSet rs2 = stm2.executeQuery();
                    rs2.next();
                    return DeptFactory.getInstance().createDept(rs2.getInt("DEPTNO"),
                            rs2.getString("DNAME"), rs2.getString("LOC"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Dept read(int deptno) {
        try {
            String sql = "SELECT * FROM \"dept\" WHERE \"DEPTNO\" = ?;";
            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setInt(1, deptno);
                ResultSet rs = stm.executeQuery();
                rs.next();
                return DeptFactory.getInstance().createDept(rs.getInt("DEPTNO"),
                        rs.getString("DNAME"),
                        rs.getString("LOC"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Dept read(String dname) {
        try {
            String sql = "SELECT * FROM \"dept\" WHERE \"DNAME\" = ?;";
            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setString(1, dname);
                ResultSet rs = stm.executeQuery();
                rs.next();
                return DeptFactory.getInstance().createDept(rs.getInt("DEPTNO"),
                        rs.getString("DNAME"),
                        rs.getString("LOC"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Dept dept) {
        try {
            String sql = "UPDATE \"dept\" SET \"DNAME\" = ?,\"LOC\" = ? WHERE \"DEPTNO\" = ?";
            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setString(1, dept.getDname());
                stm.setString(2, dept.getLoc());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int deptno) {
        try {
            String sql = "DELETE FROM \"dept\" WHERE \"DEPTNO\" = ?";
            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setInt(1, deptno);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Dept> getAll() {
        try {
            List<Dept> list = new LinkedList<>();
            String sql = "SELECT * FROM \"dept\"";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    list.add(DeptFactory.getInstance().createDept(rs.getInt("DEPTNO"),
                            rs.getString("DNAME"),
                            rs.getString("LOC")));
                }
            }
            return Collections.unmodifiableList(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

