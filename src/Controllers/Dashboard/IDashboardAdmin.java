package Controllers.Dashboard;

import javax.swing.*;

public interface IDashboardAdmin {
    public void loadTableData(JTable tblToDo, JTable tblInProgress, JTable tblCompleted);

    public void openTeamWindow();

    public void openAssignTicketWindow(int adminTicketIndex);

    public void deleteToDoTicket(JTable tblAdminToDo);

    public void viewTicket(JTable tblTicket, int option);

    public void logout();

    void markAsDone(String selectedRow);

    void assignTicket(String valueOf);
}
