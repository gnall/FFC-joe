package washo.gmd.app.client.local.page.dashboard.list.cards;


import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Composite;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.Display;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import org.jboss.errai.ui.nav.client.local.TransitionTo;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import washo.gmd.app.client.local.page.dashboard.DashboardPage;
import washo.gmd.app.client.local.page.gameView.GamePage;
import washo.gmd.app.client.model.EventDTO;
import washo.gmd.app.shared.Game;


import javax.inject.Inject;

@Templated
public class GameCard extends Composite {

    @Inject
    @DataField
    MaterialLink title, /*location,*/ date;

    @Inject
    @DataField
    MaterialLabel locationInfo;
    
    @Inject
    @DataField
    private MaterialIcon btnIconButton1;
    
    @Inject
    TransitionTo<GamePage> gamePage;

    public GameCard() {}

    public void build(Game game) {
        title.setText(game.getTitle());
        title.setIconType(IconType.EVENT);
        title.setIconSize(IconSize.SMALL);
        locationInfo.setText(game.getLocationInfo());
        btnIconButton1.setIconType(IconType.FAVORITE);
        btnIconButton1.setIconColor(Color.BLUE);
        btnIconButton1.setWaves(WavesType.DEFAULT);
        btnIconButton1.setCircle(true);
        btnIconButton1.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				GWT.log("This is the game id returned from game card "+game.getGameID());
				Multimap<String, String> multimap = ArrayListMultimap.create();
				multimap.put("gameID", game.getGameID().toString());
				gamePage.go(multimap);
			}
        	
        });

        /*location.setFontSize("0.8em");
        location.setTextColor(Color.GREY);
        location.setText(game.getCoordinates());
        location.setIconType(IconType.LOCATION_ON);
        location.setIconColor(Color.RED);*/

        /*date.setFontSize("0.8em");
        date.setTextColor(Color.GREY);
        date.setText(DateTimeFormat.getFormat("MM/dd/yyyy").format(event.getDate()));
        date.setIconType(IconType.TIMER);
        date.setIconColor(Color.PURPLE);*/
    }
}
