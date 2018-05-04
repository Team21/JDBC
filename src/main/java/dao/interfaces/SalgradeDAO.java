package dao.interfaces;

import model.Salgrade;

import java.sql.SQLException;
import java.util.List;

public interface SalgradeDAO {

    Salgrade create(int grade, int losal, int hisal) throws SQLException;

    Salgrade read(int grade) throws SQLException;

    void update(Salgrade salgrade) throws SQLException;

    void delete(int grade) throws SQLException;

    List<Salgrade> getAll()throws SQLException;

    public int getGrade(int sal);

}
