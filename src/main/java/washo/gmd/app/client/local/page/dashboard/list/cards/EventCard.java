package washo.gmd.app.client.local.page.dashboard.list.cards;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Composite;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.Display;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import washo.gmd.app.client.model.EventDTO;

import javax.inject.Inject;

@Templated
public class EventCard extends Composite {

    @Inject
    @DataField
    MaterialLink title, location, date;

    @Inject
    @DataField
    MaterialLabel description;

    public EventCard() {}

    public void build(EventDTO event) {
        title.setText(event.getTitle());
        title.setIconType(IconType.EVENT);
        title.setIconSize(IconSize.SMALL);
        description.setText(event.getDescription());

        location.setFontSize("0.8em");
        location.setTextColor(Color.GREY);
        location.setText(event.getLocation());
        location.setIconType(IconType.LOCATION_ON);
        location.setIconColor(Color.RED);

        /*date.setFontSize("0.8em");
        date.setTextColor(Color.GREY);
        date.setText(DateTimeFormat.getFormat("MM/dd/yyyy").format(event.getDate()));
        date.setIconType(IconType.TIMER);
        date.setIconColor(Color.PURPLE);*/
    }
}
