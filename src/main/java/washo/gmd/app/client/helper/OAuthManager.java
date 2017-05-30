package washo.gmd.app.client.helper;

import com.google.gwt.user.client.Window;

public class OAuthManager {


    public String getRequest() {
        String URL = Window.Location.getQueryString();

        if (URL.contains("code")) {
            int startIndex = URL.indexOf("=");
            int endIndex = URL.indexOf("&");
            URL = URL.substring(startIndex + 1, endIndex);
        }
        return URL;
    }
}
