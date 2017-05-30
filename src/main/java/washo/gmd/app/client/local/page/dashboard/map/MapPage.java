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
package washo.gmd.app.client.local.page.dashboard.map;

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


import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import gwt.material.design.client.ui.MaterialPanel;
import ol.*;
import ol.control.ScaleLine;
import ol.geom.Point;
import ol.layer.Base;
import ol.layer.LayerOptions;
import ol.layer.Tile;
import ol.layer.VectorLayerOptions;
import ol.source.Osm;
import ol.source.Vector;
import ol.source.VectorOptions;
import ol.source.XyzOptions;
import ol.style.Icon;
import ol.style.IconOptions;
import ol.style.Style;
import ol.style.StyleOptions;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import javax.inject.Inject;

@Templated
public class MapPage extends Composite {

    @Inject
    @DataField
    MaterialPanel mapContainer;

    @Override
    protected void onLoad() {
        super.onLoad();

        final String ID = DOM.createUniqueId();
        mapContainer.setId(ID);
        // create a point
        Coordinate coordinate1 = OLFactory.createCoordinate(4e6, 2e6);
        Point point1 = new Point(coordinate1);

        // create feature
        FeatureOptions featureOptions = OLFactory.createOptions();
        featureOptions.setGeometry(point1);
        Feature feature = new Feature(featureOptions);

        Collection<Feature> features = new Collection<Feature>();
        features.push(feature);

        // create source
        VectorOptions vectorSourceOptions = OLFactory.createOptions();
        vectorSourceOptions.setFeatures(OLFactory.<Feature[], Collection<Feature>> createObject2(features));
        Vector vectorSource = new Vector(vectorSourceOptions);

        // create style
        StyleOptions styleOptions = new StyleOptions();
        IconOptions iconOptions = new IconOptions();
        iconOptions.setSrc("https://openlayers.org/en/v3.20.1/examples/data/icon.png");
        Icon icon = new Icon(iconOptions);
        styleOptions.setImage(icon);
        Style style = new Style(styleOptions);

        VectorLayerOptions vectorLayerOptions = OLFactory.createOptions();
        vectorLayerOptions.setSource(vectorSource);
        vectorLayerOptions.setStyle(style);

        ol.layer.Vector vectorLayer = new ol.layer.Vector(vectorLayerOptions);

        // create a OSM-layer
        XyzOptions osmSourceOptions = OLFactory.createOptions();
        Osm osmSource = new Osm(osmSourceOptions);

        LayerOptions osmLayerOptions = OLFactory.createOptions();
        osmLayerOptions.setSource(osmSource);

        Tile osmLayer = new Tile(osmLayerOptions);

        // create a view
        View view = new View();
        Coordinate centerCoordinate = OLFactory.createCoordinate(0, 0);
        view.setCenter(centerCoordinate);
        view.setZoom(4);

        // create the map
        MapOptions mapOptions = new MapOptions();
        mapOptions.setTarget(ID);
        mapOptions.setView(view);
        Collection<Base> lstLayer = new Collection<Base>();
        lstLayer.push(osmLayer);
        lstLayer.push(vectorLayer);
        mapOptions.setLayers(lstLayer);
        Map map = new Map(mapOptions);

        // add some controls
        map.addControl(new ScaleLine());
    }
}
