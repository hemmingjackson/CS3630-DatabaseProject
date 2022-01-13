package Models;

public class TimeTracking {

    private String idTimeTracking;
    private String idTicket;
    private String idTeam;
    private String idEmployee;
    private String StartTime;
    private String EndTime;
    private String UpdatedAt;

    public TimeTracking() {this("NULL","NULL","NULL","NULL","NULL","NULL","NULL");}

    public TimeTracking(String idTimeTracking, String idTicket, String idTeam, String idEmployee, String startTime, String endTime, String updatedAt)
    {
        this.idTimeTracking = idTimeTracking;
        this.idTicket = idTicket;
        this.idTeam = idTeam;
        this.idEmployee = idEmployee;
        this.StartTime = startTime;
        this.EndTime = endTime;
        this.UpdatedAt = updatedAt;
    }

    public String getIdTimeTracking() {
        return idTimeTracking;
    }

    public void setIdTimeTracking(String idTimeTracking) {
        this.idTimeTracking = idTimeTracking;
    }

    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        idEmployee = idEmployee;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        UpdatedAt = updatedAt;
    }
}
