package dao.PostgreSQLImpl;

import dao.interfaces.DAOFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PostgreSQLDAOFactoryImpl implements DAOFactory {

    private static final String DB_PATH = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "root";
    private static final String START_SCRIPT_NAME = "C:\\Users\\MikeSunstrike\\IdeaProjects\\JDBC\\src\\main\\resources\\startscript.sql";

    private static PostgreSQLDAOFactoryImpl instance;
    private Connection connection;


    private PostgreSQLDAOFactoryImpl(String path) {
        connection = getConnection();
        executeSqlStartScript(path);
    }

    private PostgreSQLDAOFactoryImpl() {
        connection = getConnection();
        executeSqlStartScript(START_SCRIPT_NAME);
    }

    public static PostgreSQLDAOFactoryImpl getInstance(String path) {
        if (instance == null)
            instance = new PostgreSQLDAOFactoryImpl(path);
        return instance;
    }

    public static PostgreSQLDAOFactoryImpl getInstance() {
        if (instance == null)
            instance = new PostgreSQLDAOFactoryImpl();
        return instance;
    }

//    public void setConnection(String pathStartScript) {
//        try {
//            Class.forName(DRIVER_NAME);
//            connection = DriverManager.getConnection(DB_PATH, USER, PASS);
//        } catch (SQLException e) {
//            System.out.println("Error connection");
//        } catch (ClassNotFoundException e) {
//            System.out.println("Driver was not found");
//        }
//        executeSqlStartScript(pathStartScript);
//    }


    public Connection getConnection() {

        try {
            Context context = new InitialContext();
            Context env = (Context) context.lookup("java:comp/env");
            DataSource dataSource = (DataSource) env.lookup("jdbc/cracker");
            return dataSource.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void executeSqlStartScript(String path) {
        String delimiter = ";";
        Scanner scanner;
        try {
            scanner = new Scanner(new FileInputStream(path)).useDelimiter(delimiter);
            Statement currentStatement = null;
            while (scanner.hasNext()) {
                String rawStatement = scanner.next() + delimiter;
                try {
                    currentStatement = connection.createStatement();
                    currentStatement.execute(rawStatement);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (currentStatement != null) {
                        try {
                            currentStatement.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    currentStatement = null;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public PostgreSQLDeptDAOimpl getDeptDAO() {
        return new PostgreSQLDeptDAOimpl(connection);
    }


    public PostgreSQLEmpDAOImpl getEmpDAO() {
        return new PostgreSQLEmpDAOImpl(connection);
    }


    public PostgreSQLSalgradeDAOImpl getSalgradeDAO() {
        return new PostgreSQLSalgradeDAOImpl(connection);
    }


}
