package dao.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface DAOFactory {

    Connection getConnection() throws SQLException;

    DeptDAO getDeptDAO();

    EmpDAO getEmpDAO();

    SalgradeDAO getSalgradeDAO();

}
