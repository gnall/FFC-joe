package washo.gmd.app.client.model;

import java.util.Date;

public class EventDTO {

    private Date date = new Date();
    private String title;
    private String description;
    private String location;

    public EventDTO() {}

    public EventDTO(String title, String description, String location, Date date) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
