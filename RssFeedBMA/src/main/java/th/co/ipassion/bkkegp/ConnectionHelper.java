package th.co.ipassion.bkkegp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

	private String url;
	private String username;
	private String password;
	private static ConnectionHelper instance;

	private ConnectionHelper() {
		try {
			Class.forName("org.postgresql.Driver");
			url = "jdbc:postgresql://bkkegp.c1omez2fbnnj.ap-southeast-1.rds.amazonaws.com:5432/bkkegp";
			username = "postgres";
			password = "postgres";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new ConnectionHelper();
		}
		try {
			return DriverManager.getConnection(instance.url, instance.username, instance.password);
		} catch (SQLException e) {
			throw e;
		}
	}

	public static void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
