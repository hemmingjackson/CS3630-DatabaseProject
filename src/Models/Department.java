package Models;

public class Department {

    private String idDepartment;
    private String name;

    public Department() { this("0","NULL"); }

    public Department(String idDepartment, String name)
    {
        this.idDepartment = idDepartment;
        this.name = name;
    }

    public String getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(String idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
