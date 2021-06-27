package EMS;

import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.text.SimpleDateFormat;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.collections.ObservableList;


public class Main extends Application {


    Scene login_scene, main_scene, task_manager, add_task, vacation;
    TextField user_name,add_task_name,task_description,issuing_date,completion_date,add_startDATE,add_endDATE,nameHere,hereAddress,hereEmail,herePhone,hereReqSalary,performanceMetric;
    PasswordField pass;
    TableView <Event> eventsTable;
    TableView <Task> taskTable,tasktable2;
    Stage Window;

    Scene mainScreen;

    TableView<Employee> table_Scene1;
    Scene sceneAddEmployee;

    Scene sceneAddEmployee_Scene2;

    Scene myProfile_Scene3;

    Scene sceneEmployeeInfo_Scene4;

    Scene requestVacation_Scene5;

    Scene taskManager_Scene6;
    TableView<Task> table_Scene6;
    TextField nameInput_Scene6, dateInput_Scene6, deadlineInput_Scene6, projectmanagerInput_Scene6;

    Scene sceneAddTask_Scene7;


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws InterruptedException, ParseException, IOException {
        int width = (int) Screen.getPrimary().getBounds().getWidth();
        int height = (int) Screen.getPrimary().getBounds().getHeight();

        //        Login--------------------------------------------
        Window = primaryStage;
        Window.setTitle("Employee Software");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));

        Controller.TextFieldGenerator("Username");
        Label username_label = new Label("Username");
        username_label.setFont(Font.font("TimesRoman", 14));


        user_name = new TextField();
        user_name.setText("Username");


        Button login_button = new Button("Login");



        Label pass_label = new Label("Password");
        pass_label.setFont(Font.font("TimesRoman", 14));

        pass = new PasswordField();
        pass.setText("Password");








//        if(loginButtonClicked(user_name,pass)){
//            emp.setUsername(user_name.getText());
//            Employee.getEmployee(emp);
//            Window.setScene(main_scene);
//        }

        grid.add(pass, 1, 1);
        grid.add(pass_label, 0, 1);
        grid.add(user_name, 1, 0);
        grid.add(username_label, 0, 0);
        grid.add(login_button, 1, 2);

        Scene login_scene = new Scene(grid, width, height);
        login_scene.getStylesheets().add(getClass().getResource("newstyle.css").toExternalForm());



//        MAIN--------------------------------------------


        Employee emp = new Employee();
        Team t =new Team();


        login_button.setOnAction(nashar ->{
            try {
                if(loginButtonClicked(user_name,pass)){
                    emp.setUsername(user_name.getText());
                    Employee.getEmployee(emp);

                    System.out.println(emp.getManagerID());

                    GridPane grid_1 = new GridPane();
                    grid_1.setAlignment(Pos.CENTER);
                    grid_1.setVgap(10);
                    grid_1.setHgap(10);
                    grid_1.setPadding(new Insets(10));



                    //Event Name column
                    TableColumn<Event, String> eventNameColumn = new TableColumn<>("name");
                    eventNameColumn.setMinWidth(200);
                    eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

                    //Event description column
                    TableColumn<Event, String> eventDescription = new TableColumn<>("description");
                    eventDescription.setMinWidth(200);
                    eventDescription.setCellValueFactory(new PropertyValueFactory<Event,String>("description"));

                    //Actual date
                    TableColumn<Event, String> eventDate = new TableColumn<>("eventDate");
                    eventDate.setMinWidth(100);
                    eventDate.setCellValueFactory(new PropertyValueFactory<Event,String>("eventDate"));


                    eventsTable = new TableView<>();
                    eventsTable.setItems(getEvents());
                    eventsTable.getColumns().addAll(eventNameColumn, eventDescription, eventDate);


                    Label events_label = new Label("Event");
                    events_label.setFont(Font.font("verdana", FontWeight.BLACK.BOLD, 20));


                    Button task_manager_button = new Button("Go to task manager");


                    Button vacation_button = new Button("Request vacation");


                    Button myPerformance = new Button("My Performance");


                    Button myProfile = new Button("My Profile");


                    grid_1.add( eventsTable,0,1);
                    grid_1.add(events_label, 0, 0);
                    grid_1.add(task_manager_button,1,1);
                    grid_1.add(myPerformance,0,2);
                    grid_1.add(myProfile,0,3);
                    grid_1.add(vacation_button,0,4);
                    Scene main_scene = new Scene(grid_1, width, height);
                    main_scene.getStylesheets().add(getClass().getResource("newstyle.css").toExternalForm());
                    Window.setScene(main_scene);


                    //        Task Manager--------------------------------------------


                    GridPane grid_2 = new GridPane();
                    grid_2.setAlignment(Pos.CENTER);
                    grid_2.setVgap(10);
                    grid_2.setHgap(10);
                    grid_2.setPadding(new Insets(10));


                    //task Name column
                    TableColumn<Task, String> tasksNameColumn = new TableColumn<>("name");
                    tasksNameColumn.setMinWidth(160);
                    tasksNameColumn.setCellValueFactory(new PropertyValueFactory<Task,String>("name"));

                    //task description column
                    TableColumn<Task, String> tasksDescription = new TableColumn<>("description");
                    tasksDescription.setMinWidth(160);
                    tasksDescription.setCellValueFactory(new PropertyValueFactory<Task,String>("description"));

                    //issuing date
                    TableColumn<Task, String> taskIssueDate = new TableColumn<>("issuingDate");
                    taskIssueDate.setMinWidth(80);
                    taskIssueDate.setCellValueFactory(new PropertyValueFactory<Task,String>("issuingDate"));

                    //completion date
                    TableColumn<Task, String> taskCompletionDate = new TableColumn<>("deadline");
                    taskCompletionDate.setMinWidth(80);
                    taskCompletionDate.setCellValueFactory(new PropertyValueFactory<Task,String>("deadline"));


                    taskTable = new TableView<>();
                    taskTable.setItems(getTasks(emp));
                    taskTable.getColumns().addAll(tasksNameColumn, tasksDescription, taskIssueDate,taskCompletionDate);


                    taskTable.setEditable(true);

                    Label Tasks_title = new Label("Task");
                    Tasks_title.setFont(Font.font("verdana", FontWeight.BLACK.BOLD, 20));
                    Tasks_title.setAlignment(Pos.CENTER);

                    Button add_task_button = new Button("Add task");

                    Button edit_task_button = new Button("Edit task");


                    Button TeamTasks = new Button("Team Tasks");

                    Button backTO_main = new Button("Back to main");


                    Button deleteButton = new Button("Delete");
                    deleteButton.setOnAction(ev -> deleteButtonClicked(emp));


                    grid_2.add(Tasks_title, 2, 0);
                    grid_2.add(add_task_button, 3,6 );
                    grid_2.add(edit_task_button, 3, 7);
                    grid_2.add(deleteButton, 3, 8);
                    grid_2.add(taskTable, 2, 5);
                    grid_2.add(backTO_main, 2, 6);
                    grid_2.add(TeamTasks, 3,5);


                    Scene task_manager = new Scene(grid_2, width, height);
                    task_manager.getStylesheets().add(getClass().getResource("newstyle.css").toExternalForm());
//------------------------------Team Tasks-----------------------------
                    GridPane grid_20 = new GridPane();
                    grid_20.setAlignment(Pos.CENTER);
                    grid_20.setVgap(10);
                    grid_20.setHgap(10);
                    grid_20.setPadding(new Insets(10));


                    //task Name column
                    TableColumn<Task, String> teamtasksNameColumn = new TableColumn<>("name");
                    teamtasksNameColumn.setMinWidth(160);
                    teamtasksNameColumn.setCellValueFactory(new PropertyValueFactory<Task,String>("name"));

                    //task description column
                    TableColumn<Task, String> teamtasksDescription = new TableColumn<>("description");
                    teamtasksDescription.setMinWidth(160);
                    teamtasksDescription.setCellValueFactory(new PropertyValueFactory<Task,String>("description"));

                    //issuing date
                    TableColumn<Task, String> teamtaskIssueDate = new TableColumn<>("issuingDate");
                    teamtaskIssueDate.setMinWidth(80);
                    teamtaskIssueDate.setCellValueFactory(new PropertyValueFactory<Task,String>("issuingDate"));

                    //completion date
                    TableColumn<Task, String> teamtaskCompletionDate = new TableColumn<>("deadline");
                    teamtaskCompletionDate.setMinWidth(80);
                    teamtaskCompletionDate.setCellValueFactory(new PropertyValueFactory<Task,String>("deadline"));

                    tasktable2 = new TableView<>();
                    tasktable2.setItems(getTeamTasks(emp));
                    tasktable2.getColumns().addAll(teamtasksNameColumn, teamtasksDescription, teamtaskIssueDate,teamtaskCompletionDate);


                    tasktable2.setEditable(true);

                    Label Team_Events_title = new Label("Team Tasks");
                    Team_Events_title.setFont(Font.font("verdana", FontWeight.BLACK.BOLD, 20));
                    Team_Events_title.setAlignment(Pos.CENTER);


                    Button backTO_main2 = new Button("Back to main");


                    Button markAsCompletedButton = new Button("Mark as Completed");
                    //markAsCompletedButton.setOnAction(ev -> deleteButtonClicked(emp));


                    grid_20.add(Team_Events_title, 2, 0);
                    grid_20.add(markAsCompletedButton, 3, 6);
                    grid_20.add(tasktable2, 2, 5);
                    grid_20.add(backTO_main2, 2, 6);

                    Scene team_task_manager = new Scene(grid_20, width, height);
                    team_task_manager.getStylesheets().add(getClass().getResource("newstyle.css").toExternalForm());




//        Adding tasks------------------------------------
                    GridPane grid_3 = new GridPane();
                    grid_3.setAlignment(Pos.CENTER);
                    grid_3.setVgap(10);
                    grid_3.setHgap(10);
                    grid_3.setPadding(new Insets(10));

                    Label task_name_label = new Label("Task name: ");
                    task_name_label.setFont(Font.font("TimesRoman", 14));

                    add_task_name = new TextField();

                    Label task_description_label = new Label("Task description: ");
                    task_description_label.setFont(Font.font("TimesRoman", 14));

                    task_description = new TextField();

                    Label task_date_label_issue = new Label("Task issue date: ");
                    task_date_label_issue.setFont(Font.font("TimesRoman", 14));

                    Button set_task = new Button("Add task");

                    Button edit_task = new Button("Edit Task");

                    Button cancelTaskAdd = new Button("Cancel");



                    issuing_date = new TextField();
                    issuing_date.setText(Date.from(Instant.now()).toString());


                    Label task_date_label_completion = new Label("Task completion date: ");
                    task_date_label_completion.setFont(Font.font("TimesRoman", 14));

                    completion_date = new TextField();
                    completion_date.setText("dd-MM-yyyy");

                    grid_3.add(task_name_label, 0, 1);
                    grid_3.add(add_task_name, 1, 1);
                    grid_3.add(task_description_label, 0, 2);
                    grid_3.add(task_description, 1, 2);
                    grid_3.add(task_date_label_issue, 0, 3);
                    grid_3.add(issuing_date, 1, 3);
                    grid_3.add(task_date_label_completion, 0, 4);
                    grid_3.add(completion_date, 1, 4);
                    grid_3.add(set_task, 5, 5);
                    grid_3.add(edit_task, 5, 6);
                    grid_3.add(cancelTaskAdd, 0, 5);

                    Scene add_task = new Scene(grid_3, width, height);
                    add_task.getStylesheets().add(getClass().getResource("newstyle.css").toExternalForm());



//        Adding vacation------------------------------------
                    GridPane grid_4 = new GridPane();
                    grid_4.setAlignment(Pos.CENTER);
                    grid_4.setVgap(10);
                    grid_4.setHgap(10);
                    grid_4.setPadding(new Insets(10));

                    Label vacation_title = new Label("Vacation Request");
                    vacation_title.setFont(Font.font("verdana", FontWeight.BLACK.BOLD, 20));

                    Label startDATE_label = new Label("Start Date: ");
                    task_name_label.setFont(Font.font("TimesRoman", 14));

                    add_startDATE = new TextField();
                    add_startDATE.setText("DD-MM-YYYY");

                    Label endDATE_label = new Label("End Date: ");
                    task_name_label.setFont(Font.font("TimesRoman", 14));

                    add_endDATE = new TextField();
                    add_endDATE.setText("DD-MM-YYYY");

                    Button vacation_submit_request = new Button("Submit Request");


                    Button cancelVacationRequest = new Button("Cancel");



                    Stage popUpWindow = new Stage();
                    popUpWindow.setTitle("Done");
                    Label submitted = new Label("Submission successful !");
                    Button button1 = new Button("OK");
                    VBox pop = new VBox(10);
                    pop.getChildren().addAll(submitted, button1);
                    pop.setAlignment(Pos.CENTER);
                    Scene pop1 = new Scene(pop, 300, 300);
                    popUpWindow.setScene(pop1);


                    grid_4.add(vacation_title, 0, 0);
                    grid_4.add(startDATE_label, 0, 1);
                    grid_4.add(add_startDATE, 1, 1);
                    grid_4.add(endDATE_label, 0, 2);
                    grid_4.add(add_endDATE, 1, 2);
                    grid_4.add(vacation_submit_request, 3, 4);
                    grid_4.add(cancelVacationRequest, 0, 4);

                    Scene vacation = new Scene(grid_4, width, height);
                    vacation.getStylesheets().add(getClass().getResource("newstyle.css").toExternalForm());

//        Performance--------------------------------------------

                    GridPane grid_5 = new GridPane();
                    grid_5.setAlignment(Pos.CENTER);
                    grid_5.setVgap(10);
                    grid_5.setHgap(10);
                    grid_5.setPadding(new Insets(10));

                    Label performance_title = new Label("My Performance");
                    performance_title.setFont(Font.font("verdana", FontWeight.BLACK.BOLD, 20));

                    Label performanceMetrics = new Label("Performance Percentage");
                    performanceMetrics.setFont(Font.font("TimesRoman", 14));

                    performanceMetric = new TextField();
                    //performanceMetric.setText(String.valueOf(emp.getRating()));

                    Label totalCompletedtask = new Label("Total Completed Tasks");
                    totalCompletedtask.setFont(Font.font("TimesRoman", 14));

                    TextField totalCompletedtasks = new TextField();
                    //totalCompletedtasks.setText(String.valueOf(emp.getTotalCompletedTasks()));



                    Button backToMain = new Button("Back to Main");





                    grid_5.add(performance_title, 0, 0);
                    grid_5.add(performanceMetrics, 0, 1);
                    grid_5.add(performanceMetric, 1, 1);
                    grid_5.add(backToMain, 0, 5);
                    grid_5.add(totalCompletedtask, 0,2);
                    grid_5.add(totalCompletedtasks, 1,2);
                    Scene performance = new Scene(grid_5, width, height);
                    performance.getStylesheets().add(getClass().getResource("newstyle.css").toExternalForm());

//        Profile--------------------------------------------
                    GridPane grid_6 = new GridPane();
                    grid_6.setAlignment(Pos.CENTER);
                    grid_6.setVgap(10);
                    grid_6.setHgap(10);
                    grid_6.setPadding(new Insets(10));

                    Label personalInfo = new Label("Profile");
                    personalInfo.setFont(Font.font("verdana", FontWeight.BLACK.BOLD, 20));

                    Label Name = new Label("Name");
                    Name.setFont(Font.font("TimesRoman", 14));

                    nameHere = new TextField();
                    nameHere.setText(emp.getName());

                    Label address = new Label("Address");
                    address.setFont(Font.font("TimesRoman", 14));

                    hereAddress = new TextField();
                    hereAddress.setText(emp.getAddress());

                    Label phone = new Label("Phone");
                    phone.setFont(Font.font("TimesRoman", 14));

                    herePhone = new TextField();
                    herePhone.setText(emp.getPhone());

                    Label email = new Label("Email");
                    email.setFont(Font.font("TimesRoman", 14));

                    hereEmail = new TextField();
                    hereEmail.setText(emp.getEmail());

                    Label reqSalaryRaise = new Label("Current Salary: ");
                    reqSalaryRaise.setFont(Font.font("TimesRoman", 14));

                    hereReqSalary = new TextField();
                    hereReqSalary.setText(String.valueOf(emp.getSalary()));

                    Button sendSalaryRaise = new Button("Send Raise Request");


                    Button backToMainFromProfile = new Button("Back to Main");




                    grid_6.add(personalInfo, 0, 0);
                    grid_6.add(Name, 0, 1);
                    grid_6.add(nameHere, 1, 1);
                    grid_6.add(phone, 0, 2);
                    grid_6.add(herePhone, 1, 2);
                    grid_6.add(address, 0, 3);
                    grid_6.add(hereAddress, 1, 3);
                    grid_6.add(email, 0, 4);
                    grid_6.add(hereEmail, 1, 4);
                    grid_6.add(reqSalaryRaise, 0, 5);
                    grid_6.add(hereReqSalary, 1, 5);
                    grid_6.add(sendSalaryRaise, 2, 5);
                    grid_6.add(backToMainFromProfile, 0, 7);

                    Scene profile = new Scene(grid_6, width, height);
                    profile.getStylesheets().add(getClass().getResource("newstyle.css").toExternalForm());


//        --------------------------------------------

                    //----------------------------------------------

                    Window = primaryStage;
                    Window.setTitle("EMS");

                    GridPane mainGrid=new GridPane();
                    mainGrid.setAlignment(Pos.TOP_CENTER);
                    mainGrid.setVgap(10);
                    mainGrid.setHgap(10);
                    mainGrid.setPadding(new Insets(10));

                    Label events_label_Scene0=new Label("Tasks");
                    // Adding new list view element
                    ListView eventsList_Scene0 = new ListView<>();

                    //     Employee management
                    Label employeeManagerLabel_Scene0=new Label("Employee Manager");
                    Button viewAllEmployee_Scene0 = new Button("View All Employees");
                    Button addNewEmployee_Scene0 = new Button("Add New Employee");
                    // Vbox for holding elements
                    VBox employeeVbox_Scene0 = new VBox();
                    employeeVbox_Scene0.setPadding(new Insets(10,10,10,10));
                    employeeVbox_Scene0.setSpacing(10);
                    employeeVbox_Scene0.getChildren().addAll(employeeManagerLabel_Scene0,viewAllEmployee_Scene0, addNewEmployee_Scene0);

                    //     Personal  Management level
                    Label managerLabel_Scene0 = new Label("Management");
                    Button task_manager_button_Scene0=new Button("Go to task manager");
                    // Vbox for holding elements
                    VBox managerBox_Scene0 = new VBox();
                    managerBox_Scene0.setPadding(new Insets(10,10,10,10));
                    managerBox_Scene0.setSpacing(10);
                    managerBox_Scene0.getChildren().addAll(managerLabel_Scene0,task_manager_button_Scene0);

                    //      Personal data
                    Label personalLabel_Scene0 = new Label("Personal");
                    Button vacation_button_Scene0=new Button("Request vacation");
                    Button myProfile_Scene0 = new Button("My Profile");
                    // Vbox for holding elements
                    VBox personalVBox_Scene0 = new VBox();
                    personalVBox_Scene0.setPadding(new Insets(10,10,10,10));
                    personalVBox_Scene0.setSpacing(10);
                    personalVBox_Scene0.getChildren().addAll(personalLabel_Scene0,myProfile_Scene0,vacation_button_Scene0);

                    Button addEvent_Scene0 = new Button("Add Event");
                    Button editEvent_Scene0 = new Button("Edit Event");
                    Button deleteEvent_Scene0 = new Button("Delete Event");
                    // Hbox for holding elements
                    HBox eventHandlerBox_Scene0 = new HBox();
                    eventHandlerBox_Scene0.setPadding(new Insets(10,10,10,10));
                    eventHandlerBox_Scene0.setSpacing(10);
                    //eventHandlerBox_Scene0.getChildren().addAll(addEvent_Scene0,editEvent_Scene0,deleteEvent_Scene0);



                    VBox containerBox_Scene0 = new VBox();
                    containerBox_Scene0.setPadding(new Insets(10,10,10,10));
                    containerBox_Scene0.setSpacing(10);
                    containerBox_Scene0.getChildren().addAll(employeeVbox_Scene0,personalVBox_Scene0,managerBox_Scene0);


                    mainGrid.add(events_label_Scene0,0,0);
                    mainGrid.add(containerBox_Scene0,1,1);
                    mainGrid.add(eventHandlerBox_Scene0,0,2);
                    mainGrid.add(eventsList_Scene0,0,1);
                    mainScreen=new Scene(mainGrid,600,600);
                    mainGrid.setAlignment(Pos.CENTER);
                    mainScreen.getStylesheets().add(getClass().getResource("newstyle.css").toExternalForm());



                    if(emp.getRank() == 1){
                        Window.setScene(main_scene);
                        Team.getTeams(emp.getManagerID(),t);
                    } else if(emp.getRank() == 2){
                        Window.setScene(mainScreen);
                        Team.getTeams(emp.getId(),t);
                    }

                    //Window.show();

/////////////////////////////////////////////////////New Scene1( view all employees)///////////////////////////////////////////////////////////////////////////


                    TextField nameInput, idInput, rankInput;

                    //Name column
                    TableColumn<Employee, String> nameColumn_Scene1 = new TableColumn<>("Name");
                    nameColumn_Scene1.setMinWidth(200);
                    nameColumn_Scene1.setCellValueFactory(new PropertyValueFactory<>("Name"));

                    //Id column
                    TableColumn<Employee, String> idColumn_Scene1 = new TableColumn<>("ID");
                    idColumn_Scene1.setMinWidth(100);
                    idColumn_Scene1.setCellValueFactory(new PropertyValueFactory<>("id"));

                    //Rank column
                    TableColumn<Employee, String> rankColumn_Scene1 = new TableColumn<>("Rank");
                    rankColumn_Scene1.setMinWidth(100);
                    rankColumn_Scene1.setCellValueFactory(new PropertyValueFactory<>("Rank"));


                    //Button
                    Button addButton_Scene1 = new Button("Add");
                    Button deleteButton_Scene1 = new Button("Delete");
                    Button viewButton_Scene1 = new Button("View");
                    Button homescreenButton_Scene1 = new Button("HomeScreen");



                    HBox hBox_Scene1 = new HBox();
                    hBox_Scene1.setPadding(new Insets(10,10,10,10));
                    hBox_Scene1.setSpacing(10);
                    hBox_Scene1.getChildren().addAll( viewButton_Scene1, addButton_Scene1, deleteButton_Scene1);
                    viewButton_Scene1.setTranslateX(200);
                    addButton_Scene1.setTranslateX(200);
                    deleteButton_Scene1.setTranslateX(200);

                    HBox hBox2_Scene1 = new HBox();
                    hBox2_Scene1.setPadding(new Insets(10,10,10,10));
                    hBox2_Scene1.setSpacing(10);
                    hBox2_Scene1.getChildren().addAll( homescreenButton_Scene1);
                    homescreenButton_Scene1.setTranslateX(240);

                    table_Scene1 = new TableView<>();

                    table_Scene1.getColumns().addAll(nameColumn_Scene1, idColumn_Scene1, rankColumn_Scene1);

                    VBox vBox_Scene1 = new VBox();
                    vBox_Scene1.getChildren().addAll(table_Scene1, hBox_Scene1,hBox2_Scene1);
                    vBox_Scene1.setAlignment(Pos.TOP_CENTER);


                    Scene sceneAllEmployees_Scene1 = new Scene(vBox_Scene1,600,500);
                    sceneAllEmployees_Scene1.getStylesheets().add(getClass().getResource("newstyle.css").toExternalForm());

                    addButton_Scene1.setOnAction(e ->  Window.setScene(sceneAddEmployee));
                    deleteButton_Scene1.setOnAction(e -> deleteButtonTeamClicked(t));



///////////////////////////////////////////////New Scene2 (Add new Employee)//////////////////////////////////////////////////////////////////////////////////////////


                    //GridPane with 10px padding around edge
                    GridPane grid_Scene2 = new GridPane();
                    grid_Scene2.setAlignment(Pos.CENTER);
                    grid_Scene2.setPadding(new Insets(10, 10, 10, 10));
                    grid_Scene2.setVgap(8);
                    grid_Scene2.setHgap(10);


                    //Name Label - constrains use (child, column, row)
                    Label nameLabel_Scene2 = new Label("Name:");
                    GridPane.setConstraints(nameLabel_Scene2, 0, 0);
                    //Name Input
                    TextField nameInput_Scene2 = new TextField();
                    nameInput_Scene2.setPromptText("Name");
                    GridPane.setConstraints(nameInput_Scene2, 1, 0);

                    //Gender input
                    ChoiceBox<String> gender_Scene2 = new ChoiceBox<>();
                    gender_Scene2.getItems().addAll("Mr.", "Mrs.");
                    GridPane.setConstraints(gender_Scene2, 2, 0);

                    //Address Label
                    Label addressLabel_Scene2 = new Label("Address:");
                    GridPane.setConstraints(addressLabel_Scene2, 0, 3);
                    //Address Input
                    TextField addressInput_Scene2 = new TextField();
                    addressInput_Scene2.setPromptText("Address");
                    GridPane.setConstraints(addressInput_Scene2, 1, 3);

                    //Phone no. Label
                    Label phoneLabel_Scene2 = new Label("Phone number:");
                    GridPane.setConstraints(phoneLabel_Scene2, 0, 2);
                    //Phone no. Input
                    TextField phoneInput_Scene2 = new TextField();
                    phoneInput_Scene2.setPromptText("Phone number");
                    GridPane.setConstraints(phoneInput_Scene2, 1, 2);

                    //Email Label
                    Label emailLabel_Scene2 = new Label("Email:");
                    GridPane.setConstraints(emailLabel_Scene2, 0, 1);
                    //Email Input
                    TextField emailInput_Scene2 = new TextField();
                    emailInput_Scene2.setPromptText("Email");
                    GridPane.setConstraints(emailInput_Scene2, 1, 1);

                    //Position Label
                    Label positionLabel_Scene2 = new Label("Position:");
                    GridPane.setConstraints(positionLabel_Scene2, 0, 6);
                    //Position Input
                    TextField positionInput_Scene2 = new TextField();
                    positionInput_Scene2.setPromptText("Position");
                    GridPane.setConstraints(positionInput_Scene2, 1, 6);
                    //Type of employment Input
                    TextField typeOfEmploymentInput_Scene2 = new TextField();
                    typeOfEmploymentInput_Scene2.setPromptText("Type of Employment");
                    GridPane.setConstraints(typeOfEmploymentInput_Scene2, 2, 6);

                    //Salary Label
                    Label salaryLabel_Scene2 = new Label("Salary :");
                    GridPane.setConstraints(salaryLabel_Scene2, 0, 7);
                    //Salary Input
                    TextField salaryInput_Scene2 = new TextField();
                    salaryInput_Scene2.setPromptText("Salary");
                    GridPane.setConstraints(salaryInput_Scene2, 1, 7);

                    //Username  Label
                    Label usernameLabel_Scene2 = new Label("User name:");
                    GridPane.setConstraints(usernameLabel_Scene2, 0, 8);
                    //Username Input
                    TextField usernameInput_Scene2 = new TextField();
                    usernameInput_Scene2.setPromptText("username");
                    GridPane.setConstraints(usernameInput_Scene2, 1, 8);

                    //Password Label
                    Label passwordLabel_Scene2 = new Label("Password:");
                    GridPane.setConstraints(passwordLabel_Scene2, 0, 9);
                    //Password Input
                    PasswordField passwordField_Scene2 = new PasswordField();
                    passwordField_Scene2.setPromptText("Your password");
                    GridPane.setConstraints(passwordField_Scene2, 1, 9);

                    //Nationality Label
                    Label nationalityLabel_Scene2 = new Label("Nationality:");
                    GridPane.setConstraints(nationalityLabel_Scene2, 0, 4);
                    //Nationality Input
                    TextField nationalityInput_Scene2 = new TextField();
                    nationalityInput_Scene2.setPromptText("Nationality");
                    GridPane.setConstraints(nationalityInput_Scene2, 1, 4);

                    //Date of birth Label
                    Label dateOfBirthLabel_Scene2 = new Label("Date of Birth:");
                    GridPane.setConstraints(dateOfBirthLabel_Scene2, 0, 5);
                    // create a date picker
                    DatePicker d_Scene2 = new DatePicker();
                    GridPane.setConstraints(d_Scene2, 1, 5);

/* // action event
 EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
     public void handle(ActionEvent e)
     {
         // get the date picker value
         LocalDate i = d_Scene2.getValue();

         // get the selected date
         dateOfBirthLabel.setText("Date :" + i);
     }
 };*/

                    // show week numbers
                    d_Scene2.setShowWeekNumbers(true);

                    // when datePicker is pressed
                    //d_Scene2.setOnAction(event);


                    //Save button
                    Button saveButton_Scene2 = new Button("Save");
                    //GridPane.setConstraints(saveButton, 1, 2);

                    //Cancel button
                    Button cancelButton_Scene2 = new Button("Cancel");
                    //GridPane.setConstraints(cancelButton, 2, 2);

                    HBox hBox_Scene2 = new HBox();
                    hBox_Scene2.setPadding(new Insets(10,10,10,10));
                    hBox_Scene2.setSpacing(10);
                    hBox_Scene2.getChildren().addAll( saveButton_Scene2, cancelButton_Scene2);
                    GridPane.setConstraints(hBox_Scene2, 1, 10);


                    //Add everything to grid
                    grid_Scene2.getChildren().addAll(nameLabel_Scene2, nameInput_Scene2, addressLabel_Scene2, addressInput_Scene2,phoneLabel_Scene2,phoneInput_Scene2,emailLabel_Scene2,
                            emailInput_Scene2, positionInput_Scene2,positionLabel_Scene2,typeOfEmploymentInput_Scene2,salaryInput_Scene2,salaryLabel_Scene2,usernameInput_Scene2,usernameLabel_Scene2,passwordField_Scene2,
                            passwordLabel_Scene2, nationalityLabel_Scene2, nationalityInput_Scene2,dateOfBirthLabel_Scene2,d_Scene2,gender_Scene2,hBox_Scene2);


                    sceneAddEmployee_Scene2 = new Scene(grid_Scene2, 800, 500);
                    sceneAddEmployee_Scene2.getStylesheets().add(getClass().getResource("newstyle.css").toExternalForm());


////////////////////////////////////////////New scene 3 (My Profile)////////////////////////////////////////////////////////////////////////////////

                    //GridPane with 10px padding around edge
                    GridPane grid2020 = new GridPane();
                    grid2020.setAlignment(Pos.CENTER);
                    grid2020.setPadding(new Insets(10, 10, 10, 10));
                    grid2020.setVgap(8);
                    grid2020.setHgap(10);


                    //Name Label - constrains use (child, column, row)
                    Label nameLabel = new Label("Name:");
                    GridPane.setConstraints(nameLabel, 0, 0);
                    //Name Input
                    TextField nameInput_Scene3 = new TextField("Name");  // you will write the function this.getName() in the place of "Name" string
                    nameInput_Scene3.setDisable(true);
                    GridPane.setConstraints(nameInput_Scene3, 1, 0);
                    nameInput_Scene3.setText(emp.getName());

                    //Gender input
                    ChoiceBox<String> gender = new ChoiceBox<>();
                    gender.getItems().addAll("Mr.", "Mrs.");
                    GridPane.setConstraints(gender, 2, 0);
                    if(emp.getGender().equals(Gender.male)) {
                        gender.setValue("Mr.");
                    }
                    else if (emp.getGender().equals(Gender.female)){
                        gender.setValue("Mrs.");
                    }
                    //Address Label
                    Label addressLabel = new Label("Address:");
                    GridPane.setConstraints(addressLabel, 0, 3);
                    //Address Input
                    TextField addressInput = new TextField();
                    addressInput.setPromptText("Address");
                    GridPane.setConstraints(addressInput, 1, 3);
                    addressInput.setText(emp.getAddress());

                    //Phone no. Label
                    Label phoneLabel = new Label("Phone number:");
                    GridPane.setConstraints(phoneLabel, 0, 2);
                    //Phone no. Input
                    TextField phoneInput = new TextField();
                    phoneInput.setPromptText("Phone number");
                    GridPane.setConstraints(phoneInput, 1, 2);
                    phoneInput.setText(emp.getPhone());
                    //Email Label
                    Label emailLabel = new Label("Email:");
                    GridPane.setConstraints(emailLabel, 0, 1);
                    //Email Input
                    TextField emailInput = new TextField();
                    emailInput.setPromptText("Email");
                    GridPane.setConstraints(emailInput, 1, 1);
                    emailInput.setText(emp.getEmail());
                    //Position Label
                    Label positionLabel = new Label("Position:");
                    GridPane.setConstraints(positionLabel, 0, 6);
                    //Position Input
                    TextField positionInput = new TextField();
                    positionInput.setPromptText("Position");
                    GridPane.setConstraints(positionInput, 1, 6);
                    positionInput.setText(emp.getPosition());
                    //Type of employment Input
                    TextField typeOfEmploymentInput = new TextField();
                    typeOfEmploymentInput.setPromptText("Type of Employment");
                    GridPane.setConstraints(typeOfEmploymentInput, 2, 6);
                    typeOfEmploymentInput.setText(emp.getTypeOfEmployement().toString());

                    //Salary Label
                    Label salaryLabel = new Label("Salary :");
                    GridPane.setConstraints(salaryLabel, 0, 7);
                    //Salary Input
                    TextField salaryInput = new TextField();
                    salaryInput.setPromptText("Salary");                                    //  you enter the default value here
                    salaryInput.setDisable(true);
                    GridPane.setConstraints(salaryInput, 1, 7);
                    salaryInput.setText(String.valueOf(emp.getSalary()));
                    //requested salary
                    Button sendRequestButton = new Button("Send Request");
                    GridPane.setConstraints(sendRequestButton, 3, 7);
                    Button cancellRequestButton = new Button("Cancel");
                    GridPane.setConstraints(cancellRequestButton, 4, 7);

                    //Username  Label
                    Label usernameLabel = new Label("User name:");
                    GridPane.setConstraints(usernameLabel, 0, 8);
                    //Username Input
                    TextField usernameInput = new TextField();
                    usernameInput.setPromptText("username");
                    GridPane.setConstraints(usernameInput, 1, 8);
                    usernameInput.setDisable(true);
                    usernameInput.setText(emp.getUsername());

                    //Password Label
                    Label passwordLabel = new Label("Password:");
                    GridPane.setConstraints(passwordLabel, 0, 9);
                    //Password Input
                    PasswordField passwordField = new PasswordField();
                    passwordField.setPromptText("Your password");
                    GridPane.setConstraints(passwordField, 1, 9);
                    passwordField.setDisable(true);
                    passwordField.setText(emp.getPass());

                    //Nationality Label
                    Label nationalityLabel = new Label("Nationality:");
                    GridPane.setConstraints(nationalityLabel, 0, 4);
                    //Nationality Input
                    TextField nationalityInput = new TextField("Nationality");              //you will add the true nationality here
                    nationalityInput.setDisable(true);
                    GridPane.setConstraints(nationalityInput, 1, 4);
                    nationalityInput.setText(emp.getNationality());
                    //Date of birth Label
                    Label dateOfBirthLabel = new Label("Date of Birth:");
                    GridPane.setConstraints(dateOfBirthLabel, 0, 5);
                    TextField dateOfBirth = new TextField("Date of birth");
                    GridPane.setConstraints(dateOfBirth, 1, 5);
                    dateOfBirth.setDisable(true);
                    dateOfBirth.setText(emp.getBirthdate());

                    //total number of completed tasks label
                    Label completedTaskLabel = new Label("Total completed Tasks:");
                    GridPane.setConstraints(completedTaskLabel, 0, 10);
                    TextField completedTasksInput = new TextField();
                    completedTasksInput.setPromptText("eg:50");                                 //  you enter the default value here
                    GridPane.setConstraints(completedTasksInput, 1, 10);
                    completedTasksInput.setDisable(true);
                    completedTasksInput.setText(String.valueOf(emp.getTotalCompletedTasks()));

                    //total number of completed tasks label
                    Label currentTaskLabel = new Label("Current Tasks:");
                    GridPane.setConstraints(currentTaskLabel, 0, 11);
                    //Text field
                    TextField currentTaskInput = new TextField("Project A");                    //  you enter the default value here
                    GridPane.setConstraints(currentTaskInput, 1, 11);
                    currentTaskInput.setDisable(true);

                    //Performance label
                    Label performanceLabel = new Label("Performance:");
                    GridPane.setConstraints(performanceLabel, 0, 12);
                    //Text field
                    TextField performanceInput = new TextField("30%");                    //  you enter the default value here
                    GridPane.setConstraints(performanceInput, 1, 12);
                    performanceInput.setDisable(true);
                    performanceInput.setText(String.valueOf(emp.getRating()));


                    //Cancel button
                    Button cancelButton = new Button("Back");
                    //GridPane.setConstraints(cancelButton, 2, 2);

                    HBox hBox = new HBox();
                    hBox.setPadding(new Insets(10,10,10,10));
                    hBox.setSpacing(10);
                    hBox.getChildren().addAll( cancelButton);
                    GridPane.setConstraints(hBox, 1, 13);


                    //Add everything to grid
                    grid2020.getChildren().addAll(nameLabel, nameInput_Scene3, addressLabel, addressInput,phoneLabel,phoneInput,emailLabel,
                            emailInput, positionInput,positionLabel,salaryInput,salaryLabel,usernameInput,usernameLabel,passwordField,
                            passwordLabel, nationalityLabel, nationalityInput,dateOfBirthLabel,dateOfBirth,gender,hBox,sendRequestButton,cancellRequestButton,
                            completedTaskLabel,completedTasksInput,currentTaskLabel,currentTaskInput,typeOfEmploymentInput,performanceInput,performanceLabel);

                    myProfile_Scene3 = new Scene(grid2020, 1000, 600);
                    myProfile_Scene3.getStylesheets().add(getClass().getResource("newstyle.css").toExternalForm());


////////////////////////////////////////////New scene 4 (View Employee info)////////////////////////////////////////////////////////////////////////////////
//                    ObservableList<Employee> EmployeeSelected;
//                    EmployeeSelected = table_Scene1.getSelectionModel().getSelectedItems();
//                    Employee empo = EmployeeSelected.get(0);
                    //GridPane with 10px padding around edge
                    GridPane grid_Scene4 = new GridPane();
                    grid_Scene4.setAlignment(Pos.CENTER);
                    grid_Scene4.setPadding(new Insets(10, 10, 10, 10));
                    grid_Scene4.setVgap(8);
                    grid_Scene4.setHgap(10);


                    //-------------------Name Label
                    Label nameLabel_Scene4 = new Label("Name:");
                    GridPane.setConstraints(nameLabel_Scene4, 0, 0);
                    //Name Input
                    TextField nameInput_Scene4 = new TextField("Name");  // you will write the function this.getName() in the place of "Name" string
                    nameInput_Scene4.setDisable(true);
                    GridPane.setConstraints(nameInput_Scene4, 1, 0);
                    //----------------------Gender input
                    ChoiceBox<String> gender_Scene4 = new ChoiceBox<>();
                    gender_Scene4.getItems().addAll("Mr.", "Mrs.");
                    GridPane.setConstraints(gender_Scene4, 2, 0);

                    //-----------------Address Label
                    Label addressLabel_Scene4 = new Label("Address:");
                    GridPane.setConstraints(addressLabel_Scene4, 0, 3);
                    //Address Input
                    TextField addressInput_Scene4 = new TextField();
                    addressInput_Scene4.setPromptText("Address");
                    GridPane.setConstraints(addressInput_Scene4, 1, 3);


                    //-------------------------Phone no. Label
                    Label phoneLabel_Scene4 = new Label("Phone number:");
                    GridPane.setConstraints(phoneLabel_Scene4, 0, 2);
                    //Phone no. Input
                    TextField phoneInput_Scene4 = new TextField();
                    phoneInput_Scene4.setPromptText("Phone number");
                    GridPane.setConstraints(phoneInput_Scene4, 1, 2);

                    //--------------------------Email Label
                    Label emailLabel_Scene4 = new Label("Email:");
                    GridPane.setConstraints(emailLabel_Scene4, 0, 1);
                    //Email Input
                    TextField emailInput_Scene4 = new TextField();
                    emailInput_Scene4.setPromptText("Email");
                    GridPane.setConstraints(emailInput_Scene4, 1, 1);

                    //--------------------------Position Label
                    Label positionLabel_Scene4 = new Label("Position:");
                    GridPane.setConstraints(positionLabel_Scene4, 0, 6);
                    //Position Input
                    TextField positionInput_Scene4 = new TextField();
                    positionInput_Scene4.setPromptText("Position");
                    GridPane.setConstraints(positionInput_Scene4, 1, 6);

                    //Type of employment Input
                    TextField typeOfEmploymentInput_Scene4 = new TextField();
                    typeOfEmploymentInput_Scene4.setPromptText("Type of Employment");
                    GridPane.setConstraints(typeOfEmploymentInput_Scene4, 2, 6);

                    //---------------------------------Salary Label
                    Label salaryLabel_Scene4 = new Label("Salary :");
                    GridPane.setConstraints(salaryLabel_Scene4, 0, 7);
                    //Salary Input
                    TextField salaryInput_Scene4 = new TextField();
                    salaryInput_Scene4.setPromptText("Old Salary");                                    //  you enter the default value here
                    salaryInput_Scene4.setDisable(true);
                    GridPane.setConstraints(salaryInput_Scene4, 1, 7);

                    //requested salary

                    TextField requestedSalaryInput_Scene4 = new TextField();
                    requestedSalaryInput_Scene4.setPromptText("RaiseRequest status");                           //  you enter the default value here
                    requestedSalaryInput_Scene4.setDisable(true);


                    //buttons to approve or deny
                    GridPane.setConstraints(requestedSalaryInput_Scene4, 2, 7);
                    Button confirmSalaryButton_Scene4 = new Button("Approve");
                    GridPane.setConstraints(confirmSalaryButton_Scene4, 3, 7);
                    Button denySalaryButton_Scene4 = new Button("Deny");
                    GridPane.setConstraints(denySalaryButton_Scene4, 4, 7);

                    //---------------------------Vacation Label
                    Label vacationLabel_Scene4 = new Label("Requested Vacation :");
                    GridPane.setConstraints(vacationLabel_Scene4, 0, 8);
                    //Vacation Input
                    TextField startVacationInput_Scene4 = new TextField();
                    startVacationInput_Scene4.setPromptText("Start Date");                             //  you enter the default value here
                    startVacationInput_Scene4.setDisable(true);
                    GridPane.setConstraints(startVacationInput_Scene4, 1, 8);

                    //requested vacation
                    TextField endVacationInput_Scene4 = new TextField();
                    endVacationInput_Scene4.setPromptText("End Date");                                 //  you enter the default value here
                    endVacationInput_Scene4.setDisable(true);

                    //buttons to approve or deny
                    GridPane.setConstraints(endVacationInput_Scene4, 2, 8);
                    Button confirmVacationButton_Scene4 = new Button("Approve");
                    GridPane.setConstraints(confirmVacationButton_Scene4, 3, 8);
                    Button denyVacationButton_Scene4 = new Button("Deny");
                    GridPane.setConstraints(denyVacationButton_Scene4, 4, 8);

                    //-------------------------------------Username  Label
                    Label usernameLabel_Scene4 = new Label("User name:");
                    GridPane.setConstraints(usernameLabel_Scene4, 0, 9);
                    //Username Input
                    TextField usernameInput_Scene4 = new TextField();
                    usernameInput_Scene4.setPromptText("username");
                    GridPane.setConstraints(usernameInput_Scene4, 1, 9);

                    //-------------------------------------Password Label
                    Label passwordLabel_Scene4 = new Label("Password:");
                    GridPane.setConstraints(passwordLabel_Scene4, 0, 10);
                    //Password Input
                    PasswordField passwordField_Scene4 = new PasswordField();
                    passwordField_Scene4.setPromptText("Your password");
                    GridPane.setConstraints(passwordField_Scene4, 1, 10);
                    passwordField_Scene4.setText("CONFIDENTIAL");
                    //------------------------------------Nationality Label
                    Label nationalityLabel_Scene4 = new Label("Nationality:");
                    GridPane.setConstraints(nationalityLabel_Scene4, 0, 4);
                    //Nationality Input
                    TextField nationalityInput_Scene4 = new TextField("Nationality");              //you will add the true nationality here
                    nationalityInput_Scene4.setDisable(true);
                    GridPane.setConstraints(nationalityInput_Scene4, 1, 4);

                    //---------------------------------------Date of birth Label
                    Label dateOfBirthLabel_Scene4 = new Label("Date of Birth:");
                    GridPane.setConstraints(dateOfBirthLabel_Scene4, 0, 5);
                    TextField dateOfBirth_Scene4 = new TextField("Date of birth");
                    GridPane.setConstraints(dateOfBirth_Scene4, 1, 5);
                    dateOfBirth_Scene4.setDisable(true);

                    //--------------------------------------total number of completed tasks label
                    Label completedTaskLabel_Scene4 = new Label("Total completed Tasks:");
                    GridPane.setConstraints(completedTaskLabel_Scene4, 0, 11);
                    TextField completedTasksInput_Scene4 = new TextField();
                    completedTasksInput_Scene4.setPromptText("eg:50");                                 //  you enter the default value here
                    GridPane.setConstraints(completedTasksInput_Scene4, 1, 11);
                    completedTasksInput_Scene4.setDisable(true);

                    //--------------------------------------------Current task label
                    Label currentTaskLabel_Scene4 = new Label("Current Tasks:");
                    GridPane.setConstraints(currentTaskLabel_Scene4, 0, 12);
                    //Text field
                    TextField currentTaskInput_Scene4 = new TextField("Project A");                    //  you enter the default value here
                    GridPane.setConstraints(currentTaskInput_Scene4, 1, 12);
                    currentTaskInput_Scene4.setDisable(true);

                    //--------------------------------------------Performance label
                    Label performanceLabel_Scene4 = new Label("Performance:");
                    GridPane.setConstraints(performanceLabel_Scene4, 0, 13);
                    //Text field
                    TextField performanceInput_Scene4 = new TextField("30%");                    //  you enter the default value here
                    GridPane.setConstraints(performanceInput_Scene4, 1, 13);
                    performanceInput_Scene4.setDisable(true);


                    //Save button
                    Button saveButton_Scene4 = new Button("Save");
                    //GridPane.setConstraints(saveButton, 1, 2);

                    //Cancel button
                    Button cancelButton_Scene4 = new Button("Cancel");
                    //GridPane.setConstraints(cancelButton, 2, 2);

                    HBox hBox_Scene4 = new HBox();
                    hBox_Scene4.setPadding(new Insets(10,10,10,10));
                    hBox_Scene4.setSpacing(10);
                    hBox_Scene4.getChildren().addAll( saveButton_Scene4, cancelButton_Scene4);
                    GridPane.setConstraints(hBox_Scene4, 1, 14);


                    //Add everything to grid
                    grid_Scene4.getChildren().addAll(nameLabel_Scene4, nameInput_Scene4, addressLabel_Scene4, addressInput_Scene4,phoneLabel_Scene4,phoneInput_Scene4,emailLabel_Scene4,
                            emailInput_Scene4, positionInput_Scene4,positionLabel_Scene4,salaryInput_Scene4,salaryLabel_Scene4,requestedSalaryInput_Scene4,usernameInput_Scene4,usernameLabel_Scene4,passwordField_Scene4,
                            passwordLabel_Scene4, nationalityLabel_Scene4, nationalityInput_Scene4,dateOfBirthLabel_Scene4,dateOfBirth_Scene4,gender_Scene4,hBox_Scene4,confirmSalaryButton_Scene4,denySalaryButton_Scene4,
                            completedTaskLabel_Scene4,completedTasksInput_Scene4,currentTaskLabel_Scene4,currentTaskInput_Scene4,typeOfEmploymentInput_Scene4,performanceInput_Scene4
                            ,performanceLabel_Scene4,vacationLabel_Scene4,startVacationInput_Scene4,endVacationInput_Scene4,confirmVacationButton_Scene4,denyVacationButton_Scene4);

                    sceneEmployeeInfo_Scene4 = new Scene(grid_Scene4, 1000, 700);
                    sceneEmployeeInfo_Scene4.getStylesheets().add(getClass().getResource("newstyle.css").toExternalForm());



////////////////////////////////////////////New scene 5 (Request vacation)/////////////////////////////////////////////////////////////////////////


                    GridPane grid_Scene5=new GridPane();
                    grid_Scene5.setAlignment(Pos.CENTER);
                    grid_Scene5.setVgap(10);
                    grid_Scene5.setHgap(10);
                    grid_Scene5.setPadding(new Insets(10));

                    Label vacationRequestLabel_Scene5 =new Label("Vacation Request");
                    vacationRequestLabel_Scene5.setFont(Font.font("verdana", FontWeight.BLACK.BOLD,20));
                    GridPane.setConstraints(vacationRequestLabel_Scene5, 0, 0);


                    // -----------------------start date
                    Label startDateLabel_Scene5=new Label("Start Date: ");
                    GridPane.setConstraints(startDateLabel_Scene5, 0, 1);
                    DatePicker startDatePicker_Scene5 = new DatePicker();
                    GridPane.setConstraints(startDatePicker_Scene5, 1, 1);




                    //---------------------------end date
                    Label endDateLabel_Scene5=new Label("End Date: ");
                    GridPane.setConstraints(endDateLabel_Scene5, 0, 2);
                    DatePicker endDatePicker_Scene5 = new DatePicker();
                    GridPane.setConstraints(endDatePicker_Scene5, 1, 2);



                    Button submitButton_Scene5=new Button("Submit Request");
                    Button cancelButton_Scene5=new Button("Cancel");

                    HBox hBox_Scene5 = new HBox();
                    hBox_Scene5.setPadding(new Insets(10,10,10,10));
                    hBox_Scene5.setSpacing(10);
                    hBox_Scene5.getChildren().addAll( submitButton_Scene5, cancelButton_Scene5);
                    GridPane.setConstraints(hBox_Scene5, 1, 3);

                    grid_Scene5.getChildren().addAll(vacationRequestLabel_Scene5,startDateLabel_Scene5,startDatePicker_Scene5,endDateLabel_Scene5,endDatePicker_Scene5,
                            hBox_Scene5);

                    requestVacation_Scene5 = new Scene(grid_Scene5, 600, 600);
                    requestVacation_Scene5.getStylesheets().add(getClass().getResource("newstyle.css").toExternalForm());

////////////////////////////////////////////New scene 6 (Task manager)/////////////////////////////////////////////////////////////////////////

                    //Name column
                    TableColumn<Task, String> nameColumn_Scene6 = new TableColumn<>("Name");
                    nameColumn_Scene6.setMinWidth(200);
                    nameColumn_Scene6.setCellValueFactory(new PropertyValueFactory<>("Name"));

                    //date column
                    TableColumn<Task, String> dateColumn_Scene6 = new TableColumn<>("Issuing Date");
                    dateColumn_Scene6.setMinWidth(100);
                    dateColumn_Scene6.setCellValueFactory(new PropertyValueFactory<>("issuingDate"));

                    //deadline column
                    TableColumn<Task, String> deadlineColumn_Scene6 = new TableColumn<>("Deadline");
                    deadlineColumn_Scene6.setMinWidth(100);
                    deadlineColumn_Scene6.setCellValueFactory(new PropertyValueFactory<>("deadline"));

                    //Project manager column
                    TableColumn<Task, String> projectManagerColumn_Scene6 = new TableColumn<>("Project Manager");
                    projectManagerColumn_Scene6.setMinWidth(200);
                    projectManagerColumn_Scene6.setCellValueFactory(new PropertyValueFactory<>("projectManager"));




                    //Button
                    Button addButton_Scene6 = new Button("Add");
                    Button deleteButton_Scene6 = new Button("Delete");
                    Button homeScreen_Scene6 = new Button("Home Screen");



                    HBox hBox_Scene6 = new HBox();
                    hBox_Scene6.setPadding(new Insets(10,10,10,10));
                    hBox_Scene6.setSpacing(10);
                    hBox_Scene6.getChildren().addAll( addButton_Scene6, deleteButton_Scene6);
                    homeScreen_Scene6.setTranslateX(200);
                    addButton_Scene6.setTranslateX(200);
                    deleteButton_Scene6.setTranslateX(200);

                    HBox hBox1_Scene6 = new HBox();
                    hBox1_Scene6.setPadding(new Insets(10,10,10,10));
                    hBox1_Scene6.setSpacing(10);
                    hBox1_Scene6.getChildren().addAll( homeScreen_Scene6);



                    table_Scene6 = new TableView<>();
                    table_Scene6.setItems(getTask(t));
                    table_Scene6.getColumns().addAll(nameColumn_Scene6, dateColumn_Scene6, deadlineColumn_Scene6/*,projectManagerColumn_Scene6*/);

                    VBox vBox_Scene6 = new VBox();
                    vBox_Scene6.getChildren().addAll(table_Scene6, hBox_Scene6,hBox1_Scene6);
                    vBox_Scene6.setAlignment(Pos.CENTER);


                    taskManager_Scene6 = new Scene(vBox_Scene6,600,500);
                    taskManager_Scene6.getStylesheets().add(getClass().getResource("newstyle.css").toExternalForm());


////////////////////////////////////////////New scene 7 (Add new Task)/////////////////////////////////////////////////////////////////////////




                    //GridPane with 10px padding around edge
                    GridPane grid_Scene7 = new GridPane();
                    grid_Scene7.setAlignment(Pos.CENTER);
                    grid_Scene7.setPadding(new Insets(10, 10, 10, 10));
                    grid_Scene7.setVgap(8);
                    grid_Scene7.setHgap(10);

                    //-----------------------------Name Label
                    Label taskNameLabel_Scene7 = new Label("Task Name:");
                    GridPane.setConstraints(taskNameLabel_Scene7, 0, 0);
                    //Name Input
                    TextField nameInput_Scene7 = new TextField();
                    nameInput_Scene7.setPromptText("Name");
                    GridPane.setConstraints(nameInput_Scene7, 1, 0);
                    System.out.println(nameInput_Scene7.getText());

                    //-------------------------------Issuing date Label---------------------------------------------------------
                    Label issuingDateLabel_Scene7 = new Label("Issuing Date:");
                    GridPane.setConstraints(issuingDateLabel_Scene7, 0, 1);
                    // create a date picker
                    DatePicker d_Scene7 = new DatePicker();
                    GridPane.setConstraints(d_Scene7, 1, 1);

       /* // action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                // get the date picker value
                LocalDate i = d_Scene7.getValue();

                // get the selected date
                issuingDateLabel.setText("Date :" + i);
            }
        };*/

                    // show week numbers
                    d_Scene7.setShowWeekNumbers(true);
                    // when datePicker is pressed
                    //d_Scene7.setOnAction(event);


                    //----------------------------------Deadline Label----------------------------------------------------------
                    Label deadlineLabel_Scene7 = new Label("Deadline:");
                    GridPane.setConstraints(deadlineLabel_Scene7, 0, 2);
                    // create a date picker
                    DatePicker d2_Scene7 = new DatePicker();
                    GridPane.setConstraints(d2_Scene7, 1, 2);

       /* // action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                // get the date picker value
                LocalDate i = d2_Scene7.getValue();

                // get the selected date
                deadlineLabel.setText("Date :" + i);
            }
        };*/

                    // show week numbers
                    d2_Scene7.setShowWeekNumbers(true);

                    // when datePicker is pressed
                    //d_Scene7.setOnAction(event);

                    //--------------------Project manager Label
                    //Label projectManagerLabel_Scene7 = new Label("Project Manager Name:");
                    //GridPane.setConstraints(projectManagerLabel_Scene7, 0, 3);
                    //Name Input
                    //TextField projectManagerInput_Scene7 = new TextField();
                    //projectManagerInput_Scene7.setPromptText("Name");
                    //GridPane.setConstraints(projectManagerInput_Scene7, 1, 3);


                    //--------------------Project description Label
                    Label descriptionLabel_Scene7 = new Label("Project Description:");
                    GridPane.setConstraints(descriptionLabel_Scene7, 0, 4);
                    //Name Input
                    TextField descriptionInput_Scene7 = new TextField();
                    descriptionInput_Scene7.setPromptText("Type Here...");
                    GridPane.setConstraints(descriptionInput_Scene7, 1, 4);



                    //Save button
                    Button saveButton_Scene7 = new Button("Save");

                    //Cancel button
                    Button cancelButton_Scene7 = new Button("Cancel");

                    HBox hBox_Scene7 = new HBox();
                    hBox_Scene7.setPadding(new Insets(10,10,10,10));
                    hBox_Scene7.setSpacing(10);
                    hBox_Scene7.getChildren().addAll( saveButton_Scene7, cancelButton_Scene7);
                    GridPane.setConstraints(hBox_Scene7, 1, 5);


                    //Add everything to grid
                    grid_Scene7.getChildren().addAll(taskNameLabel_Scene7,nameInput_Scene7,issuingDateLabel_Scene7,d_Scene7,deadlineLabel_Scene7,d2_Scene7/*,projectManagerInput_Scene7*/,
                            /*projectManagerLabel_Scene7,*/descriptionLabel_Scene7,descriptionInput_Scene7,hBox_Scene7);

                    sceneAddTask_Scene7 = new Scene(grid_Scene7, 800, 500);
                        sceneAddTask_Scene7.getStylesheets().add(getClass().getResource("newstyle.css").toExternalForm());



                    //-------------------------------------------------
                    viewAllEmployee_Scene0.setOnAction(e-> {
                        try {
                            table_Scene1.setItems(getEmployee(t));
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }
                        Window.setScene(sceneAllEmployees_Scene1);
                    });
                    addNewEmployee_Scene0.setOnAction(e-> Window.setScene(sceneAddEmployee_Scene2));
                    saveButton_Scene2.setOnAction(e->{
                        Employee empol = new Employee();
                        Gender g =null;
                        empol.setNationality(nationalityInput_Scene2.getText());
                        empol.setName(nameInput_Scene2.getText());
                        empol.setSalary(Integer.parseInt(salaryInput_Scene2.getText()));
                        empol.setPosition(positionInput_Scene2.getText());
                        empol.setTypeOfEmployement(Employement.valueOf(typeOfEmploymentInput_Scene2.getText()));
                        empol.setPhone(phoneInput_Scene2.getText());

                        if(gender_Scene2.getValue()=="Mr."){
                            empol.setGender(Gender.male);
                        }
                        else if(gender_Scene2.getValue()=="Mrs."){
                            empol.setGender(Gender.female);
                        }
                        empol.setAddress(addressInput_Scene2.getText());
                        empol.setEmail(emailInput_Scene2.getText());
                        try {
                            LocalDate myLocalDate =(d_Scene2.getValue());
                            Date doe=Date.from(myLocalDate.atStartOfDay(ZoneId.of( "America/Montreal" )).toInstant());
                            String Ds=new SimpleDateFormat("dd-MM-yyyy").format(doe);
                            empol.setBirthdate(new SimpleDateFormat("dd-MM-yyyy").parse(Ds));
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }
                        empol.setJobDescription("");
                        empol.setUsername(usernameInput_Scene2.getText());
                        empol.setPass(passwordField_Scene2.getText());
                        empol.setManagerID(emp.getId());
                        try {
                            Employee.addEmployee(empol);
                            Employee.getEmployee(empol);
                            String id =empol.getId();
                            List<String> as=new ArrayList<>();
                            Collections.addAll(as,t.getEmployees());
                            as.add(id);
                            t.setEmployees(as.toArray(new String[0]));
                            Team.updateTeam(t);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }
                    });
                    homescreenButton_Scene1.setOnAction(e->Window.setScene(mainScreen));
                    cancelButton_Scene2.setOnAction(e-> Window.setScene(mainScreen));
                    addButton_Scene1.setOnAction(e-> Window.setScene(sceneAddEmployee_Scene2));
                    myProfile_Scene0.setOnAction(e-> Window.setScene(myProfile_Scene3));
                    cancelButton.setOnAction(e-> Window.setScene(mainScreen));
                    saveButton_Scene4.setOnAction(e->{
                        ObservableList<Employee> EmployeeSelected;
                        EmployeeSelected = table_Scene1.getSelectionModel().getSelectedItems();
                        Employee empo = EmployeeSelected.get(0);
                        empo.setUsername(usernameInput_Scene4.getText());
                        empo.setAddress(usernameInput_Scene4.getText());
                        empo.setEmail(emailInput_Scene4.getText());
                        empo.setPhone(phoneInput_Scene4.getText());
                        empo.setUsername(usernameInput_Scene4.getText());
                        empo.setNationality(nationalityInput_Scene4.getText());
                        empo.setAddress(addressInput_Scene4.getText());
                        empo.setName(nameInput_Scene4.getText());
                        empo.setPosition(positionInput_Scene4.getText());
                        empo.setTypeOfEmployement(Employement.valueOf(typeOfEmploymentInput_Scene4.getText()));
                        empo.setSalary(Integer.valueOf(salaryInput_Scene4.getText()));
                        try {
                            empo.setBirthdate(new SimpleDateFormat("dd-MM-yyyy").parse(dateOfBirth_Scene4.getText()));
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }
                        empo.setTotalCompletedTasks(Integer.valueOf(completedTasksInput_Scene4.getText()));
                        empo.setRating(Float.valueOf(performanceInput_Scene4.getText()));
                        if(gender_Scene4.getValue().equals("Mr.")){
                            empo.setGender(Gender.male);
                        } else if(gender_Scene4.getValue().equals("Mrs.")){
                            empo.setGender(Gender.female);
                        }
                        try {
                            Employee.updateEmployee(empo);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    });

                    confirmSalaryButton_Scene4.setOnAction(e->{
                        ObservableList<Employee> EmployeeSelected;
                        EmployeeSelected = table_Scene1.getSelectionModel().getSelectedItems();
                        Employee empo = EmployeeSelected.get(0);
                        RaiseRequest rr = empo.getRaiseRequest().get(0);
                        rr.setStatus("accepted");
                        ArrayList<RaiseRequest> rra = empo.getRaiseRequest();
                        rra.set(0, rr);
                        empo.setRaiseRequest(rra);
                        empo.setSalary(Integer.valueOf(salaryInput_Scene4.getText()));
                        try {
                            Employee.updateEmployee(empo);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    });
                    denySalaryButton_Scene4.setOnAction(e->{
                        ObservableList<Employee> EmployeeSelected;
                        EmployeeSelected = table_Scene1.getSelectionModel().getSelectedItems();
                        Employee empo = EmployeeSelected.get(0);
                        RaiseRequest rr = empo.getRaiseRequest().get(0);
                        rr.setStatus("denied");
                        ArrayList<RaiseRequest> rra = empo.getRaiseRequest();
                        rra.set(0, rr);
                        empo.setRaiseRequest(rra);
                        empo.setSalary(Integer.valueOf(salaryInput_Scene4.getText()));
                        try {
                            Employee.updateEmployee(empo);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    });
                    confirmVacationButton_Scene4.setOnAction(e->{
                        ObservableList<Employee> EmployeeSelected;
                        EmployeeSelected = table_Scene1.getSelectionModel().getSelectedItems();
                        Employee empo = EmployeeSelected.get(0);
                        VacationRequest rr = empo.getVacations().get(0);
                        ArrayList<VacationRequest> vta = t.getVacationRequests();
                        vta.remove(rr);
                        t.setVacationRequests(vta);
                        try {
                            Team.updateTeam(t);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        rr.setStatus(Status.accepted);
                        ArrayList<VacationRequest> rra = empo.getVacations();
                        rra.set(0, rr);
                        empo.setVacations(rra);
                        try {
                            Employee.updateEmployee(empo);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    });
                    denyVacationButton_Scene4.setOnAction(e->{
                        ObservableList<Employee> EmployeeSelected;
                        EmployeeSelected = table_Scene1.getSelectionModel().getSelectedItems();
                        Employee empo = EmployeeSelected.get(0);
                        VacationRequest rr = empo.getVacations().get(0);
                        rr.setStatus(Status.denied);
                        ArrayList<VacationRequest> rra = empo.getVacations();
                        rra.set(0, rr);
                        empo.setVacations(rra);
                        try {
                            Employee.updateEmployee(empo);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    });
                    viewButton_Scene1.setOnAction(e-> {
                                ObservableList<Employee> EmployeeSelected;
                                EmployeeSelected = table_Scene1.getSelectionModel().getSelectedItems();
                                Employee empo = EmployeeSelected.get(0);
                                ArrayList<VacationRequest> vrt = t.getVacationRequests();
                                ArrayList<RaiseRequest> rrt=t.getRaiseRequests();
                                String status = null;
                                String startvaca = null;
                                String endvaca = null;
                                String taskname = null;
                        /*try{
                            startvaca=(empo.getVacations().get(0)).getStartDate();
                        }
                        catch(IndexOutOfBoundsException ioobe){
                            startvaca="No requests";

                        }
                        try{
                            endvaca=(empo.getVacations().get(0)).getEndDate();
                        }
                        catch(IndexOutOfBoundsException ioobe){
                            endvaca="No requests";
                        }
                                try {
                                    status = (empo.getRaiseRequest()).get(0).getStatus();
                                } catch (IndexOutOfBoundsException ioobe) {
                                    status = "No requests";
                                }*/
                                try {
                                    taskname = (empo.getTasks().get((empo.getTasks()).size()-1).getName());
                                } catch (IndexOutOfBoundsException ioobe) {
                                    taskname = "No tasks";
                                }
                        startvaca = "No requests";
                        endvaca = "No requests";
                                try {
                                    for (int i = 0; i < vrt.size(); i++) {
                                        if (empo.getId().equals(vrt.get(i).getEmployeeID()) && vrt.get(i).getStatus().equals("pending")) {
                                            startvaca = (vrt.get(i)).getStartDate();
                                            endvaca = (vrt.get(i)).getEndDate();
                                            break;
                                        }
                                    }
                                }
                                catch(IndexOutOfBoundsException ioobe){
                                    startvaca = "No requests";
                                    endvaca = "No requests";
                                }
                        status="No request";
                        try {
                            for (int i = 0; i < rrt.size(); i++) {
                                if (empo.getId().equals(rrt.get(i).getEmp()) && rrt.get(i).getStatus().equals("pending")) {
                                    status = "pending";
                                    break;
                                }
                            }
                        }
                        catch(IndexOutOfBoundsException ioobe){
                            status="No request";
                        }

                            nameInput_Scene4.setText(empo.getName());

                        if(empo.getGender().equals(Gender.male)) {
                            gender_Scene4.setValue("Mr.");
                        }
                        else if (empo.getGender().equals(Gender.female)){
                            gender_Scene4.setValue("Mrs.");
                        }
                        addressInput_Scene4.setText(empo.getAddress());
                        phoneInput_Scene4.setText(empo.getPhone());
                        emailInput_Scene4.setText(empo.getEmail());
                        positionInput_Scene4.setText(empo.getPosition());

                        typeOfEmploymentInput_Scene4.setText(empo.getTypeOfEmployement().toString());
                        salaryInput_Scene4.setText(String.valueOf(empo.getSalary()));

                       requestedSalaryInput_Scene4.setText(status);
                       startVacationInput_Scene4.setText(startvaca);
                       endVacationInput_Scene4.setText(endvaca);
                        usernameInput_Scene4.setText(empo.getUsername());
                        nationalityInput_Scene4.setText(empo.getNationality());
                        dateOfBirth_Scene4.setText(empo.getBirthdate());
                        phoneInput_Scene4.setText(empo.getPhone());
                        completedTaskLabel_Scene4.setText(String.valueOf((empo.getTotalCompletedTasks())));
                       currentTaskInput_Scene4.setText(taskname);
                        performanceInput_Scene4.setText(String.valueOf(empo.getRating()));
                        Window.setScene(sceneEmployeeInfo_Scene4);
                    });
                    cancelButton_Scene4.setOnAction(e-> Window.setScene(mainScreen));
                    vacation_button_Scene0.setOnAction(e-> Window.setScene(requestVacation_Scene5));
                    submitButton_Scene5.setOnAction(e-> {
                        LocalDate myLocalDate2 = startDatePicker_Scene5.getValue();
                        Date doe2=Date.from(myLocalDate2.atStartOfDay(ZoneId.of( "America/Montreal" )).toInstant());
                        String Ds2=new SimpleDateFormat("dd-MM-yyyy").format(doe2);
                        LocalDate myLocalDate = startDatePicker_Scene5.getValue();
                        Date doe=Date.from(myLocalDate.atStartOfDay(ZoneId.of( "America/Montreal" )).toInstant());
                        String Ds=new SimpleDateFormat("dd-MM-yyyy").format(doe);

                        VacationRequest vr = null;
                        try {
                            vr = new VacationRequest(new SimpleDateFormat("dd-MM-yyyy").parse(Ds), new SimpleDateFormat("dd-MM-yyyy").parse(Ds2), emp.getId());
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }
                        try {
                            submitTeamVacation(emp, t, vr);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    });
                    cancelButton_Scene5.setOnAction(e-> Window.setScene(mainScreen));
                    deleteButton_Scene6.setOnAction(e->deleteTaskTeam(t));
                    task_manager_button_Scene0.setOnAction(e-> Window.setScene(taskManager_Scene6));
                    homeScreen_Scene6.setOnAction(e-> Window.setScene(mainScreen));
                    addButton_Scene6.setOnAction(e-> Window.setScene(sceneAddTask_Scene7));
                    saveButton_Scene7.setOnAction(e->{
                        Task to = null;
                        try {
                            to = new Task();
                            to.setName(nameInput_Scene7.getText());
                            LocalDate myLocalDate =(d2_Scene7.getValue());
                            Date doe=Date.from(myLocalDate.atStartOfDay(ZoneId.of( "America/Montreal" )).toInstant());
                            String Ds=new SimpleDateFormat("dd-MM-yyyy").format(doe);
                            to.setDeadline(new SimpleDateFormat("dd-MM-yyyy").parse(Ds));

                            to.setDescription(descriptionInput_Scene7.getText());
                            addTaskTeam(t,to);
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        Window.setScene(mainScreen);
                    });
                    cancelButton_Scene7.setOnAction(e-> Window.setScene(mainScreen));



                    //---------------------------------------------------
                    task_manager_button.setOnAction(ev -> Window.setScene(task_manager));
                    myPerformance.setOnAction(ev -> {
                        performanceMetric.setText(String.valueOf(emp.getRating()));
                        totalCompletedtasks.setText(String.valueOf(emp.getTotalCompletedTasks()));
                        Window.setScene(performance);
                    });
                    add_task_button.setOnAction(ev -> Window.setScene(add_task));
                    edit_task_button.setOnAction(ev -> Window.setScene(add_task));
                    edit_task.setOnAction(ev -> {
                        deleteButtonClicked(emp);
                        try {
                            addTaskClicked(emp);
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        Window.setScene(task_manager);
                        popUpWindow.showAndWait();
                    });
                    //markAsCompletedButton.setOnAction(ev ->{});
                    TeamTasks.setOnAction(ev -> Window.setScene(team_task_manager));
                    set_task.setOnAction(ev -> {
                        try {
                            addTaskClicked(emp);
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        Window.setScene(task_manager);
                        popUpWindow.showAndWait();
                    });
                    backTO_main.setOnAction(ev -> Window.setScene(main_scene));
                    vacation_button.setOnAction(ev -> Window.setScene(vacation));
                    backToMain.setOnAction(ev -> Window.setScene(main_scene));
                    myProfile.setOnAction(ev -> Window.setScene(profile));
                    sendSalaryRaise.setOnAction(ev -> {
                        try {
                            raiseRequest(emp,t);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        popUpWindow.showAndWait();
                    });
                    backToMainFromProfile.setOnAction(ev -> Window.setScene(main_scene));
                    vacation_submit_request.setOnAction(ev -> {
                        try {
                            VacationRequest vr = new VacationRequest(new SimpleDateFormat("dd-MM-yyyy").parse(add_startDATE.getText()),new SimpleDateFormat("dd-MM-yyyy").parse(add_endDATE.getText()),emp.getId());
                            submitVacation(emp,t,vr);
                            Window.setScene(main_scene);
                            popUpWindow.showAndWait();
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });
                    backTO_main2.setOnAction(ev -> Window.setScene(main_scene));
                    button1.setOnAction(ev -> popUpWindow.close());
                    cancelVacationRequest.setOnAction(ev -> Window.setScene(main_scene));
                    cancelTaskAdd.setOnAction(ev -> Window.setScene(main_scene));
                    markAsCompletedButton.setOnAction(ev -> {

                        try {
                            markasCompleted(emp,t);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        popUpWindow.showAndWait();
                    });
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }

        });

        System.out.println(emp.getEmail());

        Window.setScene(login_scene);
        Window.show();

    }

    public ObservableList<Task> getTeamTasks(Employee emp) throws InterruptedException, ParseException, IOException {
//       System.out.println(emp.getUsername());
        ObservableList<Task> Task = FXCollections.observableArrayList();
        //SimpleDateFormat d =new SimpleDateFormat("dd-MM-yyyy");
        // java.util.Date myDate = d.parse("11-11-6666");
        //Task.add(new Task("Mahmoud", "8P35",myDate));
        Team t = new Team();
        Team.getTeams(emp.getManagerID(),t);
        ArrayList<Task> ta = t.getTasks();
        System.out.println(ta.size());
        for(int i = 1; i < ta.size(); i++){
            Task.add(ta.get(i));
            System.out.println(ta.get(i));
        }
        System.out.println(ta);
        return Task;


    }

    public void markasCompleted(Employee empi,Team te) throws IOException, InterruptedException {
        int m= te.getTasks().size();
        int t = empi.getTotalCompletedTasks();
        t++;
        float rate=((float)t/(float)m)*100;
        empi.setRating(rate);
        empi.setTotalCompletedTasks(t);
        Employee.updateEmployee(empi);
    }
    public void addTaskClicked(Employee emp) throws ParseException, IOException, InterruptedException  //
    {
        Task task = new Task();
        task.setName(add_task_name.getText());
        task.setDescription(task_description.getText());
        Date date1= null;
        try {
            date1 = new SimpleDateFormat("dd-MM-yyyy").parse(completion_date.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        task.setCompletionDate(date1);
        ArrayList<Task> ta= emp.getTasks();
        ta.add(task);
        emp.setTasks(ta);
        Employee.updateEmployee(emp);
        taskTable.setItems(getTasks(emp));
        add_task_name.clear();
        task_description.clear();
    }
    public void deleteButtonClicked(Employee emp){
        ObservableList<Task> taskSelected, allTasks;
        allTasks = taskTable.getItems();
        taskSelected = taskTable.getSelectionModel().getSelectedItems();
        try {
            ArrayList<Task> ta = emp.getTasks();
            System.out.println(ta.size());
            for (int i = 0; i < taskSelected.size(); i++) {
                for(int j = 0; j < ta.size(); j++){
                    System.out.println(ta.get(j).getName().equals(taskSelected.get(i).getName()));
                    System.out.println(ta.get(j).getName() + "\t" + taskSelected.get(i).getName());
                    if (ta.get(j).getName().equals(taskSelected.get(i).getName())) {
                        ta.remove(j);
                    }
                }
                allTasks.remove(taskSelected.get(i));
            }
            emp.setTasks(ta);
            Employee.updateEmployee(emp);

        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Task> getTasks(Employee emp){
//        System.out.println(emp.getUsername());
        ObservableList<Task> Task = FXCollections.observableArrayList();
        ArrayList<Task> ta = emp.getTasks();
        for(int i = 1; i < ta.size(); i++){
            Task.add(ta.get(i));
        }
        return Task;
    }
    public ObservableList<Event> getEvents() throws InterruptedException, ParseException, IOException {
        ObservableList<Event> events = FXCollections.observableArrayList();
        ArrayList<Event> ev = Event.getEvents();
        System.out.println(ev.size());
        for(int i = 0; i < ev.size(); i++){
            events.add(ev.get(i));
        }
        return events;
    }
    public ObservableList<Task> getPerformance(){
        ObservableList<Task> performedTasks = FXCollections.observableArrayList();
        return performedTasks;
    }

    public Boolean loginButtonClicked(TextField user_name,PasswordField pass) throws IOException, InterruptedException {
        String s1 = Employee.login(user_name.getText(), pass.getText());
        if(s1.equals("success")){
            return true;
        } else {
            return false;
        }
    }
    public void submitVacation(Employee emp, Team t, VacationRequest vr) throws IOException, InterruptedException {
        ArrayList<VacationRequest> getvr =emp.getVacations();
        getvr.add(vr);
        ArrayList<VacationRequest> getvrforTeam = t.getVacationRequests();
        getvrforTeam.add(vr);
        t.setVacationRequests(getvrforTeam);
        emp.setVacations(getvr);
        Team.updateTeam(t);
        Employee.updateEmployee(emp);
    }
    public void raiseRequest(Employee emp, Team t) throws IOException, InterruptedException {
        RaiseRequest rr = new RaiseRequest(emp.getId(),"pending");
        ArrayList<RaiseRequest> getrr =emp.getRaiseRequest();
        getrr.add(rr);
        ArrayList<RaiseRequest> getrrforTeam = t.getRaiseRequests();
        getrrforTeam.add(rr);
        t.setRaiseRequests(getrrforTeam);
        emp.setRaiseRequest(getrr);
        Team.updateTeam(t);
        Employee.updateEmployee(emp);
    }
    //////////////////////////////////////////Methods Related//////////////////////////////////////////////////////////////////////////////
    //Delete button clicked
    public void deleteButtonTeamClicked(Team t){
        ObservableList<Employee> employeeSelected, allEmployees;
        allEmployees = table_Scene1.getItems();
        employeeSelected = table_Scene1.getSelectionModel().getSelectedItems();
        try {
            ArrayList<String> em = (ArrayList<String>) Arrays.asList(t.getEmployees());
            for (int i = 0; i < employeeSelected.size(); i++) {
                for(int j = 0; j < em.size(); j++){
                    if (em.get(j).equals(employeeSelected.get(i).getId())) {
                        em.remove(j);
                    }
                }
                allEmployees.remove(employeeSelected.get(i));
            }
            String [] arr = em.toArray(new String[0]);
            t.setEmployees(arr);
            Team.updateTeam(t);
            Employee.deleteEmployee(employeeSelected.get(0));

        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Get all of the employee
    public ObservableList<Employee> getEmployee(Team t) throws InterruptedException, IOException, ParseException {
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        //employees.add(new Employee("Mahmoud", "8P35", "Employee"));
        //employees.add(new Employee("Omar", "2Z49", "Employee"));
        //employees.add(new Employee("Mahmoud", "99M00", "Employee"));
        //employees.add(new Employee("Zakaria", "19O99", "Employee"));
        //employees.add(new Employee("Ahmed", "1U49", "Employee"));
        List<String> eid= (Arrays.asList(t.getEmployees()));

        try {
            for (int i = 1; i < eid.size(); i++) {
                employees.add(Employee.getEmployeeByID(eid.get(i)));
            }
        }
        catch(IndexOutOfBoundsException Ioofbe){
            return employees;
        }
        return employees;
    }

    //Get all of the employee
    public ObservableList<Task> getTask(Team Tem) throws ParseException {
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        ArrayList<Task> ta=Tem.getTasks();
        for(Task to:ta){
            tasks.add(to);
        }
        return tasks;
    }
    public void addTaskTeam(Team tem,Task to) throws ParseException, IOException, InterruptedException {
        ObservableList<Task> tol;
        tol=table_Scene6.getItems();
        ArrayList<Task> ta = tem.getTasks();
        ta.add(to);
        tem.setTasks(ta);
        Team.updateTeam(tem);
        tol.add(to);
    }
    public void deleteTaskTeam(Team tem){
        ObservableList<Task> taskSelected, allTasks;
        allTasks = table_Scene6.getItems();
        taskSelected = table_Scene6.getSelectionModel().getSelectedItems();
        try {
            ArrayList<Task> ta = tem.getTasks();
            System.out.println(ta.size());
            for (int i = 0; i < taskSelected.size(); i++) {
                for(int j = 0; j < ta.size(); j++){
                    if (ta.get(j).getName().equals(taskSelected.get(i).getName())) {
                        ta.remove(j);
                    }
                }
                allTasks.remove(taskSelected.get(i));
            }
            tem.setTasks(ta);
            Team.updateTeam(tem);

        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void submitTeamVacation(Employee emp, Team t, VacationRequest vr) throws IOException, InterruptedException {
        ArrayList<VacationRequest> getvr =emp.getVacations();
        getvr.add(vr);
        ArrayList<VacationRequest> getvrforTeam = t.getVacationRequests();
        getvrforTeam.add(vr);
        t.setVacationRequests(getvrforTeam);
        emp.setVacations(getvr);
        Team.updateTeam(t);
        Employee.updateEmployee(emp);
    }

}
