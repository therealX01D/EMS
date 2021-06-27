/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EMS;

/**
 *
 * @author moroz
 */
/**
 *
 * @author moroz
 */
import org.json.*;
import org.json.JSONArray;
//import src.userClasses;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class APILINK {
    private static final String SRVR = "https://shrouded-peak-45915.herokuapp.com/" ;//The link of the Backend
    //static HttpClient client = HttpClient.newHttpClient();
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
    private static ArrayList<String> setter=new ArrayList<String>();
    private static ArrayList<Object> Val=new ArrayList<Object>();
    //public static void main(String[] args) throws IOException, MalformedURLException, InterruptedException, ParseException{

//    JSONObject jo = new JSONObject("{ \"abc\" : \"def\" }");
    //  System.out.println(jo.toString());
    //int n = 10000;
    //POST();
    //getEmployee("testytest2211");
    //Employee e= new Employee("testfinal1","male",1,"he do nothing","hrheuf@yahoo.com","fullTime",new Date(),"egyptian",10000,"testX","hr");

    //ArrayList<Task> vr= new ArrayList<Task>();
    // ArrayList<Event> ea =new ArrayList<Event>();
    //ea.add(new Event("dyg",new Date(),"hduighgd",new String[]{"he","it","them"},500,new Date(),"011221"));
    //vr.add(new Task("do work",new Date (1000000) , "hcdigsgcdbc",new Date (1000500), "00014"));
    //VacationRequest[] v= {new VacationRequest(new Date(100000),new Date(200000),"00014","pending")};
    //e.setVacations(v);
    //e.setTasks(vr.toArray(new Task[0]));
    //e.setEvents(ea.toArray(new Event[0]));
    //addEmployee(e);
    //vr.add(new Task("do work",new Date (10000) , "hcdigsg",new Date (100500), "00015"));
    //ea.add(new Event("added",new Date(),"hduighgd",new String[]{"he","it","them"},500,new Date(),"011221"));
    //e.setTasks(vr.toArray(new Task[0]));
    //e.setEvents(ea.toArray(new Event[0]));
    //addEvents(e,false);
    //pByCondition("employee-db","username",setter.toArray(new String[0]), e.getUsername(), Val.toArray(new Object[0]));

    //     addTasks(e,false);
//            upByCondition("employee-db","username",setter.toArray(new String[0]), e.getUsername(), Val.toArray(new Object[0]));
    //updateAllOfEmployee(e);
    //upByCondition("employee-db","username","salary","testytest2211",500);
    //updateSpecficOfEmployee();
//updateAllOfEmployee();
//        for (Employee eo : getEmployees().toArray(new Employee[0])){
//            System.out.println(eo.getTasks());
//        }
    //addEmployee();
    //getEmployee("testytest2211");
    // getEmployees();
    //}
    //all will change after the creation of classes
    public static ArrayList<Employee> getEmployees() throws IOException, InterruptedException, ParseException{
        JSONArray eag =GET("employee-db");
        ArrayList<Employee> ea=new ArrayList<Employee>();
        JSONObject jo =new JSONObject();
        int size=eag.length();
        int rank ;
        Date date;
        String gender;
        String employement;
        System.out.println("size of the json object is "+size);

        for(int i = 0; i < size;i++){
            jo=((JSONObject)eag.getJSONObject(i));
            rank = (jo.getString("rank")).equals("") ? 0 : Integer.parseInt(jo.getString("rank"));
            gender =((jo.get("gender")).toString()).equals("")?"other":((jo.get("gender")).toString());
            employement =((jo.getString("typeOfEmployement")).equals("")||jo.getString("typeOfEmployement").equals("manager"))?"fullTime":(jo.getString("typeOfEmployement"));
            date= (jo.getString("birthday")).equals("")? null:new SimpleDateFormat("dd-MM-yyyy").parse(jo.getString("birthday"));
            try{
                System.out.println(jo.getString("gender"));
                Employee eo=new Employee(jo.getString("name")
                        ,gender
                        ,rank
                        ,jo.getString("jobDescription")
                        ,jo.getString("email")
                        ,employement
                        ,date
                        ,jo.getString("nationality")
                        ,jo.getNumber("salary").intValue()
                        ,jo.getString("username")
                        ,jo.getString("position"));
                eo.setId(jo.getString("_id"));
                eo.setPhone(jo.getString("phone"));
                eo.setAddress(jo.getString("address"));
                eo.setRating(jo.getNumber("rating").floatValue());
                ArrayList<Task> ta =(taskSchemetoArr(jo.getJSONArray("tasks")));
                ArrayList<Event> eva=(eventSchemetoArr(jo.getJSONArray("events")));
                ArrayList<VacationRequest> va =(vacationSchemetoArr(jo.getJSONArray("vacations")));
                ArrayList<RaiseRequest> rra=(RaiseReqSchemetoArr(jo.getJSONArray("raiseRequests")));
                if (jo.getJSONArray("tasks").length()!=0) eo.setTasks(ta);
                if (jo.getJSONArray("events").length()!=0) eo.setEvents(eva);
                if (jo.getJSONArray("vacations").length()!=0) eo.setVacations(va);
                if (jo.getJSONArray("raiseRequests").length()!=0) eo.setRaiseRequest(rra);
                eo.setPosition(jo.getString("position"));
                eo.setManagerID(jo.getString("managerID"));

                eo.setAllowedVacationDays(jo.getNumber("allowedVacationDays").intValue());
                ea.add(eo);
            }
            catch(JSONException JE){
                System.out.println("the error is "+JE);
            }
        }
        printEmployee(eag);
        return ea;
    }
    public static void getEmployee(Employee eo) throws IOException, InterruptedException, ParseException{
        JSONObject jo =GET("employee-db/"+eo.getUsername()).getJSONObject(0);
        eo.setName(jo.getString("name"));
        JSONArray ja=jo.getJSONArray("tasks");
        Gender gendstr= Gender.valueOf(jo.getString("gender"));
        Employement empstr= Employement.valueOf(jo.getString("typeOfEmployement"));
        Date D=new SimpleDateFormat("dd-MM-yyyy").parse(jo.getString("birthday"));
        eo.setGender(gendstr);
        eo.setId(jo.getString("_id"));
        eo.setAddress(jo.getString("address"));
        eo.setPhone(jo.getString("phone"));
        eo.setEmail(jo.getString("email"));
        eo.setRank(Integer.parseInt(jo.getString("rank")));
        eo.setJobDescription(jo.getString("jobDescription"));
        eo.setTypeOfEmployement(empstr);
        eo.setBirthdate(D);
        eo.setNationality(jo.getString("nationality"));
        eo.setTotalCompletedTasks(jo.getNumber("totalCompletedTasks").intValue());
        eo.setSalary(jo.getNumber("salary").intValue());
        eo.setExtraCompensations(jo.getNumber("extraCompensations").doubleValue());
        eo.setRating(jo.getNumber("rating").floatValue());
        ArrayList<Task> ta =(taskSchemetoArr(jo.getJSONArray("tasks")));
        ArrayList<Event> ea=(eventSchemetoArr(jo.getJSONArray("events")));
        ArrayList<VacationRequest> va =(vacationSchemetoArr(jo.getJSONArray("vacations")));
        ArrayList<RaiseRequest> rra=(RaiseReqSchemetoArr(jo.getJSONArray("raiseRequests")));
        eo.setRaiseRequest(rra);
        eo.setTasks(ta);
        eo.setPosition(jo.getString("position"));
        eo.setManagerID(jo.getString("managerID"));
        eo.setEvents(ea);
        eo.setVacations(va);
        eo.setAllowedVacationDays(jo.getNumber("allowedVacationDays").intValue());

//        raiseRequests: {type: raiseRequest}
//    });
        //GET("employee-db/?username="+usrName);
    }
    public static void deleteEmployee(Employee eo) throws IOException, InterruptedException{
        DEL("employee-db/"+eo.getUsername());
    }
    public static void addEmployee(Employee e) throws IOException, InterruptedException{
        Map<Object, Object> data = new HashMap<>();
        JSONObject tasks =new JSONObject();
        data.put("username", e.getUsername());///*filler data*/
        data.put("name", e.getName());//
        data.put("rating",(float)(e.getRating()));//
        data.put("address", e.getAddress());//
        data.put("gender",e.getGender().toString());//
        data.put("phone",e.getPhone());//
        data.put("email", e.getEmail());//
        data.put("jobDescription",e.getJobDescription());//
        data.put("birthday",e.getBirthdate());//
        data.put("nationality",e.getNationality());//
        data.put("totalCompletedTasks",e.getTotalCompletedTasks());//no. of completed tasks//
        data.put("salary", e.getSalary());//
        data.put("extraCompensations",((int)(e.getExtraCompensations())));//
        //data.put("vacations",vacationReqScheme(e.getVacations()));
        //data.put("tasks",(new JSONArray()));//json array of tasks
        //data.put("events",(new JSONArray()));//json array of events
        data.put("position", e.getPosition());//
        data.put("managerID",e.getManagerID());
        data.put("password", e.getPass());
        data.put("rank", e.getRank());//
        data.put("allowedVacationDays",e.getAllowedVacationDays());
        data.put("typeOfEmployement",e.getTypeOfEmployement());//
        // data.put("raiseRequests",new JSONObject());
        POST("employee-db",data);
        addTasks(e,true);
        addEvents(e,true);
        addVacations(e,true);
        addRaiseRequests(e,true);
        System.out.println(setter);
        upByCondition("employee-db","username",setter, e.getUsername(), Val);
        setter.clear();
        Val.clear();
    }

    /*not tested yet*/public static ArrayList<Employee> selectEmployees(String cond,String value) throws IOException, InterruptedException, ParseException{
        ArrayList<Employee> ea=new ArrayList<Employee>();
        JSONArray eag=GET("employee-db?"+cond+"="+value);
        JSONObject jo =new JSONObject();
        int size=eag.length();
        int rank ;
        Date date;
        String gender;
        String employement;
        System.out.println("size of the json object is "+size);

        for(int i = 0; i < size;i++){
            jo=((JSONObject)eag.getJSONObject(i));
            rank = (jo.getString("rank")).equals("") ? 0 : Integer.parseInt(jo.getString("rank"));
            gender =((jo.get("gender")).toString()).equals("")?"other":((jo.get("gender")).toString());
            employement =((jo.getString("typeOfEmployement")).equals("")||jo.getString("typeOfEmployement").equals("manager"))?"fullTime":(jo.getString("typeOfEmployement"));
            date= (jo.getString("birthday")).equals("")? null:new SimpleDateFormat("dd-MM-yyyy").parse(jo.getString("birthday"));
            Employee e=null;
            try{
                System.out.println(jo.getString("gender"));
                e=new Employee(jo.getString("name"),gender,rank/*,jo.getString("pass")*/
                        ,jo.getString("jobDescription"),jo.getString("email"),employement,date
                        ,jo.getString("nationality"),jo.getNumber("salary").intValue(),jo.getString("username"),jo.getString("position"));
                e.setPhone(jo.getString("phone"));
                e.setId(jo.getString("_id"));
                e.setTotalCompletedTasks(jo.getNumber("totalCompletedTasks").intValue());
                e.setAddress(jo.getString("address"));
                e.setExtraCompensations(jo.getNumber("extraCompensations").intValue());
                e.setRating(jo.getNumber("rating").floatValue());
                e.setManagerID(jo.getString("managerID"));
                e.setAllowedVacationDays(jo.getNumber("allowedVacationDays").intValue());
                ArrayList<Task> ta =(taskSchemetoArr(jo.getJSONArray("tasks")));
                ArrayList<Event> eva=(eventSchemetoArr(jo.getJSONArray("events")));
                ArrayList<VacationRequest> va =(vacationSchemetoArr(jo.getJSONArray("vacations")));
                ArrayList<RaiseRequest> rra=(RaiseReqSchemetoArr(jo.getJSONArray("raiseRequests")));
                if (jo.getJSONArray("tasks").length()!=0) e.setTasks(ta);
                if (jo.getJSONArray("events").length()!=0) e.setEvents(eva);
                if (jo.getJSONArray("vacations").length()!=0) e.setVacations(va);
                if (jo.getJSONArray("raiseRequests").length()!=0) e.setRaiseRequest(rra);
            }
            catch(JSONException JE){
                System.out.println("the error is "+JE);

            }
            ea.add(e);
        }
        printEmployee(eag);
        return ea;
    }
//private static JSONArray eventScheme(Event[] ea){
//      JSONObject jo =new JSONObject();
//      JSONArray ja =new JSONArray();
//      if (ea == null) {
//        return new JSONArray();
//        }
//      else{
//        for(Event eo:ea){
//        jo.append("name",eo.getName());
//        jo.append("issuingDate",eo.getIssuingDate());
//        jo.append("eventDate",eo.getEventDate());
//        jo.append("description",eo.getDescription());
//        jo.append("organizers",eo.getOrganizers());
//        jo.append("peopleEnrolled",eo.getPeopleEnrolled());
//        jo.append("endRegisteration", eo.getEndRegisteration());
//        ja.put(jo);
//        }
//      }
//      return ja;
//  }

    /* private static JSONArray taskScheme(Task[] ta){
         JSONArray ja =new JSONArray();

        for(Task to:ta){
            System.out.println(to.getName());
            JSONObject jo=new JSONObject();
            jo.accumulate("name",to.getName().toString());
            jo.accumulate("issuingDate",(to.getIssuingDate()).toString());
            jo.accumulate("description",(to.getDescription()).toString());
            jo.accumulate("deadline",(to.getDeadline()).toString());
            jo.accumulate("employeeID",(to.getEmployeeID()).toString());
            ja.put(jo);
        }
        return ja;
    }
      private static JSONArray vacationReqScheme(VacationRequest[] vra){
        JSONObject jo =new JSONObject();
        JSONArray ja =new JSONArray();
        if(vra == null){
                jo.accumulate("startDate","");
                jo.accumulate("endDate","");
                jo.accumulate("employeeID","");
                jo.accumulate("status","");
                ja.put(jo);
        }
        else{
            for(VacationRequest vro:vra){
                jo.accumulate("startDate",vro.getStartDate());
                jo.accumulate("endDate",vro.getEndDate());
                jo.accumulate("employeeID",vro.getEmployeeID());
                jo.accumulate("status",vro.getStatus().toString());
                ja.put(jo);
              }
        }
        System.out.println(ja);
         return ja;
    }*/
    //   private static JSONArray RaiseReqScheme(RaiseRequest[] rra){
//      JSONObject jo =new JSONObject();
//
//            JSONArray ja =new JSONArray();
//       for(RaiseRequest rro:rra){
//                 jo.append("employeeID",rro.getEmp());
//                 jo.append("status",rro.getStatus());
//              ja.put(jo);
//      }
//      return ja;
//    }
    //flush on adding a new task array or changed task array to much it changes all of task array to risky to be true
    private static void addTasks(Employee e,boolean flush) throws IOException, InterruptedException{
        ArrayList<Task> ta=e.getTasks();
        if(e.getTasks()!=null && !flush){
            for(int i = 0; i<ta.size();i++){
                setter.add("[$push][tasks][name]");
                Val.add(ta.get(i).getName());
                setter.add("[$push][tasks][issuingDate]");
                Val.add(ta.get(i).getIssuingDate());
                setter.add("[$push][tasks][description]");
                Val.add(ta.get(i).getDescription());
                setter.add("[$push][tasks][deadline]");
                Val.add(ta.get(i).getDeadline());
            }

        }
        else if(e.getTasks()!=null && flush){

            for(int i = 0; i<ta.size();i++){
                setter.add("tasks["+i+"][name]");
                Val.add(ta.get(i).getName());
                setter.add("tasks["+i+"][issuingDate]");
                Val.add(ta.get(i).getIssuingDate());
                setter.add("tasks["+i+"][description]");
                Val.add(ta.get(i).getDescription());
                setter.add("tasks["+i+"][deadline]");
                Val.add(ta.get(i).getDeadline());
            }
        }
    }
    private static void addTasksTeam(Team to,boolean flush) throws IOException, InterruptedException{
        ArrayList<Task> ta=to.getTasks();
        if(to.getTasks()!=null && !flush){
            for(int i = 0; i<ta.size();i++){
                setter.add("[$push][tasks][name]");
                Val.add(ta.get(i).getName());
                setter.add("[$push][tasks][issuingDate]");
                Val.add(ta.get(i).getIssuingDate());
                setter.add("[$push][tasks][description]");
                Val.add(ta.get(i).getDescription());
                setter.add("[$push][tasks][deadline]");
                Val.add(ta.get(i).getDeadline());
            }

        }
        else if(to.getTasks()!=null && flush){

            for(int i = 0; i<ta.size();i++){
                setter.add("[tasks]["+i+"][name]");
                Val.add(ta.get(i).getName());
                setter.add("[tasks]["+i+"][issuingDate]");
                Val.add(ta.get(i).getIssuingDate());
                setter.add("[tasks]["+i+"][description]");
                Val.add(ta.get(i).getDescription());
                setter.add("[tasks]["+i+"][deadline]");
                Val.add(ta.get(i).getDeadline());
            }
        }
    }
    private static void addEvents(Employee e,boolean flush) throws IOException, InterruptedException{
        ArrayList<Event> ta= e.getEvents();
        if(e.getEvents()!=null && !flush){
            for(int i = 0; i<ta.size();i++){
                setter.add("[$push][events][name]");
                Val.add(ta.get(i).getName());
                setter.add("[$push][events][description]");
                Val.add(ta.get(i).getDescription());
                setter.add("[$push][events][eventDate]");
                Val.add(ta.get(i).getEventDate());
                for(int j = 0; j<ta.get(i).getOrganizers().length;j++){
                    setter.add("[$push][events][organizers]"+"["+j+"]");
                    Val.add((ta.get(i).getOrganizers())[j]);
                }
                setter.add("[$push][events][peopleEnrolled]");
                Val.add(ta.get(i).getPeopleEnrolled());
                setter.add("[$push][events][endRegistration]");
                Val.add(ta.get(i).getEndRegisteration());
            }
        }

        else if(e.getEvents()!=null && flush){
            for(int i = 0; i<ta.size();i++){
                setter.add("[events]["+i+"][name]");
                Val.add(ta.get(i).getName());
                setter.add("[events]["+i+"][description]");
                Val.add(ta.get(i).getDescription());
                setter.add("[events]["+i+"][eventDate]");
                Val.add(ta.get(i).getEventDate());
                for(int j = 0; j<ta.get(i).getOrganizers().length;j++){
                    setter.add("[events]["+i+"][organizers]"+"["+j+"]");
                    Val.add((ta.get(i).getOrganizers())[j]);
                }
                setter.add("[events]["+i+"][peopleEnrolled]");
                Val.add(ta.get(i).getPeopleEnrolled());
                setter.add("[events]["+i+"][endRegistration]");
                Val.add(ta.get(i).getEndRegisteration());
            }

        }
    }
    private static void addRaiseRequests(Employee e,boolean flush) throws IOException, InterruptedException{
        ArrayList<RaiseRequest> ta=e.getRaiseRequest();
        if(e.getRaiseRequest()!=null && !flush){
            for(int i = 0; i<ta.size();i++){
                setter.add("[$push][raiserequest][employeeID]");
                Val.add(ta.get(i).getEmp());
                setter.add("[$push][raiserequest][status]");
                Val.add(ta.get(i).getStatus());
            }
        }
        else if(e.getRaiseRequest()!=null && flush){

            for(int i = 0; i<ta.size();i++){
                setter.add("[raiserequest]["+i+"][employeeID]");
                Val.add(ta.get(i).getEmp());
                setter.add("[raiserequest]["+i+"][status]");
                Val.add(ta.get(i).getStatus());
            }
        }
    }
    private static void addRaiseRequestsTeam(Team e,boolean flush) throws IOException, InterruptedException{
        ArrayList<RaiseRequest> ta=e.getRaiseRequests();
        if(e.getRaiseRequests()!=null && !flush){
            for(int i = 0; i<ta.size();i++){
                setter.add("[$push][raiserequest][employeeID]");
                Val.add(ta.get(i).getEmp());
                setter.add("[$push][raiserequest][status]");
                Val.add(ta.get(i).getStatus());
            }
        }
        else if(e.getRaiseRequests()!=null && flush){

            for(int i = 0; i<ta.size();i++){
                setter.add("[raiserequest]["+i+"][employeeID]");
                Val.add(ta.get(i).getEmp());
                setter.add("[raiserequest]["+i+"][status]");
                Val.add(ta.get(i).getStatus());
            }
        }
    }
    private static void addVacations(Employee e,boolean flush) throws IOException, InterruptedException{
        ArrayList<VacationRequest> ta=e.getVacations();
        if(e.getVacations()!=null && flush){

            for(int i = 0; i<ta.size();i++){
                setter.add("[vacations]["+i+"][startDate]");
                Val.add(ta.get(i).getStartDate());
                setter.add("[vacations]["+i+"][endDate]");
                Val.add(ta.get(i).getEndDate());
                setter.add("[vacations]["+i+"][employeeID]");
                Val.add(ta.get(i).getEmployeeID());
                setter.add("[vacations]["+i+"][status]");
                Val.add(ta.get(i).getStatus());
            }
        }
        else if(e.getVacations()!=null && !flush){

            for(int i = 0; i<ta.size();i++){
                setter.add("[$push][vacations][startDate]");
                Val.add(ta.get(i).getStartDate());
                setter.add("[$push][vacations][endDate]");
                Val.add(ta.get(i).getEndDate());
                setter.add("[$push][vacations][employeeID]");
                Val.add(ta.get(i).getEmployeeID());
                setter.add("[$push][vacations][status]");
                Val.add(ta.get(i).getStatus());
            }
        }
    }
    private static void addVacationsTeam(Team e,boolean flush) throws IOException, InterruptedException{
        ArrayList<VacationRequest> ta=e.getVacationRequests();
        if(e.getVacationRequests()!=null && flush){

            for(int i = 0; i<ta.size();i++){
                setter.add("[vacations]["+i+"][startDate]");
                Val.add(ta.get(i).getStartDate());
                setter.add("[vacations]["+i+"][endDate]");
                Val.add(ta.get(i).getEndDate());
                setter.add("[vacations]["+i+"][employeeID]");
                Val.add(ta.get(i).getEmployeeID());
                setter.add("[vacations]["+i+"][status]");
                Val.add(ta.get(i).getStatus());
            }
        }
        else if(e.getVacationRequests()!=null && !flush){

            for(int i = 0; i<ta.size();i++){
                setter.add("[$push][vacations][startDate]");
                Val.add(ta.get(i).getStartDate());
                setter.add("[$push][vacations][endDate]");
                Val.add(ta.get(i).getEndDate());
                setter.add("[$push][vacations][employeeID]");
                Val.add(ta.get(i).getEmployeeID());
                setter.add("[$push][vacations][status]");
                Val.add(ta.get(i).getStatus());
            }
        }
    }

    private static ArrayList<RaiseRequest> RaiseReqSchemetoArr(JSONArray ja){
        JSONObject jo;
        int size =ja.length();
        ArrayList<RaiseRequest> rra = new ArrayList<RaiseRequest>();
        for (int i = 0; i < size; i++) {
            jo=ja.getJSONObject(i);
            rra.add(new RaiseRequest(jo.getString("employeeID"),jo.getString("status")));
        }
        return rra;
    }
    private static ArrayList<VacationRequest> vacationSchemetoArr(JSONArray ja) throws ParseException{
        int size =ja.length();
        JSONObject jo;
        ArrayList<VacationRequest> va = new ArrayList<VacationRequest>();
        for (int i = 0; i < size; i++) {
            jo=ja.getJSONObject(i);
            va.add(new VacationRequest(new SimpleDateFormat("dd-MM-yyyy").parse(jo.getString("startDate"))
                    ,new SimpleDateFormat("dd-MM-yyyy").parse(jo.getString("endDate"))
                    ,jo.getString("employeeID")));
        }
        return va;
    }
    private static ArrayList<Task> taskSchemetoArr(JSONArray ja) throws ParseException{
        int size =ja.length();
        JSONObject jo = null;
        ArrayList<Task> ta = new ArrayList<Task>();
        Date s = null;

        for (int i = 0; i < size; i++) {
            jo=ja.getJSONObject(i);
            try{
                s = (new SimpleDateFormat("dd-MM-yyyy")).parse(jo.getString("deadline"));
            }
            catch(ParseException e){
                s=new SimpleDateFormat("dd-MM-yyyy").parse("22-11-2021");
            }
            ta.add(new Task(jo.getString("name")
                    ,jo.getString("description")
                    ,s
            ));
        }
        return ta;
    }
    private static ArrayList<Event> eventSchemetoArr(JSONArray ja) throws ParseException{
        int size =ja.length();
        JSONObject jo;
        ArrayList<Event> ea = new ArrayList<Event>();
        for (int i = 0; i < size; i++) {
            jo=ja.getJSONObject(i);
            JSONArray organizer = jo.getJSONArray("organizers");
            String[] organizerstr = new String[organizer.length()];
            for (int j = 0; j < organizer.length(); i++) {
                organizerstr[j]= (String)(organizer.get(j));
            }
            ea.add(new Event(jo.getString("name"),
                    new SimpleDateFormat("dd/MM/yyyy").parse(jo.getString("issuingDate"))
                    , jo.getNumber("peopleEnrolled").intValue()
                    , jo.getString("description")
                    , organizerstr
                    , new SimpleDateFormat("dd/MM/yyyy").parse(jo.getString("endRegistration"))
                    , jo.getString("_id")));
        }
        return ea;
    }

    public static void updateAllOfEmployee(Employee eo) throws IOException, InterruptedException{
        Map<Object, Object> data = new HashMap<>();
        data.put("username", eo.getUsername());/*filler data*/
        data.put("name", eo.getName());
        data.put("address", eo.getAddress());
        data.put("gender",eo.getGender());
        data.put("phone",eo.getPhone());
        data.put("email", eo.getEmail());
        data.put("jobDescription",eo.getJobDescription());
        data.put("birthday",eo.getBirthdate());
        data.put("nationality",eo.getNationality());
        data.put("totalCompletedTasks",eo.getTotalCompletedTasks());//no. of completed tasks
        data.put("salary", eo.getSalary());
        data.put("extraCompensations",eo.getExtraCompensations());
//          data.put("vacations",vacationReqScheme(eo.getVacations()));
//          System.out.println(taskScheme(eo.getTasks()));
//          data.put("tasks",taskScheme(eo.getTasks()));//json array of tasks
        //data.put("events",eventScheme(eo.getEvents()));//json array of events
        data.put("position", eo.getPosition());
        data.put("managerID", eo.getManagerID());
        data.put("password",eo.getPass());
        data.put("rank", eo.getRank());
         data.put("rating", eo.getRating());
        //data.put("allowedVacationDays",eo.getAllowedVacationDays());
        data.put("typeOfEmployement",eo.getTypeOfEmployement());
        ArrayList <Task> ta = eo.getTasks();
        for(int i = 0; i<ta.size();i++){
            data.put("tasks["+i+"][name]", ta.get(i).getName());
            data.put("tasks["+i+"][issuingDate]", ta.get(i).getIssuingDate());
            data.put("tasks["+i+"][description]", ta.get(i).getDescription());
            data.put("tasks["+i+"][deadline]",ta.get(i).getDeadline());
        }
        //data.put("raiseRequests",RaiseReqScheme(eo.getRaiseRequest()));
        PUT("employee-db/"+eo.getUsername(),data);
        //addTasks(eo,true);
        addEvents(eo,true);
        addVacations(eo,true);
        addRaiseRequests(eo,true);
        upByCondition("employee-db","username",setter, eo.getUsername(), Val);
        setter.clear();
        Val.clear();
    }
    /*public static void updateSpecficOfEmployee(Employee eo,String cond,String[] Setters,Object[] Values) throws IOException, InterruptedException{
          upByCondition("employee-db",cond,new String[]{genSetters("Tasks",0,null)},"testytest2211",new Integer[]{8000});
    }*/
  /*public static void addTask(Employee eo,String[] Setters,Object[] Values) throws IOException, InterruptedException{
        upByCondition("employee-db","username",new String[]{genSetters("Tasks",1,null)},eo.getUsername(),new Integer[]{8000});
  } */
    public static Team getTeams(Team t) throws IOException, InterruptedException, ParseException{
        JSONArray tag =GET("team-db");
        ArrayList<Team> Ta=new ArrayList<Team>();
        JSONObject jo =new JSONObject();

        int size=tag.length();
        System.out.println("size of the json object is "+size);
        ArrayList<Task> ta =(taskSchemetoArr(jo.getJSONArray("tasks")));
        ArrayList<Event> ea=(eventSchemetoArr(jo.getJSONArray("events")));
        ArrayList<VacationRequest> va =(vacationSchemetoArr(jo.getJSONArray("vacations")));
        ArrayList<RaiseRequest> rra=(RaiseReqSchemetoArr(jo.getJSONArray("raiseRequests")));
        t.setRaiseRequests(rra);
        t.setTasks(ta);
        t.setVacationRequests(va);
        t.setRaiseRequests(rra);
        printEmployee(tag);
        return t;
    }
    /*not tested yet*/public static void selectTeams(String cond,Object value,Team t) throws IOException, InterruptedException, ParseException{
        JSONObject jo= GET("team-db?"+cond+"="+value).getJSONObject(0);
        t.setManagerID(jo.getString("managerID"));
        JSONArray employees = jo.getJSONArray("employees");
        String[] EIDA = new String[employees.length()];
        for (int j = 0; j < employees.length(); j++) {
            EIDA[j]= (String)(employees.get(j));
        }
        t.setEmployees(EIDA);
        ArrayList<Task> ta =(taskSchemetoArr(jo.getJSONArray("tasks")));
        ArrayList<VacationRequest> va =(vacationSchemetoArr(jo.getJSONArray("vacationRequests")));
        ArrayList<RaiseRequest> rra=(RaiseReqSchemetoArr(jo.getJSONArray("raiseRequests")));
        t.setRaiseRequests(rra);
        t.setTasks(ta);
        t.setVacationRequests(va);
        t.setRaiseRequests(rra);
    }


    //     managerID: {type: String, required: true, unique:true, sparse: true, index:true, message:"you need to specify a manager"},
//        employees: {type:[String], required: false, default: null},
//        rating:{type: Number, min: 0, max: 10, message:"the number should be between 0 and 10"},
//        tasks: {type:[taskSchema], required: false, default: null},[raiseRequest]
//        vacationRequests: {type: [vacationSchema], required:false, default: null},
//        raiseRequests: {type: }
    public static void addTeam(Team to) throws IOException, InterruptedException{
        Map<Object, Object> data = new HashMap<>();
        data.put("managerID", to.getManagerID());
        data.put("employees",to.getEmployees());
        // IDs OF USERS
        POST("team-db",data);
        addTasksTeam(to,true);
        addVacationsTeam(to,true);
        addRaiseRequestsTeam(to, true);
        upByCondition("team-db","username",setter, to.getManagerID(), Val);
        setter.clear();
        Val.clear();
    }
    //delete teams
    public static void deleteTeam(Team to) throws IOException, InterruptedException{
        DEL("/team-db?managerID="+to.getManagerID());
    }
    public static void updateSpecficOfTeam(Team to) throws IOException, InterruptedException{
        setter.add(genSetters("managerID",0,null));
        Val.add(to.getManagerID());
        for(int j = 0; j<to.getEmployees().length;j++){
            setter.add("[employees]"+"["+j+"]");
            Val.add((to.getEmployees())[j]);
        }


       //setter.add(genSetters("rating",0,null));
        addTasksTeam(to,true);
        addVacationsTeam(to,true);
        addRaiseRequestsTeam(to, true);
        upByCondition("team-db","managerID",setter,to.getManagerID(),Val);
        setter.clear();
        Val.clear();
    }
    public static String login(String username, String pass) throws IOException, InterruptedException{
        Map<Object, Object> data = new HashMap<>();
        data.put("username", username);
        data.put("password", pass);
        return POST("login",data);
    }
    public static ArrayList<Event> getEvent() throws IOException, InterruptedException, ParseException{

        JSONArray tag =GET("event-db");;
        ArrayList<Event> ea=new ArrayList<Event>();
        JSONObject jo =new JSONObject();

        int size=tag.length();
        System.out.println("size of the json object is "+size);
        for(int i = 0; i <size;i++){
            jo=tag.getJSONObject(i);
            JSONArray Organizers = jo.getJSONArray("organizers");
            String[] EIDA = new String[Organizers.length()];
            for (int j = 0; j < Organizers.length(); j++) {
                EIDA[j]= (String)(Organizers.get(j));
            }
            try{
                Event eo=(new Event(jo.getString("name")
                        ,new SimpleDateFormat("dd-MM-yyyy").parse(jo.getString("eventDate"))
                        , jo.getNumber("peopleEnrolled").intValue()
                        , jo.getString("description")
                        , EIDA
                        , new SimpleDateFormat("dd-MM-YYYY").parse(jo.getString("endRegistration"))
                        , (jo.get("_id")).toString()));
                // System.out.println(jo.getString("gender"));
                ea.add(eo);
            }
            catch(JSONException JE){
                System.out.println("the error is "+JE);
            }
        }
        printEmployee(tag);
        return ea;
    }
    public static void addEvent(Event eo) throws IOException, InterruptedException{
        Map <Object, Object>event=new HashMap<>();
        event.put("name",eo.getName());
        event.put("eventDate", eo.getEventDate());
        event.put("description",eo.getDescription());
        for (int i = 0; i < eo.getOrganizers().length; i++) {
            event.put("organizers["+i+"]",(eo.getOrganizers())[i]);
        }
        event.put("peopleEnrolled",eo.getPeopleEnrolled());
        event.put("endRegisteration",eo.getEndRegisteration());
        POST("event-db",event);

    }
    public static ArrayList<Event> selectEvents(String cond,Object value) throws IOException, InterruptedException, ParseException{
        JSONArray tag =GET("event-db?"+cond+"="+value);
        ArrayList<Event> ea=new ArrayList<Event>();
        JSONObject jo =new JSONObject();

        int size=tag.length();
        System.out.println("size of the json object is "+size);

        for(int i = 0; i < size;i++){

            jo=((JSONObject)tag.getJSONObject(i));
            JSONArray Organizers = jo.getJSONArray("organizers");
            String[] EIDA = new String[Organizers.length()];
            for (int j = 0; j < Organizers.length(); i++) {
                EIDA[j]= (String)(Organizers.get(j));
            }
            try{
                Event eo=(new Event(jo.getString("name")
                        ,new SimpleDateFormat("dd-MM-YYYY").parse(jo.getString("eventDate"))
                        , jo.getNumber("peopleEnrolled").intValue()
                        , jo.getString("description")
                        , EIDA
                        , new SimpleDateFormat("dd-MM-YYYY").parse(jo.getString("endRegistration"))
                        , (jo.get("_id")).toString()));
                // System.out.println(jo.getString("gender"));
                ea.add(eo);
            }
            catch(JSONException JE){
                System.out.println("the error is "+JE);
            }
        }
        printEmployee(tag);
        return ea;
    }
    public static void updateSpecficOfEvent(Event eo) throws IOException, InterruptedException{

        setter.add(genSetters("name",0,null));
        Val.add(eo.getName());
        setter.add(genSetters("eventDate",0,null));
        Val.add(eo.getEventDate());
        setter.add(genSetters("description",0,null));
        Val.add(eo.getDescription());
        for(int j = 0; j<eo.getOrganizers().length;j++){
            setter.add("[Organizers]"+"["+j+"]");
            Val.add((eo.getOrganizers())[j]);
        }
        setter.add(genSetters("peopleEnrolled",0,null));
        Val.add(eo.getPeopleEnrolled());
        setter.add(genSetters("endRegistration",0,null));
        Val.add(eo.getEndRegisteration());
        upByCondition("event-db","name",setter,eo.getName(),Val);
        setter.clear();
        Val.clear();
    }
    public static void deleteEvent(Event eo) throws IOException, InterruptedException{
        DEL("/team-db?name="+eo.getName());
    }
    //delete events
    //update condition, set
    private static void upByCondition(String Path,String condition, ArrayList<String> setter, Object val1,ArrayList<Object> val2) throws IOException, InterruptedException{
        Map<Object, Object> data = new HashMap<>();
        data.put("condition["+condition+"]", val1);
       // for(int i = 0 ; i<setter.length-1;i++){
       //     data.put("set"+setter[i], val2[i]);
       // }
         for(int i = 0 ; i<setter.size();i++){
             data.put("set"+setter.get(i), val2.get(i));
    }
        HttpRequest request = (HttpRequest) HttpRequest.newBuilder()
                .uri(URI.create(SRVR+Path))
                .method("PATCH",convertMapToRequest(data) /*HttpRequest.BodyPublishers.ofString("condition[username]=testytest2211&set[salary]=102000")*/)
                .setHeader("User-Agent", "Desktop App")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        HttpResponse response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        // print response body
        System.out.println(response.body());

    }
    private static void PUT(String path,Map<Object,Object> data ) throws IOException, InterruptedException{



        HttpRequest request = HttpRequest.newBuilder()
                .PUT(convertMapToRequest(data))
                .uri(URI.create(SRVR+path))
                .setHeader("User-Agent", "Desktop App") // add request header
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        System.out.println(response.statusCode());

        // print response body
        System.out.println(response.body());
    }
    private static String POST(String Path,Map<Object,Object> data) throws MalformedURLException, IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .POST(convertMapToRequest(data))
                .uri(URI.create(SRVR+Path))
                .setHeader("User-Agent", "Desktop App") // add request header
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        System.out.println(response.statusCode());

        // print response body
        return response.body();
        //
//
//      try {
//
//        URL url = new URL(SRVR+path);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setDoOutput(true);
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("Content-Type", "text/plain");
//        //conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
//        //input is a string in json format
//        OutputStream os = conn.getOutputStream();
//        os.write(input.getBytes());
//        os.flush();
//
//        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED && conn.getResponseCode() != 200) {
//            throw new RuntimeException("Failed : HTTP error code : "
//                + conn.getResponseCode());
//        }
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(
//                (conn.getInputStream())));
//
//        String output;
//        System.out.println("Output from Server .... \n");
//        while ((output = br.readLine()) != null) {
//            System.out.println(output);
//        }
//
//        conn.disconnect();
//
//      } catch (MalformedURLException e) {
//
//        e.printStackTrace();
//
//      } catch (IOException e) {
//
//        e.printStackTrace();
//
//     }


    }
    private static void DEL(String Path) throws IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create(SRVR+Path))
                .setHeader("User-Agent", "Desktop App")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        System.out.println("HTTP response is "+response.statusCode());

        // print response body
        System.out.println(response.body());


    }
    private static JSONArray GET(String path) throws IOException, InterruptedException{
        System.out.println(path);
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(SRVR+path))
                .setHeader("User-Agent", "Desktop App")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(HttpResponse.BodyHandlers.ofString());
        // print status code
        System.out.println("HTTP response is "+response.statusCode());

        // print response body

        System.out.println(response.body());
        JSONArray jArray = (JSONArray) new JSONTokener(response.body()).nextValue();

//array of outputs on returning multiple fields of json
        //JSONObject je = jArray.getJSONObject(0);
        return jArray;


        // System.out.println(je.toString());

//     try {
//        URL url = new URL(SRVR+path);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Accept", "application/json");
//
//        if (conn.getResponseCode() != 200) {
//            throw new RuntimeException("Failed : HTTP error code : "
//                    + conn.getResponseCode());
//        }
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(
//            (conn.getInputStream())));
//
//        String output=new String();
//        String resp =new String();
//        JSONObject res;
//        System.out.println("Output from Server .... \n");
//        while ((output = br.readLine()) != null) {
//            System.out.println(output);
//            resp = resp + output;
//            //System.out.println(res);
//         }
//          JSONArray jArray = (JSONArray) new JSONTokener(resp).nextValue();//array of outputs on returning multiple fields of json
//          JSONObject je=jArray.getJSONObject(0);//
//          System.out.println(je.toString());
//
//        conn.disconnect();
//
//      } catch (MalformedURLException e) {
//
//        e.printStackTrace();
//
//      } catch (IOException e) {
//
//        e.printStackTrace();
//
//      }
    }
    private static HttpRequest.BodyPublisher convertMapToRequest(Map<Object, Object> data) {

        var builder = new StringBuilder();
        try{
            data.entrySet().stream().map(entry -> {
                if (builder.length() > 0) {
                    builder.append("&");
                }
                builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
                return entry;
            }).forEachOrdered(entry -> {
                builder.append("=");
                if(entry.getValue() == null){
                    System.out.println("Entry "+entry+" is null");
                }
                else{
                    builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
                }
            });
            System.out.println(builder.toString());

        }
        catch(NullPointerException NPE){
            System.out.println("there is a null in the header of a send"+NPE.getMessage());
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }
    private static void printEmployee(JSONArray ja){
        for (int i = 0; i < ja.length(); i++) {
            JSONObject jo=(JSONObject)ja.get(i);
            try{
                System.out.println(jo.toString()+",");
                System.out.println("the rating is "+jo.getFloat("rating")+",");
                System.out.println("the name is "+jo.getString("name")+",");
                System.out.println("the username is "+jo.getString("username")+",");
                System.out.println("the extraCompensations is "+jo.getInt("extraCompensations")+",");
                System.out.println("the position is "+jo.getString("position")+",");
                System.out.println("the managerID is "+jo.getString("managerID")+",");
            }
            catch(JSONException E){
                System.out.println("error is "+E.getMessage());
            }
        }
    }
    private static String genSetters(String input , int type , Object param){
        String res = new String();
        switch (type) {
            case 0:
                res="["+input+"]";
                break;
            case 1:
                res="[$push]["+input+"]";
                break;
            case 2:
                res="[$pull]["+input+"]";
                break;
            default:
                break;
        }
        return res;
    }
    //private Employee jsonToEmployee
}

