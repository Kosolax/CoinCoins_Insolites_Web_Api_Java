package api.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CloseRessourceHelper {
	public static void sqlCloseResources(PreparedStatement stm, ResultSet result) {
		if(stm != null && result != null) {
			try {
				stm.close();
				result.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (stm != null && result == null) {
			try {
				stm.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (stm == null && result != null) {
			try {
				result.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void connectionCloseResources(Connection connection) {
		if(connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
