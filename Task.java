package EMS;
//const taskSchema = new mongoose.Schema({
//        name: {type: String},
//        issuingDate: {type: String},
//        description:{type:String},
//        deadline :{type: String},
//        completionDate:{type: String},
////  employeeID: {type:String, required: true}
//        });

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class Task {
    private String name;
    private Date deadline;
    private Date issuingDate;
    private String description;

    public Task(String name, String description, Date deadline) throws ParseException {
        this.name = name;
        Date d = Date.from(Instant.now());
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String date = df.format(d);
        this.issuingDate = df.parse(date);
        this.description = description;
        this.deadline = deadline;
    }

    public Task() throws ParseException {
        Date d = Date.from(Instant.now());
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String date = df.format(d);
        this.issuingDate = df.parse(date);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssuingDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(this.issuingDate);
    }


    public String getCompletionDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(this.deadline);
    }

    public void setCompletionDate(Date completionDate) {
        this.deadline = completionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(this.deadline);
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

}
