package EMS;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//    const employeeSchema = new mongoose.Schema({
//        name: {type: String, required: true, message:"you need to specify a name"},
//        address: {type: String},
//        gender: {type: String},
//        phone: {type: String},
//        email: {type: String, required: true, default: null},
//        rank: {type:String, required: true, default:null},
//        jobDescription: {type: String},
//        typeOfEmployement: {type: String, required: true, default:null},
//        birthday: {type: String},
//        nationality:{type: String},
//        totalCompletedTasks:{type: Number},
//        salary:{type: Number, required:true, message:"you need to specify a salary"},
//        extraCompensations : {type: Number, default: 0},
//        rating:{type: Number, min: 0, max: 10, default: 0, message:"the number should be between 0 and 10"},
//        tasks: {type:[taskSchema], required:false, default: null},
//        username: {type: String, required: true, unique:true, message:"you need to specify a username"},
//        position: String,
//        managerID: {type: String, required: true, sparse: true, index:true},
//        events: {type: [eventSchema], required: false, default: null},
//        vacations: {type:[vacationSchema], required: false, default:null},
//        allowedVacationDays: {type: Number},
//        raiseRequests: {type: raiseRequest}
//    });
public class Employee {
    private String name;
    private String address;
    private String id;
    private String managerID;
    private Gender gender;
    private int rank;
    private String pass;
    private String phone;
    private String jobDescription;
    private String email;
    private Employement typeOfEmployement;
    private Date birthdate;
    private String nationality;
    private int totalCompletedTasks = 0;
    private int salary=0;
    private double extraCompensations=0;
    private float rating;
    private ArrayList<Task> tasks = new ArrayList<>() ;
    private String username;
    private String position;
    private ArrayList <Event> events=new ArrayList<>();
    private ArrayList<VacationRequest> VacationRequest = new ArrayList<>();
    private ArrayList<RaiseRequest> raiseRequest = new ArrayList<>();
    private int allowedVacationDays;


    public Employee(String name, String address, String id, String managerID, Gender gender, int rank, String pass, String phone, String jobDescription, String email, Employement typeOfEmployement, Date birthdate, String nationality, int totalCompletedTasks, int salary, double extraCompensations, float rating, ArrayList<Task> tasks, String username, String position, ArrayList<Event> events, ArrayList<EMS.VacationRequest> vacationRequest, ArrayList<RaiseRequest> raiseRequest, int allowedVacationDays) {
        this.name = name;
        this.address = address;
        this.id = id;
        this.managerID = managerID;
        this.gender = gender;
        this.rank = rank;
        this.pass = pass;
        this.phone = phone;
        this.jobDescription = jobDescription;
        this.email = email;
        this.typeOfEmployement = typeOfEmployement;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.totalCompletedTasks = totalCompletedTasks;
        this.salary = salary;
        this.extraCompensations = extraCompensations;
        this.rating = rating;
        this.tasks = tasks;
        this.username = username;
        this.position = position;
        this.events = events;
        VacationRequest = vacationRequest;
        this.raiseRequest = raiseRequest;
        this.allowedVacationDays = allowedVacationDays;
    }

    public Employee(String name, String address, String id, String managerID, Gender gender, int rank, String phone, String jobDescription, String email, Employement typeOfEmployement, Date birthdate, String nationality, int totalCompletedTasks, int salary, double extraCompensations, float rating, ArrayList<Task> tasks, String username, String position, ArrayList<Event> events, ArrayList<EMS.VacationRequest> vacationRequest, ArrayList<RaiseRequest> raiseRequest, int allowedVacationDays) {
        this.name = name;
        this.address = address;
        this.id = id;
        this.managerID = managerID;
        this.gender = gender;
        this.rank = rank;
        this.phone = phone;
        this.jobDescription = jobDescription;
        this.email = email;
        this.typeOfEmployement = typeOfEmployement;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.totalCompletedTasks = totalCompletedTasks;
        this.salary = salary;
        this.extraCompensations = extraCompensations;
        this.rating = rating;
        this.tasks = tasks;
        this.username = username;
        this.position = position;
        this.events = events;
        this.VacationRequest = vacationRequest;
        this.raiseRequest = raiseRequest;
        this.allowedVacationDays = allowedVacationDays;
    }

    public Employee(String name, String gender, int rank
            /*String pass*/, String jobDescription, String email,
                    String typeOfEmployement, Date birthdate, String nationality,
                    int salary, String username, String position) {
        this.name = name;
        this.gender = Gender.valueOf(gender);
        this.rank = rank;
        // this.pass = pass;
        this.jobDescription = jobDescription;
        this.email = email;
        this.typeOfEmployement = Employement.valueOf(typeOfEmployement);
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.salary = salary;
        this.username = username;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employement getTypeOfEmployement() {
        return typeOfEmployement;
    }

    public void setTypeOfEmployement(Employement typeOfEmployement) {
        this.typeOfEmployement = typeOfEmployement;
    }

    public String getBirthdate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(this.birthdate);
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getTotalCompletedTasks() {
        return totalCompletedTasks;
    }

    public void setTotalCompletedTasks(int totalCompletedTasks) {
        this.totalCompletedTasks = totalCompletedTasks;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public double getExtraCompensations() {
        return extraCompensations;
    }

    public void setExtraCompensations(double extraCompensations) {
        this.extraCompensations = extraCompensations;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public ArrayList<VacationRequest> getVacations() {
        return VacationRequest;
    }

    public void setVacations(ArrayList<VacationRequest> vacations) {
        this.VacationRequest = vacations;
    }

    public ArrayList<RaiseRequest> getRaiseRequest() {
        return this.raiseRequest;
    }

    public void setRaiseRequest(ArrayList<RaiseRequest> raiseRequest) {
        this.raiseRequest = raiseRequest;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
    public void setAllowedVacationDays(int allowedVacationDays) {
        this.allowedVacationDays = allowedVacationDays;
    }

    public int getAllowedVacationDays() {
        return allowedVacationDays;
    }
    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public static ArrayList<Employee> getAllEmployees() throws InterruptedException, ParseException, IOException {
        return APILINK.getEmployees();
    }
    public static void getEmployee(Employee e) throws InterruptedException, ParseException, IOException {
        APILINK.getEmployee(e);
    }
    public static void deleteEmployee(Employee e) throws IOException, InterruptedException {
        APILINK.deleteEmployee(e);
    }
    public static void addEmployee(Employee e) throws IOException, InterruptedException {
        APILINK.addEmployee(e);
    }
    public static void updateEmployee(Employee e) throws IOException, InterruptedException {
        APILINK.updateAllOfEmployee(e);
    }
    public static Employee getEmployeeByID(String ID) throws InterruptedException, ParseException, IOException {
        Employee eo= APILINK.selectEmployees("_id",ID).get(0);
        return eo;
    }
    public static String login(String username, String password) throws IOException, InterruptedException {
        return APILINK.login(username, password);
    }

    public Employee() {
    }
}
