package th.co.ipassion.bkkegp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHelper {

	private String url;
	private String username;
	private String password;
	private static ConnectionHelper instance;

	private ConnectionHelper() {
		try {
			Properties prop = getProperties("config.properties");
			Class.forName(prop.getProperty("db.driver"));
			url = prop.getProperty("db.url")+":"+prop.getProperty("db.port")+'/'+prop.getProperty("db.schema");
			username = prop.getProperty("db.username");
			password = prop.getProperty("db.password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("finally")
	private Properties getProperties(String propFileName) throws IOException {
		Properties prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream(propFileName));
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			return prop;
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
