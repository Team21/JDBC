//import dao.PostgreSQLImpl.PostgreSQLDAOFactoryImpl;
//import dao.interfaces.DeptDAO;
//import dao.interfaces.SalgradeDAO;
//import model.Dept;
//
//import java.sql.SQLException;
//
//public class Main {
//    public static void main(String[] args) {
//
//        try {
//
//            PostgreSQLDAOFactoryImpl postgreSQLDAOFactory = PostgreSQLDAOFactoryImpl.getInstance();
//            DeptDAO deptDAO = postgreSQLDAOFactory.getDeptDAO();
//            SalgradeDAO salgradeDAO = postgreSQLDAOFactory.getSalgradeDAO();
//            salgradeDAO.create(123,45,67);
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}

import dao.PostgreSQLImpl.PostgreSQLDAOFactoryImpl;
import dao.PostgreSQLImpl.PostgreSQLDeptDAOimpl;
import dao.PostgreSQLImpl.PostgreSQLEmpDAOImpl;
import dao.PostgreSQLImpl.PostgreSQLSalgradeDAOImpl;
import dao.interfaces.DAOFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        primaryStage.setTitle("JDBC Lab");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
