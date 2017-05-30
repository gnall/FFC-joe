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
package washo.gmd.app.client.local;

import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.ui.MaterialToast;
import washo.gmd.app.client.local.page.base.HasNavigation;
import washo.gmd.app.client.local.events.NavigationEvent;
import washo.gmd.app.client.local.resources.AppClientBundle;
import washo.gmd.app.client.local.widget.Header;
import washo.gmd.app.client.local.widget.Main;
import washo.gmd.app.client.local.widget.SideNav;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.jboss.errai.ui.nav.client.local.Navigation;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import static gwt.material.design.jquery.client.api.JQuery.$;

@EntryPoint
public class Client extends Composite{

    @Inject
    Navigation navigation;

    @Inject
    Header header;

    @Inject
    SideNav sideNav;

    @Inject
    Main content;

    @PostConstruct
    public void init() {
        StyleInjector.inject(AppClientBundle.INSTANCE.appCss().getText());
        content.getContainer().add(navigation.getContentPanel());

        header.setVisible(false);
        sideNav.setVisible(false);

        RootPanel.get().add(content);
        RootPanel.get().add(header);
        RootPanel.get().add(sideNav);
    }

    public void onNavigation(@Observes NavigationEvent event) {
        RootPanel.get().getElement().setId(event.getPage().getId());
        if (event.getPage() instanceof HasNavigation) {
            header.setVisible(true);
            sideNav.setVisible(true);
            $("main").css("marginLeft", "280px");
        } else {
            $("main").css("marginLeft", "0px");
            header.setVisible(false);
            sideNav.setVisible(false);
        }
    }
}
