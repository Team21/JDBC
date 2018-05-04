package dao.PostgreSQLImpl;

import dao.interfaces.SalgradeDAO;
import model.Salgrade;
import model.factory.SalgradeFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PostgreSQLSalgradeDAOImpl implements SalgradeDAO {
    private final Connection connection;

    public PostgreSQLSalgradeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public Salgrade create(int grade, int losal, int hisal) {
        try {
            String sql = "INSERT INTO \"salgrade\" (\"GRADE\",\"LOSAL\",\"HISAL\") VALUES (?,?,?);";
            String sqlSelect = "SELECT * FROM \"salgrade\" WHERE \"GRADE\" = ?";
            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setInt(1, grade);
                stm.setInt(2, losal);
                stm.setInt(3, hisal);
                stm.executeUpdate();
                try (PreparedStatement stm2 = connection.prepareStatement(sqlSelect)) {
                    stm2.setInt(1, grade);
                    ResultSet rs2 = stm2.executeQuery();
                    rs2.next();
                    return SalgradeFactory.getInstance().createSalgrade(rs2.getInt("GRADE"),
                            rs2.getInt("LOSAL"), rs2.getInt("HISAL"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Salgrade read(int grade) {
        try {
            String sql = "SELECT * FROM \"salgrade\" WHERE \"GRADE\" = ?;";
            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setInt(1, grade);
                ResultSet rs = stm.executeQuery();
                rs.next();
                return SalgradeFactory.getInstance().createSalgrade(rs.getInt("GRADE"),
                        rs.getInt("LOSAL"), rs.getInt("HISAL"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Salgrade salgrade) {
        try {
            String sql = "UPDATE \"salgrade\" SET \"LOSAL\" = ?,\"HISAL\" = ? WHERE \"GRADE\" = ?";
            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setInt(1, salgrade.getLosal());
                stm.setInt(2, salgrade.getHisal());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(int grade) {
        try {
            String sql = "DELETE FROM \"salgrade\" WHERE \"GRADE\" = ?";
            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setInt(1, grade);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Salgrade> getAll() {
        try {
            List<Salgrade> list = new LinkedList<>();
            String sql = "SELECT * FROM \"salgrade\"";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    list.add(SalgradeFactory.getInstance().createSalgrade(rs.getInt("GRADE"),
                            rs.getInt("LOSAL"),
                            rs.getInt("HISAL")));
                }
            }
            return Collections.unmodifiableList(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getGrade(int sal)  {
        try {
            String sql = "SELECT \"GRADE\" FROM \"salgrade\" WHERE \"LOSAL\" <= ? AND \"HISAL\">= ?";

            int res;
            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setInt(1, sal);
                stm.setInt(2, sal);
                ResultSet rs = stm.executeQuery();
                rs.next();
                res = rs.getInt(1);
                return res;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
