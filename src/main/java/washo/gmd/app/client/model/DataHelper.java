package washo.gmd.app.client.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataHelper {

    public static List<EventDTO> getAllEvents() {
        List<EventDTO> events = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            events.add(new EventDTO("Event " + i, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            , "Location " + i, new Date()));
        }

        return events;
    }
}
