package api.dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import api.entity.UserEntity;
import api.idataaccess.IUserDataAccess;
import api.utils.CloseRessourceHelper;
import api.utils.Constants;

public class UserDataAccess implements IUserDataAccess {

	@Override
	public UserEntity create(UserEntity userEntity) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
		UserEntity userEntityFromDatabase = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		if(connection != null) {
			try {
				String query = 
						"INSERT INTO " + 
						Constants.USER_TABLE_NAME +
						" (" + Constants.USER_COLUMN_NAME_MAIL + ", " +
						Constants.USER_COLUMN_NAME_PASSWORD + ", " + 
						Constants.USER_COLUMN_NAME_PSEUDONYM + ")" + 
						" VALUES (?,?,?)";
				preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, userEntity.getMail());
				preparedStatement.setString(2, userEntity.getPassword());
				preparedStatement.setString(3, userEntity.getPseudonym());
				preparedStatement.executeUpdate();
				resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()) {
					userEntityFromDatabase = this.findById(resultSet.getInt(1));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseRessourceHelper.sqlCloseResources(preparedStatement, resultSet);
			}
		}
		
		return userEntityFromDatabase;
	}

	@Override
	public Boolean delete(int id) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
    	PreparedStatement preparedStatement = null;
    	
		if(connection != null) {
			String query = 
					"DELETE FROM " + 
			Constants.USER_TABLE_NAME +
			" WHERE " +
			Constants.USER_COLUMN_NAME_ID + 
			" = ?";
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);
				preparedStatement.execute();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseRessourceHelper.sqlCloseResources(preparedStatement, null);
			}
		}
		
		return false;
	}

	@Override
	public List<UserEntity> findAll() {
		Connection connection = ConnectionSingleton.getConnectionInstance();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<UserEntity> listUserEntity = new ArrayList<UserEntity>();
		
		if(connection != null) {
			try {
				String query = "SELECT * FROM " + Constants.USER_TABLE_NAME;
				preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					UserEntity userEntity = new UserEntity(
							resultSet.getInt(Constants.USER_COLUMN_NAME_ID),
							resultSet.getString(Constants.USER_COLUMN_NAME_MAIL), 
							resultSet.getString(Constants.USER_COLUMN_NAME_PASSWORD),
							resultSet.getString(Constants.USER_COLUMN_NAME_PSEUDONYM)
					);
					listUserEntity.add(userEntity);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseRessourceHelper.sqlCloseResources(preparedStatement, resultSet);
			}
		}
		
		return listUserEntity;
	}

	@Override
	public UserEntity findById(Integer id) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		UserEntity userEntity = null;
		
		if(connection != null) {
			try {
				String query = 
						"SELECT * FROM " +
						Constants.USER_TABLE_NAME +
						" WHERE " +
						Constants.USER_COLUMN_NAME_ID +
						"= ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					userEntity = new UserEntity(
							resultSet.getInt(Constants.USER_COLUMN_NAME_ID),
							resultSet.getString(Constants.USER_COLUMN_NAME_MAIL), 
							resultSet.getString(Constants.USER_COLUMN_NAME_PASSWORD),
							resultSet.getString(Constants.USER_COLUMN_NAME_PSEUDONYM)
					);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseRessourceHelper.sqlCloseResources(preparedStatement, resultSet);
			}
		}
		
		return userEntity;
	}

	@Override
	public UserEntity findByMailAndPassword(UserEntity userEntity) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		UserEntity userEntityFromDatabase = null;
		
		if(connection != null) {
			try {
				String query = 
						"SELECT * FROM " +
						Constants.USER_TABLE_NAME +
						" WHERE " +
						Constants.USER_COLUMN_NAME_MAIL +
						"= ? AND " + 
						Constants.USER_COLUMN_NAME_PASSWORD +
						"= ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, userEntity.getMail());
				preparedStatement.setString(2, userEntity.getPassword());
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					userEntityFromDatabase = new UserEntity(
							resultSet.getInt(Constants.USER_COLUMN_NAME_ID),
							resultSet.getString(Constants.USER_COLUMN_NAME_MAIL), 
							resultSet.getString(Constants.USER_COLUMN_NAME_PASSWORD),
							resultSet.getString(Constants.USER_COLUMN_NAME_PSEUDONYM)
					);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseRessourceHelper.sqlCloseResources(preparedStatement, resultSet);
			}
		}
		
		return userEntityFromDatabase;
	}

	@Override
	public UserEntity update(UserEntity userEntity) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
		UserEntity userEntityFromDatabase = null;
		PreparedStatement preparedStatement = null;
		
		if(connection != null) {
			try {
				String query =
						"UPDATE " + 
						Constants.USER_TABLE_NAME +
						" SET " + Constants.USER_COLUMN_NAME_MAIL + " = ?, " +
						Constants.USER_COLUMN_NAME_PASSWORD + " = ?," + 
						Constants.USER_COLUMN_NAME_PSEUDONYM + " = ?" + 
						" WHERE " +
						Constants.USER_COLUMN_NAME_ID +
						" = ?";
				preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, userEntity.getMail());
				preparedStatement.setString(2, userEntity.getPassword());
				preparedStatement.setString(3, userEntity.getPseudonym());
				preparedStatement.setInt(4, userEntity.getId());
				preparedStatement.executeUpdate();
				userEntityFromDatabase = this.findById(userEntity.getId());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseRessourceHelper.sqlCloseResources(preparedStatement, null);
			}
		}
		
		return userEntityFromDatabase;
	}
}
