package washo.gmd.app.client.local.page.dashboard.list;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import washo.gmd.app.client.local.page.dashboard.list.ListPage.GetGameServiceException;
import washo.gmd.app.shared.Game;

@RemoteServiceRelativePath("creategame")
public interface CreateGameService extends RemoteService{
	public List<Game> selectRecordsFromDbGameTable() throws GetGameServiceException;
	public String returnString();
	public Integer createGame(Game game);
	public Integer getUserByFbId(String fBid);
}
