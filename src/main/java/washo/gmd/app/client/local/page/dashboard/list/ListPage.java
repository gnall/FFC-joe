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
package washo.gmd.app.client.local.page.dashboard.list;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;

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


import com.google.gwt.user.client.ui.Composite;

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialRow;

import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import washo.gmd.app.client.local.page.dashboard.list.cards.EventCard;
import washo.gmd.app.client.local.page.dashboard.list.cards.GameCard;
import washo.gmd.app.client.model.DataHelper;
import washo.gmd.app.client.model.EventDTO;
import washo.gmd.app.shared.Game;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@Templated
public class ListPage extends Composite {

    @Inject
    @DataField
    MaterialRow rowEvents;

    /*@Inject
    Instance<EventCard> eventCardProvider;*/
    
    @Inject
    Instance<GameCard> gameCardProvider;
    
    
    CreateGameServiceAsync createGameService;
    
    @PostConstruct
    public void init() {
    	this.createGameService = GWT.create(CreateGameService.class);
    	try {
			getGameList();
		} catch (GetGameServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    public void getGameList() throws GetGameServiceException{
    	this.createGameService.selectRecordsFromDbGameTable(new AsyncCallback<List<Game>>(){
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Failed to get DB stuff");
				GWT.log(caught.getMessage());
				
			}

			@Override
			public void onSuccess(List<Game> result) {
				for(Game game: result){
					GWT.log("FB ID " );
					GWT.log("This is the game id "+game.getGameID());
				    buildGameCard(game);
				        
				}
				
			}});
    }
    
    public void buildGameCard(Game game) {
        GameCard gameCard = gameCardProvider.get();
        gameCard.build(game);
        rowEvents.add(gameCard);
    }
    
    @SuppressWarnings("serial")
	public static class GetGameServiceException extends Exception implements Serializable 
	{
		public GetGameServiceException() 
		{
			super();
		}

		public GetGameServiceException(String message)
		{
	  	    super(message);
		}
	}
}
