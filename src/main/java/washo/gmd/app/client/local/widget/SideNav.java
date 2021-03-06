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
package washo.gmd.app.client.local.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.inject.Inject;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.*;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import washo.gmd.app.client.local.AppConstants;
import washo.gmd.app.client.local.events.UserSignin;
import washo.gmd.app.client.local.page.dashboard.list.CreateGameService;
import washo.gmd.app.client.local.page.dashboard.list.CreateGameServiceAsync;
import washo.gmd.app.client.local.page.dashboard.list.ListPage.GetGameServiceException;
import washo.gmd.app.shared.Game;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;

@Templated
public class SideNav extends Composite {

    @Inject
    @DataField
    MaterialSideNavPush sideNav;

    @Inject
    MaterialSideNavContent content;

    @Inject
    MaterialImage image;

    @Inject
    MaterialLabel name;

    @Inject
    MaterialLabel id;
    
    UserServiceAsync userService;

    @PostConstruct
    protected void init() throws GetGameServiceException {
    	Boolean adminVal = false;
    	this.userService = GWT.create(UserService.class);
    	adminVal = isUserAdmin();
    	
    	
    	
        sideNav.setId("sideNav");
        sideNav.setWidth(280);
        sideNav.reinitialize();

        MaterialLink option1 = new MaterialLink("Dashboard");
        option1.setIconType(IconType.LOCATION_ON);
        option1.setIconColor(Color.RED);
        option1.setHref(AppConstants.DASHBOARD_LINK);

        MaterialLink option2 = new MaterialLink("Create Event");
        option2.setIconType(IconType.EVENT);
        option2.setIconColor(Color.BLUE);
        option2.setHref(AppConstants.EVENT_CREATION_LINK);
        
        MaterialLink option3 = new MaterialLink("Stripe");
        option3.setIconType(IconType.PAYMENT);
        option3.setHref(AppConstants.STRIPE_LINK);

        sideNav.add(content);
        image.setUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQx2J39CUrKvnTJnfMpvj4tn-uFeIgmO03g-R4oi2GcrnAE5vr");
        content.add(image);
        
        content.add(name);
        content.add(id);

        sideNav.add(option1);
        sideNav.add(option2);
        
        
    }

    public void onUserSignIn(@Observes UserSignin event) {
        name.setText(event.getUser().getName());
        id.setText(event.getUser().getId());
    }
    
    private Boolean isUserAdmin(){
    	Boolean x = false;
    	userService.isAdmin("1", new AsyncCallback<Boolean>(){

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("isUserAdmin fail");
				
			}

			@Override
			public void onSuccess(Boolean result) {
				GWT.log("isUserAdmin success " + result);
				if(result == true){
					addCreateGameLink();
				}
			}
     });
    	GWT.log("THIS IS THE X RETURNED " + x);
		return x;
    }
    
    private void addCreateGameLink(){
    	MaterialLink option3 = new MaterialLink("Stripe");
        option3.setIconType(IconType.PAYMENT);
        option3.setHref(AppConstants.STRIPE_LINK);
        sideNav.add(option3);
    }
}
