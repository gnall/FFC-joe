package washo.gmd.app.client.local.events;

import washo.gmd.app.client.model.User;

public class UserSignin {

    private User user;

    public UserSignin(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
