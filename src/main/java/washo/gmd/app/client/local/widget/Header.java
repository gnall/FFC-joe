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

import com.google.gwt.user.client.ui.Composite;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.ui.*;
import org.jboss.errai.ui.nav.client.local.TransitionTo;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import washo.gmd.app.client.local.events.UpdateNavBarContent;
import washo.gmd.app.client.local.events.UserSignin;
import washo.gmd.app.client.local.page.login.LoginPage;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Templated
public class Header extends Composite {

    @Inject
    @DataField
    MaterialNavBar navBar;

    @Inject
    MaterialNavContent content;

    @Inject
    MaterialNavSection section;

    @Inject
    MaterialImage image;

    @Inject
    MaterialDropDown dropDown;

    @Inject
    MaterialLink logout;

    @Inject
    TransitionTo<LoginPage> loginPage;

    @PostConstruct
    protected void init() {
        navBar.setActivates("sideNav");
        navBar.add(new MaterialNavBrand("Washo"));

        navBar.add(section);
        section.setPosition(Position.RIGHT);

        section.setId("language-selection");
        navBar.add(content);

        buildLanguageSelection();

        logout.setIconType(IconType.EXIT_TO_APP);
        logout.addClickHandler(clickEvent -> loginPage.go());
        section.add(logout);
    }

    protected void buildLanguageSelection() {
        section.clear();
        final String englishImage = "https://cdn1.iconfinder.com/data/icons/world-flags-circular/1000/Flag_of_United_Kingdom_-_Circle-128.png";
        final String frenchImage = "https://cdn1.iconfinder.com/data/icons/european-country-flags/83/france-512.png";

        MaterialImage image = new MaterialImage(englishImage);
        section.add(image);

        image.add(dropDown);

        MaterialLink english = new MaterialLink("English");
        dropDown.add(english);

        MaterialLink french = new MaterialLink("French");
        dropDown.add(french);

        dropDown.addSelectionHandler(selectionEvent -> {
            if (selectionEvent.getSelectedItem() == english) {
                image.setUrl(englishImage);
            } else {
                image.setUrl(frenchImage);
            }
        });
    }

    public void onUpdateNavBarContent(@Observes UpdateNavBarContent event) {
        content.clear();
        if (event.getWidget() != null) {
            content.add(event.getWidget());
        }
    }
}
