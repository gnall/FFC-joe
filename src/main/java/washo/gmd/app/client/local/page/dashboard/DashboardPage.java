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
package washo.gmd.app.client.local.page.dashboard;

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


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.*;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import washo.gmd.app.client.helper.OAuthManager;
import washo.gmd.app.client.local.events.UserSignin;
import washo.gmd.app.client.local.page.base.HasNavigation;
import washo.gmd.app.client.local.page.base.Page;
import washo.gmd.app.client.local.page.dashboard.list.ListPage;
import washo.gmd.app.client.local.page.dashboard.list.ListPage.GetGameServiceException;
import washo.gmd.app.client.local.page.dashboard.map.MapPage;
import washo.gmd.app.client.local.events.EventCreated;
import washo.gmd.app.client.local.events.GameCreated;
import washo.gmd.app.client.local.events.UpdateNavBarContent;
import washo.gmd.app.client.model.User;
import washo.gmd.app.client.services.AuthenticationService;
import washo.gmd.app.client.services.AuthenticationServiceAsync;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@Templated
@ApplicationScoped
@org.jboss.errai.ui.nav.client.local.Page(path = "dashboard")
public class DashboardPage extends Page implements HasNavigation {


    @Inject
    MaterialTab tabs;

    @Inject
    @DataField
    MaterialRow rowEvents;

    @Inject
    Event<UpdateNavBarContent> navBarContentEvent;

    @Inject
    @DataField
    MaterialPanel tabContent;

    @Inject
    Instance<ListPage> listPageProvider;

    private AuthenticationServiceAsync authService = GWT.create(AuthenticationService.class);
    private ListPage listPage;
    private MapPage mapPage;

    @Inject
    Instance<MapPage> mapPageProvider;

    @Inject
    Event<UserSignin> userSigninEvent;

    @Override
    public void onPostConstruct() {
        super.onPostConstruct();

        setId("dashboard-page");

        listPage = listPageProvider.get();
        tabContent.add(listPage.asWidget());

        mapPage = mapPageProvider.get();
        tabContent.add(mapPage.asWidget());

        tabs.setBackgroundColor(Color.DEEP_PURPLE);
        MaterialLink list = new MaterialLink("List");
        list.setHref("#list");
        MaterialTabItem listTabItem = new MaterialTabItem();
        listTabItem.add(list);


        MaterialLink map = new MaterialLink("Map");
        map.setHref("#map");
        MaterialTabItem mapTabItem = new MaterialTabItem();
        mapTabItem.add(map);

        tabs.add(listTabItem);
        tabs.add(mapTabItem);


        OAuthManager manager = new OAuthManager();
        MaterialLoader.showLoading(true);
        authService.getRequest(manager.getRequest(), new AsyncCallback<User>() {
            @Override
            public void onFailure(Throwable throwable) {
                MaterialToast.fireToast(throwable.getMessage());
                MaterialLoader.showLoading(false);
            }

            @Override
            public void onSuccess(User user) {
                userSigninEvent.fire(new UserSignin(user));
                MaterialLoader.showLoading(false);

                MaterialToast.fireToast("Welcome " + user.getName() + "");
            }
        });
    }



    @Override
    public void onPageShown() {
        super.onPageShown();

        navBarContentEvent.fire(new UpdateNavBarContent(tabs));
    }

    
    public void onGameCreated(@Observes GameCreated gameCreated) {
        listPage.buildGameCard(gameCreated.getGame());
    }


}
