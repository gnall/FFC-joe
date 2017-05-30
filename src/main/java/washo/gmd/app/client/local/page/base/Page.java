package washo.gmd.app.client.local.page.base;

import org.jboss.errai.ui.nav.client.local.PageShown;
import washo.gmd.app.client.local.events.NavigationEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.inject.Inject;

public class Page {

    private String id;

    @Inject
    Event<NavigationEvent> navigationEvent;

    @PostConstruct
    public void onPostConstruct() {}

    @PageShown
    public void onPageShown() {
        navigationEvent.fire(new NavigationEvent(this));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
