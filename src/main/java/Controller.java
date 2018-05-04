
import dao.PostgreSQLImpl.PostgreSQLDAOFactoryImpl;
import dao.PostgreSQLImpl.PostgreSQLDeptDAOimpl;
import dao.PostgreSQLImpl.PostgreSQLEmpDAOImpl;
import dao.PostgreSQLImpl.PostgreSQLSalgradeDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Emp;
import java.sql.*;
import java.util.List;


public class Controller {



    @FXML
    private TextField inpEmpNo;
    @FXML
    private TextField inpEName;
    @FXML
    private TextField inpMgr;
    @FXML
    private TextField inpSal;
    @FXML
    private TextField inpDeptNo;
    @FXML
    private TextField inpJob;
    @FXML
    private TextField inpComm;
    @FXML
    private TextArea mainTextArea;


    PostgreSQLDAOFactoryImpl postgreSQLDAOFactory = PostgreSQLDAOFactoryImpl.getInstance();
    PostgreSQLDeptDAOimpl deptDAO = postgreSQLDAOFactory.getDeptDAO();
    PostgreSQLEmpDAOImpl empDAO = postgreSQLDAOFactory.getEmpDAO();
    PostgreSQLSalgradeDAOImpl salgradeDAO = postgreSQLDAOFactory.getSalgradeDAO();
    private String empsText = "";

    public void initialize() {

        try {
            syncTable();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void syncTable() {
        List<Emp> emps = empDAO.getAll();
        StringBuffer sb = new StringBuffer();
        for (Emp emp : emps) {
            sb.append(emp.toString());
            sb.append(", " + deptDAO.read(emp.getDeptno()));
            sb.append(", grade=" + salgradeDAO.getGrade(emp.getSal()));
            sb.append("\n");
        }
        empsText = sb.toString();
        mainTextArea.setText(empsText);
    }


    @FXML
    private void refreshButtonEvent(ActionEvent event) {
        syncTable();
    }


    @FXML
    private void searchButtonEvent(ActionEvent event) {
        Emp emp = empDAO.read(Integer.parseInt(inpEmpNo.getText()));
        StringBuffer sb = new StringBuffer();

        sb.append(emp.toString());
        sb.append(", " + deptDAO.read(emp.getDeptno()));
        sb.append(", grade=" + salgradeDAO.getGrade(emp.getSal()));
        sb.append("\n");
        String empText = sb.toString();
        mainTextArea.setText(empText);
    }

    @FXML
    private void deleteButtonEvent(ActionEvent event) {
        empDAO.delete(Integer.parseInt(inpEmpNo.getText()));
        syncTable();
    }

    @FXML
    private void addButtonEvent(ActionEvent event) {
        Date date = new Date(System.currentTimeMillis());
        empDAO.create(Integer.parseInt(inpEmpNo.getText()),
                inpEName.getText(),
                inpJob.getText(),
                Integer.parseInt(inpMgr.getText()),
                date,
                Integer.parseInt(inpSal.getText()),
                Integer.parseInt(inpComm.getText()),
                Integer.parseInt(inpDeptNo.getText()));
        syncTable();
    }
}