package washo.gmd.app.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import washo.gmd.app.client.local.page.dashboard.list.CreateGameService;
import washo.gmd.app.client.local.page.dashboard.list.ListPage.GetGameServiceException;
import washo.gmd.app.shared.Game;

public class CreateGameServiceImpl extends RemoteServiceServlet implements CreateGameService {
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/ffc";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "password";
	
	private static final Logger log = Logger.getLogger(CreateGameServiceImpl.class.getName());

	public List<Game> selectRecordsFromDbGameTable() throws GetGameServiceException {

		Connection dbConnection = null;
		Statement statement = null;

		List<Game> result = new ArrayList<Game>();
		
		String selectTableSQL = "SELECT gameId, gameOwner, title, date, time, coordinates, locationInfo, fieldType from Game";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(selectTableSQL);

			// execute select SQL statement
			ResultSet rs = statement.executeQuery(selectTableSQL);

			while (rs.next()) {
				Game game = new Game();
				game.setGameID(rs.getInt("gameId"));
				game.setGameOwner(rs.getInt("gameOwner"));
				game.setTitle(rs.getString("title"));
				game.setDate(rs.getLong("date"));
				game.setTime(rs.getString("time"));
				game.setCoordinates(rs.getString("coordinates"));
				game.setLocationInfo(rs.getString("locationInfo"));
				game.setFieldType(rs.getInt("fieldType"));
				log.warning("This is the game id "+rs.getInt("gameId"));
				result.add(game);
			}

		} catch (SQLException e) {
			log.info(e.getMessage());
			System.out.println(e.getMessage());

		} /*finally {

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.warning(e.getMessage());
					e.printStackTrace();
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.warning(e.getMessage());
					e.printStackTrace();
				}
			}
		 
		}*/
		try {
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}try {
			dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
		return result;

	}

	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {
			log.warning(e.getMessage());
			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
					DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {
			log.warning(e.getMessage());
			System.out.println(e.getMessage());

		}

		return dbConnection;

	}

	@Override
	public String returnString() {
		String result = "Test";
		return result;
	}

	@Override
	public Integer createGame(Game game) {
		log.info("GOT INTO CREATE GAME DB METHOD");
		Integer gameId = -1;
		
		Connection dbConnection = null;
		Statement statement = null;

		String insertTableSQL = "INSERT INTO Game"
				+ "(gameOwner, title, coordinates, locationInfo, fieldType ) " + "VALUES"
				+ "('"+game.getGameOwner()+"','"+game.getTitle()+"','"+game.getCoordinates()+"','"+ game.getLocationInfo() + "','" + game.getFieldType()+"')";
		/*
		 * "INSERT INTO Game"
				+ "(gameOwner, title, coordinates, locationInfo, fieldType ) " + "VALUES"
				+ "(1,'mkyong','system', " + "to_date('"
				+ getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))";
		 */
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(insertTableSQL);

			// execute insert SQL stetement
			statement.executeUpdate(insertTableSQL);

			System.out.println("Record is inserted into DBUSER table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}
		try {
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}try {
			dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
		
		return gameId;
	}

	@Override
	public Integer getUserByFbId(String fBid) {
		Connection dbConnection = null;
		Statement statement = null;

		Integer result = -1;
		
		String selectTableSQL = "SELECT userID FROM UserProfile WHERE facebookID = " + fBid;
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(selectTableSQL);

			// execute select SQL statement
			ResultSet rs = statement.executeQuery(selectTableSQL);

			while (rs.next()) {
				result = rs.getInt("userID");
				log.warning("This is the user id "+result);
			}

		} catch (SQLException e) {
			log.info(e.getMessage());
			System.out.println(e.getMessage());

		}
		
		try {
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}try {
			dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
		return result;
	}

	
	
}
