package Models;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static Tickets.Main.connection;

public class Team {

    private String idTeam;
    private String name;
    private String creationDate;

    public Team() {this("NULL","NULL","NULL");}

    public Team(String idTeam, String name, String creationDate)
    {
        this.idTeam = idTeam;
        this.name = name;
        this.creationDate = creationDate;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public static ArrayList<Team> getTeams() {
        ArrayList<Team> teams = new ArrayList<>();

        try {
            ResultSet rs = connection.execute("SELECT * FROM team", new String[] {}, true);
            while (rs.next()) {
                Team team = new Team();
                team.setIdTeam(rs.getString("idTeam"));
                team.setName(rs.getString("name"));
                team.setCreationDate(rs.getString("creationDate"));
                teams.add(team);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database connection error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return teams;
    }
}
