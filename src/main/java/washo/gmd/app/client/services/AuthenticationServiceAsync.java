package washo.gmd.app.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import washo.gmd.app.client.model.User;


public interface AuthenticationServiceAsync {

    void authenticate(String apiKey, String secretKey, AsyncCallback<String> async);

    void getRequest(String code, AsyncCallback<User> async);
}
