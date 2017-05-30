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
package washo.gmd.app.client.local.page.login;

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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.ui.*;
import org.jboss.errai.ui.nav.client.local.DefaultPage;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import washo.gmd.app.client.local.AppConstants;
import washo.gmd.app.client.local.page.base.Page;
import washo.gmd.app.client.services.AuthenticationService;
import washo.gmd.app.client.services.AuthenticationServiceAsync;

import javax.inject.Inject;

@Templated
@org.jboss.errai.ui.nav.client.local.Page(path = "login", role = DefaultPage.class)
public class LoginPage extends Page {

    @Inject
    @DataField
    MaterialTextBox username, password;

    @Inject
    @DataField
    MaterialAnchorButton signIn;

    @Inject
    @DataField
    MaterialImage facebook, instagram;

    @Inject
    @DataField
    MaterialAnchorButton create;

    private AuthenticationServiceAsync authService = GWT.create(AuthenticationService.class);

    @Override
    public void onPostConstruct() {
        super.onPostConstruct();

        setId("login-page");

        username.setLabel("Username");
        password.setLabel("Password");
        password.setType(InputType.PASSWORD);

        signIn.setText("Sign In");
        signIn.setWaves(WavesType.DEFAULT);
        signIn.setHref("#profile_build");

        create.setType(ButtonType.FLOATING);
        create.setIconType(IconType.ADD);
        create.setSize(ButtonSize.LARGE);
        create.setWaves(WavesType.DEFAULT);
        create.setHref("#sign_up_t");

        facebook.addClickHandler(clickEvent -> authService.authenticate(AppConstants.FB_API_KEY, AppConstants.FB_SECRET_KEY, new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable throwable) {
                MaterialToast.fireToast(throwable.getMessage());
            }

            @Override
            public void onSuccess(String url) {
                Window.Location.replace(url);
            }
        }));
    }
}
