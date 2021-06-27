package EMS;
//const teamSchema = new mongoose.Schema({
//        managerID: {type: String, required: true, unique:true, sparse: true, index:true, message:"you need to specify a manager"},
//        employees: {type:[String], required: false, default: null},
//        rating:{type: Number, min: 0, max: 10, message:"the number should be between 0 and 10"},
//        tasks: {type:[taskSchema], required: false, default: null},[raiseRequest]
//        vacationRequests: {type: [vacationSchema], required:false, default: null},
//        raiseRequests: {type: }
//        });

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class Team {
    private String managerID;
    private String[] employeesID = {};
    private ArrayList<Task> tasks;
    private ArrayList<VacationRequest> vacationRequests;
    private ArrayList<RaiseRequest> raiseRequests;
    private String teamID;

    /*public Team(String managerID, Employee[] employees) {
        this.managerID = managerID;
        this.employees = employees;
    }*///not sufficient


    public Team() {
    }

    public Team(String managerID, String[] employeesID, ArrayList<Task> tasks, ArrayList<VacationRequest> vacationRequests, ArrayList<RaiseRequest> raiseRequests, String teamID) {
        this.managerID = managerID;
        this.employeesID = employeesID;
        this.tasks = tasks;
        this.vacationRequests = vacationRequests;
        this.raiseRequests = raiseRequests;
        this.teamID = teamID;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public String[] getEmployees() {
        return employeesID;
    }

    public void setEmployees(String[] employees) {
        this.employeesID = employees;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<VacationRequest> getVacationRequests() {
        return vacationRequests;
    }

    public void setVacationRequests(ArrayList<VacationRequest> vacationRequests) {
        this.vacationRequests = vacationRequests;
    }

    public ArrayList<RaiseRequest> getRaiseRequests() {
        return raiseRequests;
    }

    public void setRaiseRequests(ArrayList<RaiseRequest> raiseRequests) {
        this.raiseRequests = raiseRequests;
    }

    public static void addTeam(Team t) throws IOException, InterruptedException {
        APILINK.addTeam(t);
    }
    public static void deleteTeam(Team t) throws IOException, InterruptedException {
        APILINK.deleteTeam(t);
    }
    public static void getTeams(String id,Team t) throws InterruptedException, ParseException, IOException {
        APILINK.selectTeams("managerID", id,t);
    }
    public static void updateTeam(Team t) throws IOException, InterruptedException {
        APILINK.updateSpecficOfTeam(t);
    }

}
