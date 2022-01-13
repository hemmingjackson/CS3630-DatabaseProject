package Models;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Tickets.Main.connection;

public class TicketLocation {

    private String idTicketLocation;
    private String name;
    private String room;
    private String building;

    public TicketLocation() {this("NULL","NULL","NULL","NULL");}

    public TicketLocation(String idTicketLocation, String name, String room, String building)
    {
        this.idTicketLocation = idTicketLocation;
        this.name = name;
        this.room = room;
        this.building = building;
    }

    public String getIdTicketLocation() {
        return idTicketLocation;
    }

    public void setIdTicketLocation(String idTicketLocation) {
        this.idTicketLocation = idTicketLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public static ArrayList<TicketLocation> getTicketLocations() {
        ArrayList<TicketLocation> ticketLocations = new ArrayList<>();
        try {
            ResultSet rs = connection.execute("SELECT * FROM ticketlocation", new String[] {}, true);
            while (rs.next()) {
                TicketLocation ticketLocation = new TicketLocation();
                ticketLocation.setIdTicketLocation(rs.getString("idTicketLocation"));
                ticketLocation.setBuilding(rs.getString("building"));
                ticketLocation.setName(rs.getString("name"));
                ticketLocation.setRoom(rs.getString("room"));
                ticketLocations.add(ticketLocation);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database connection error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return ticketLocations;
    }
}
