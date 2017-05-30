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
package washo.gmd.app.client.local.page.event.create;

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
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialDatePicker;
import gwt.material.design.client.ui.MaterialTextBox;
import org.jboss.errai.ui.nav.client.local.TransitionTo;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import washo.gmd.app.client.local.page.base.HasNavigation;
import washo.gmd.app.client.local.page.base.Page;
import washo.gmd.app.client.local.page.dashboard.DashboardPage;
import washo.gmd.app.client.local.events.EventCreated;
import washo.gmd.app.client.local.events.UpdateNavBarContent;
import washo.gmd.app.client.model.EventDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@Templated
@ApplicationScoped
@org.jboss.errai.ui.nav.client.local.Page(path = "event/create")
public class EventCreatePage extends Page implements HasNavigation {

    @Inject
    Event<UpdateNavBarContent> navBarContent;

    @Inject
    Event<EventCreated> eventCreated;

    @Inject
    @DataField
    MaterialDatePicker date;

    @Inject
    @DataField
    MaterialTextBox title;

    @Inject
    @DataField
    MaterialTextBox description;

    @Inject
    @DataField
    MaterialTextBox location;

    @Inject
    @DataField
    MaterialButton create;

    @Inject
    TransitionTo<DashboardPage> dashboardPage;

    @Override
    public void onPostConstruct() {
        super.onPostConstruct();

        setId("event-page");

        title.setLabel("Title");
        description.setLabel("Description");
        date.setPlaceholder("Date");
        location.setLabel("Location");

        create.setText("Create Event");
        create.setWaves(WavesType.DEFAULT);
        create.setBackgroundColor(Color.DEEP_PURPLE);
        create.setWidth("100%");

        create.addClickHandler(clickEvent -> {
            dashboardPage.go();
            eventCreated.fire(new EventCreated(new EventDTO(title.getValue(), description.getValue(), location.getValue(), date.getDate())));
        });
    }

    @Override
    public void onPageShown() {
        super.onPageShown();

        navBarContent.fire(new UpdateNavBarContent());
    }
}
