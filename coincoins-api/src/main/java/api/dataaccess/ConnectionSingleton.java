package api.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;

import api.utils.Constants;

public class ConnectionSingleton {
	public final static String className = ConnectionSingleton.class.getName();
	private static Connection connection = null;

	private ConnectionSingleton() {
		try {
			Class.forName(className);
			connection = DriverManager.getConnection(Constants.DATABASE_URL + Constants.DATABASE_NAME,
					Constants.DATABASE_USER, Constants.DATABASE_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized Connection getConnectionInstance() {
		if (connection == null) {
			new ConnectionSingleton();
		}

		return connection;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
