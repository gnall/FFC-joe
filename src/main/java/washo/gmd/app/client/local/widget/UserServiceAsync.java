package washo.gmd.app.client.local.widget;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {
	void isAdmin(String id, AsyncCallback<Boolean> asyncCallback);
}
