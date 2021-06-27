package EMS;

import java.text.SimpleDateFormat;
import java.util.Date;
//const vacationSchema = new mongoose.Schema({
//        startDate:{type: String},
//        endDate: {type: String},
//        employeeID: {type:String},
//        status:{type:String}
//        });
public class VacationRequest {
    private Date startDate;
    private Date endDate;
    private String employeeID;
    private Status status;

    public VacationRequest(Date startDate, Date endDate, String employeeID, String status) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.employeeID = employeeID;
        this.status = Status.valueOf(status);
    }

    public VacationRequest(Date startDate, Date endDate, String employeeID) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.employeeID = employeeID;
        this.status = status.pending;
    }

    public String getStartDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(this.startDate);
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(endDate);
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getStatus() {
        return status.name();
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
