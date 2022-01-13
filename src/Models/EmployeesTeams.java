package Models;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Tickets.Main.connection;

public class EmployeesTeams {

    String idEmployee;
    String idTeam;
    String enrollDate;

    public EmployeesTeams()
    {
        this("0","NULL","NULL");
    }

    public EmployeesTeams(String idEmployee, String idTeam, String enrollDate)
    {
        this.idEmployee = idEmployee;
        this.idTeam = idTeam;
        this.enrollDate = enrollDate;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public String getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(String enrollDate) {
        this.enrollDate = enrollDate;
    }

    public boolean saveInDatabase() {
        boolean inserted = false;
        try {
            connection.execute("INSERT INTO employeesteams VALUES (?, ?, DEFAULT)",
                    new String[] {
                            idEmployee,idTeam
                    }, false);
            inserted = true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Database connection error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return inserted;
    }
}
