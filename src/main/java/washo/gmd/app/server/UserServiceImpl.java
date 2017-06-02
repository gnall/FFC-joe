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
import washo.gmd.app.client.local.widget.UserService;
import washo.gmd.app.shared.Game;

public class UserServiceImpl extends RemoteServiceServlet implements UserService {
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/ffc";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "password";
	
	private static final Logger log = Logger.getLogger(CreateGameServiceImpl.class.getName());

	public Boolean isAdmin(String userId){

		Connection dbConnection = null;
		Statement statement = null;

		boolean result = false;
		
		String selectTableSQL = "SELECT admin FROM user WHERE userID = " + userId;

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(selectTableSQL);

			// execute select SQL statement
			ResultSet rs = statement.executeQuery(selectTableSQL);

			while (rs.next()) {
				if(rs.getInt("admin") == 1){
					result = true;
				}
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
	
}
