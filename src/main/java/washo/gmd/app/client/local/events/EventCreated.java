package washo.gmd.app.client.local.events;

import washo.gmd.app.client.model.EventDTO;

public class EventCreated {

    private EventDTO event;

    public EventCreated() {}

    public EventCreated(EventDTO event) {
        this.event = event;
    }

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }
}
