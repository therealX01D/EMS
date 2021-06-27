package EMS;


public class RaiseRequest {
    private String employeeID;
    private Status status;

    public RaiseRequest(String emp, String status) {
        this.employeeID=emp;
        this.status = Status.valueOf(status);
    }

    public RaiseRequest(String emp) {
        this.employeeID = emp;
        this.status = Status.pending;
    }
    public RaiseRequest(){}

    public String getEmp() {
        return employeeID;
    }

    public void setEmp(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getStatus() {
        return status.name();
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }
}
