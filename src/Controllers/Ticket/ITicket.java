package Controllers.Ticket;

import javax.swing.*;

public interface ITicket {
    public void cancel();
    public void saveCreationByCustomer(JComboBox cbxLocation, JComboBox cbxType, JTextArea txtIssue);
    public void loadInformationForCreation(JComboBox cbxLocation, JComboBox cbxType);
    public void loadInformationForAssignment(JComboBox assignedTeam);
    public void goBack();
    public void loadTicketInfo(JTable tblTicket, JTextArea textAreaDescription);
    public void loadCustomerInfo(JTable tblCustomer);
    public void openUIChangePriority();
}
