package Models;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Tickets.Main.connection;

public class TicketType {

    private String idTicketType;
    private String name;

    public TicketType() {this("NULL", "NULL");}

    public TicketType(String idTicketType, String name)
    {
        this.idTicketType = idTicketType;
        this.name = name;
    }

    public String getIdTicketType() {
        return idTicketType;
    }

    public void setIdTicketType(String idTicketType) {
        this.idTicketType = idTicketType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<TicketType> getTicketTypes() {
        ArrayList<TicketType> ticketTypes = new ArrayList<>();
        try {
            ResultSet rs = connection.execute("SELECT * FROM tickettype", new String[] {}, true);
            while (rs.next()) {
                TicketType ticketType = new TicketType();
                ticketType.setIdTicketType(rs.getString("idTicketType"));
                ticketType.setName(rs.getString("name"));
                ticketTypes.add(ticketType);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database connection error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return ticketTypes;
    }
}
