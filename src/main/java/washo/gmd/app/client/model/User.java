package washo.gmd.app.client.model;

import java.io.Serializable;

public class User implements Serializable {

    private String picture;
    private String name;
    private String id;

    public User() {}

    public User(String picture, String name, String id) {
        this.picture = picture;
        this.name = name;
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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
}
