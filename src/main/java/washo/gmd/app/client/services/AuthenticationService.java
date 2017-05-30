package washo.gmd.app.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import washo.gmd.app.client.model.User;

/**
 * Created by kevzl on 5/16/2017.
 */
@RemoteServiceRelativePath("authentication")
public interface AuthenticationService extends RemoteService {

    String authenticate(String apiKey, String secretKey);

    User getRequest(String code);
}
