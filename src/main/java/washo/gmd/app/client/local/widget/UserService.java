package washo.gmd.app.client.local.widget;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("usercheck")
public interface UserService extends RemoteService{
	public Boolean isAdmin(String id);
}
