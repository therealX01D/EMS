package EMS;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

//const eventSchema = new mongoose.Schema({
//        name:{type: String},
//        issuingDate: {type: String},
//        eventDate: {type: String},
//        description: {type: String},
//        organizers: {type: [String]},
//        peopleEnrolled:{type: Number},
//        endRegistration: {type: String}
//        });
public class Event {
    private String id;
    private String name;
    private Date eventDate;
    private String description;
    private String [] organizers;
    private int peopleEnrolled = 0;
    private Date endRegisteration;

    public Event(String name, Date eventDate, int peopleEnrolled, String description, String[] organizers, Date endRegisteration, String id) throws ParseException {
        this.name = name;
        this.eventDate = eventDate;
        this.description = description;
        this.organizers = organizers;
        this.peopleEnrolled = peopleEnrolled;
        this.endRegisteration = endRegisteration;
        this.id = id;
    }

    public Event() {
    }


    public Event(String id, String name, Date eventDate, String description, String[] organizers, int peopleEnrolled, Date endRegisteration) throws ParseException {
        this.id = id;
        this.name = name;

        this.eventDate = eventDate;
        this.description = description;
        this.organizers = organizers;
        this.peopleEnrolled = peopleEnrolled;
        this.endRegisteration = endRegisteration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(this.eventDate);
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getOrganizers() {
        return organizers;
    }

    public void setOrganizers(String[] organizers) {
        this.organizers = organizers;
    }

    public int getPeopleEnrolled() {
        return peopleEnrolled;
    }

    public void setPeopleEnrolled(int peopleEnrolled) {
        this.peopleEnrolled = peopleEnrolled;
    }

    public String getEndRegisteration() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(endRegisteration);
    }

    public void setEndRegisteration(Date endRegisteration) {
        this.endRegisteration = endRegisteration;
    }

    public static ArrayList<Event> getEvents() throws InterruptedException, ParseException, IOException {
        return APILINK.getEvent();
    }
    public static void addEvent(Event e) throws IOException, InterruptedException {
        APILINK.addEvent(e);
    }
    public static void updateEvent(Event e) throws IOException, InterruptedException {
        APILINK.updateSpecficOfEvent(e);
    }
    public static void deleteEvent(Event e) throws IOException, InterruptedException {
        APILINK.deleteEvent(e);
    }
    public static void updateTeam(Team t) throws IOException, InterruptedException {
        APILINK.updateSpecficOfTeam(t);
    }

}
