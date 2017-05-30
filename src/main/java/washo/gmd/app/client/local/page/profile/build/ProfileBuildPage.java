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
package washo.gmd.app.client.local.page.profile.build;

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

import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialAnchorButton;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;
import washo.gmd.app.client.local.page.base.Page;
import washo.gmd.app.client.local.page.dashboard.DashboardPage;

import org.jboss.errai.ui.nav.client.local.TransitionTo;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import com.google.gwt.core.shared.GWT;

import javax.inject.Inject;

@Templated
@org.jboss.errai.ui.nav.client.local.Page(path = "sign_up_t")
public class ProfileBuildPage extends Page {

	@Inject
	@DataField
	MaterialLabel title, description;

	@Inject
	@DataField
	MaterialTextBox name, password, confirmPassword, zipCode, gender;

	@Inject
	@DataField
	MaterialAnchorButton validate, back;

	@Inject
	TransitionTo<DashboardPage> dashboardPage;

	@Override
	public void onPostConstruct() {
		super.onPostConstruct();

		setId("sign-up");

		title.setText("Sign Up");
		description.setText("Build now your profile");

		name.setLabel("Name");
		name.setPlaceholder("e.g John Doe");
		password.setLabel("Password");
		password.setPlaceholder("Password");
		confirmPassword.setLabel("Confirm Password");
		confirmPassword.setPlaceholder("Confirm Password");
		zipCode.setLabel("Zip Code");
		zipCode.setPlaceholder("e.g 18505");
		gender.setLabel("Gender");
		gender.setPlaceholder("e.g Male");

		validate.setText("Validate");
		validate.setWaves(WavesType.DEFAULT);
		validate.setMargin(12);
		validate.setBackgroundColor(Color.DEEP_PURPLE);
		validate.setWidth("80%");

		validate.addClickHandler(clickEvent -> {
			if (!isValidForm()) {
				MaterialToast.fireToast("Not Valid");
				return;
			} else {
				MaterialToast.fireToast("TODO Save to Database");

				testFireBase("test","test",new CreateAccountCallback() {

					@Override
					public void onSuccess(String imageData) {
						GWT.log("Successfully got callback");
					}

					@Override
					public void onFailure(String message) {
						GWT.log("Failed to get callback");
					}
				});
				dashboardPage.go();
			}
		});

		back.setText("Sign In");
		back.setWaves(WavesType.DEFAULT);
		back.setType(ButtonType.FLAT);
		back.setWidth("80%");

		back.setHref("#login");
	}

	private boolean isValidForm() {
		// Name Field Validation
		if (name.getValue().length() < 5) {
			name.setError("Username must be longer than 5 characters");
			return false;
		} else {
			name.setError("");
			name.removeErrorModifiers();
		}
		// Password Field Validation
		if (password.getValue().length() < 5) {
			password.setError("Password must be longer than 5 characters");
			return false;
		} else {
			password.setError("");
			password.removeErrorModifiers();
		}
		// Password/Confirm Password Field Validation
		if (!password.getValue().equals(confirmPassword.getValue())) {

			password.setError("Passwords need to match");
			confirmPassword.setError("Passwords need to match");
			return false;
		} else {
			password.setError("");
			password.removeErrorModifiers();
			confirmPassword.setError("");
			confirmPassword.removeErrorModifiers();
		}
		// Zipcode Field Validation
		if (zipCode.getValue().contentEquals("")) {
			zipCode.setError("Zip Code cannot be empty");
		} else {
			zipCode.setError("");
			zipCode.removeErrorModifiers();
		}
		// Gender Field Validation
		if (gender.getValue().contentEquals("")) {
			gender.setError("Gender cannot be empty");
		} else {
			gender.setError("");
			gender.removeErrorModifiers();
		}
		return true;
	}

	private native void testFireBase(String email, String password, CreateAccountCallback createAccountCallback) /*-{
		console.log("IN FIRE BASE METHOD");
    	var config = {
    		apiKey: "AIzaSyBTnJ7T7wO5Pz2bl-iLL7fo1BbSw-Z3na4",
    		authDomain: "ffc-test.firebaseapp.com",
    		databaseURL: "https://ffc-test.firebaseio.com",
    		projectId: "ffc-test",
    		storageBucket: "ffc-test.appspot.com",
    		messagingSenderId: "914301343729"
  		};
  		$wnd.firebase.initializeApp(config);
    	
    	 
		console.log("AFTER INITIALIZE");
    	// Get a reference to the database service
    
    	var x = $wnd.firebase.auth().createUserWithEmailAndPassword(email, password);
    	console.log(x);
////    	function onSuccess(imageData) {
////    		accountCreateCallback.@washo.gmd.client.app.client.local.page.profile.build.ProfileBuildPage.CreateAccountCallback::onSuccess(Ljava/lang/String;)(imageData);
////    	}
////    	
////    	function onFail(message) {
////    		accountCreateCallback.@washo.gmd.client.app.client.local.page.profile.build.ProfileBuildPage.CreateAccountCallback::onFailure(Ljava/lang/String;)(message);
////    	}
//    
    }-*/;

	public interface CreateAccountCallback {
		void onSuccess(String imageData);

		void onFailure(String message);
	}
}
