package Controllers.Team;

import javax.swing.*;

public interface ITeams {
    public void loadTeams(JTable teamsTable);

    public void edit(JTable teamsTable);

    public void delete(JTable teamsTable);

    public void assign(JComboBox teamsTable, JComboBox employee);

    public void add(String name);

}
