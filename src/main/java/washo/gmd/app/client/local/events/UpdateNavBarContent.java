package washo.gmd.app.client.local.events;

import gwt.material.design.client.base.MaterialWidget;

public class UpdateNavBarContent {

    private MaterialWidget widget;

    public UpdateNavBarContent(MaterialWidget widget) {
        this.widget = widget;
    }

    public UpdateNavBarContent() {
    }

    public MaterialWidget getWidget() {
        return widget;
    }

    public void setWidget(MaterialWidget widget) {
        this.widget = widget;
    }
}
