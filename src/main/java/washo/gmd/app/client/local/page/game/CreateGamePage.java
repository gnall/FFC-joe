/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package washo.gmd.app.client.local.page.game;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.*;

import org.jboss.errai.ui.nav.client.local.TransitionTo;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import washo.gmd.app.client.local.page.base.HasNavigation;
import washo.gmd.app.client.local.page.base.Page;
import washo.gmd.app.client.local.page.dashboard.DashboardPage;
import washo.gmd.app.client.local.page.dashboard.list.CreateGameService;
import washo.gmd.app.client.local.page.dashboard.list.CreateGameServiceAsync;
import washo.gmd.app.client.local.events.GameCreated;
import washo.gmd.app.client.local.events.UpdateNavBarContent;
import washo.gmd.app.shared.Game;

import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@Templated
@ApplicationScoped
@org.jboss.errai.ui.nav.client.local.Page(path = "stripe")
public class CreateGamePage extends Page implements HasNavigation {


    @Inject
    Event<UpdateNavBarContent> navBarContentEvent;


	

    @Inject
    Event<GameCreated> gameCreated;
    
    @Inject
    @DataField
    MaterialDatePicker date;

    @Inject
    @DataField
    MaterialTextBox title;

    @Inject
    @DataField
    MaterialTextBox locationInfo;

    @Inject
    @DataField
    MaterialTextBox location;
    
    @Inject
    @DataField
    MaterialButton create;
    
    @Inject
    @DataField
    private MaterialButton btnDropdown4;

    @Inject
    private MaterialDropDown dropdown4;
    
    @Inject
    TransitionTo<DashboardPage> dashboardPage;
    
    CreateGameServiceAsync createGameService;
    
    String selectedFieldType = "";

    @Override
    public void onPostConstruct() {
        super.onPostConstruct();

        setId("stripe");

        title.setLabel("Title");
        date.setPlaceholder("Date");
        locationInfo.setLabel("Location Info");
        location.setLabel("Location");
        buildSelectionEvent();

        create.setText("Create Event");
        create.setWaves(WavesType.DEFAULT);
        create.setBackgroundColor(Color.DEEP_PURPLE);
        create.setWidth("100%");

        create.addClickHandler(clickEvent -> {
            Game game = new Game();
            game.setTitle(title.getValue());
            Date test = date.getDate();Long date = test.getTime();game.setDate(date);
            game.setLocationInfo(locationInfo.getValue());
            game.setFieldType(whatFieldType());
            
            this.createGameService = GWT.create(CreateGameService.class);
            this.createGameService.createGame(game, new AsyncCallback<Integer>(){

    			@Override
    			public void onFailure(Throwable caught) {
    				GWT.log(caught.getMessage());
    				
    			}

				@Override
				public void onSuccess(Integer result) {
					GWT.log("SUCCESSFULLY RETURNED GAME ID");
					GWT.log("THIS IS THE RESULT " + result);
					
				}
        		
        	});
            dashboardPage.go();
            
            //gameCreated.fire(new GameCreated(new Game(title.getValue(), date.getDate().toString(), locationInfo.getValue(), fieldType.getValue())));
        });


    }



    @Override
    public void onPageShown() {
        super.onPageShown();

    }

    
    private void buildSelectionEvent() {
        btnDropdown4.add(dropdown4);
        btnDropdown4.setText("Dropdown");
        btnDropdown4.setIconPosition(IconPosition.RIGHT);
        btnDropdown4.setIconType(IconType.ARROW_DROP_DOWN);
        
        MaterialLink link = new MaterialLink("Turf");
        dropdown4.add(link);
        
        dropdown4.addSelectionHandler(selectionEvent -> {
            MaterialToast.fireToast(((MaterialLink) selectionEvent.getSelectedItem()).getText());
            selectedFieldType = ((MaterialLink)selectionEvent.getSelectedItem()).getText();
            btnDropdown4.setText(selectedFieldType);
        });
    }
    
    private int whatFieldType(){
    	if(selectedFieldType.contentEquals("Turf")){
    		return 1;
    	}
    	else{
    		return 0;
    	}
    }

}
