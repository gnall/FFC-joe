package washo.gmd.app.client.local.page.dashboard.list;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import washo.gmd.app.client.local.page.dashboard.list.ListPage.GetGameServiceException;
import washo.gmd.app.shared.Game;

public interface CreateGameServiceAsync 
{
	void selectRecordsFromDbGameTable(AsyncCallback<List<Game>> callback) throws GetGameServiceException;
	void returnString(AsyncCallback<String> callback);
	void createGame(Game game, AsyncCallback<Integer> asyncCallback);
	void getUserByFbId(String fBid, AsyncCallback<Integer> asyncCallback);
}