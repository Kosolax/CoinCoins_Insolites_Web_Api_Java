package api.dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import api.entity.PictureUserEntity;
import api.idataaccess.IPictureUserDataAccess;
import api.utils.CloseRessourceHelper;
import api.utils.Constants;

public class PictureUserDataAccess implements IPictureUserDataAccess {

	@Override
	public PictureUserEntity create(PictureUserEntity pictureUserEntity) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
		PictureUserEntity pictureUserEntityFromDatabase = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		if(connection != null) {
			try {
				String query = 
						"INSERT INTO " + 
						Constants.PICTURE_USER_TABLE_NAME +
						" (" + Constants.PICTURE_USER_COLUMN_NAME_LATITUDE + ", " +
						Constants.PICTURE_USER_COLUMN_NAME_LONGITUDE + ", " +
						Constants.PICTURE_USER_COLUMN_NAME_SVG_LINK + ", " +
						Constants.PICTURE_USER_COLUMN_NAME_ID_USER + ")" + 
						" VALUES (?,?,?,?)";
				preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setDouble(1, pictureUserEntity.getLatitude());
				preparedStatement.setDouble(2, pictureUserEntity.getLongitude());
				preparedStatement.setString(3, pictureUserEntity.getSvgLink());
				preparedStatement.setInt(4, pictureUserEntity.getIdUser());
				preparedStatement.executeUpdate();
				resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()) {
					pictureUserEntityFromDatabase = this.findById(resultSet.getInt(1));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseRessourceHelper.sqlCloseResources(preparedStatement, resultSet);
			}
		}
		
		return pictureUserEntityFromDatabase;
	}

	@Override
	public Boolean delete(int id) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
    	PreparedStatement preparedStatement = null;
    	
		if(connection != null) {
			String query = 
					"DELETE FROM " + 
			Constants.PICTURE_USER_TABLE_NAME +
			" WHERE " +
			Constants.PICTURE_USER_COLUMN_NAME_ID + 
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
	public Boolean deleteAllFromUserId(int userId) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
    	PreparedStatement preparedStatement = null;
    	
		if(connection != null) {
			String query = 
					"DELETE FROM " + 
			Constants.PICTURE_USER_TABLE_NAME +
			" WHERE " +
			Constants.PICTURE_USER_COLUMN_NAME_ID_USER + 
			" = ?";
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, userId);
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
	public List<PictureUserEntity> findAll() {
		Connection connection = ConnectionSingleton.getConnectionInstance();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<PictureUserEntity> listPictureUserEntity = new ArrayList<PictureUserEntity>();
		
		if(connection != null) {
			try {
				String query = "SELECT * FROM " + Constants.PICTURE_USER_TABLE_NAME;
				preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					PictureUserEntity pictureUserEntity = new PictureUserEntity(
							resultSet.getInt(Constants.PICTURE_USER_COLUMN_NAME_ID),
							resultSet.getInt(Constants.PICTURE_USER_COLUMN_NAME_ID_USER),
							resultSet.getDouble(Constants.PICTURE_USER_COLUMN_NAME_LATITUDE),
							resultSet.getDouble(Constants.PICTURE_USER_COLUMN_NAME_LONGITUDE),
							resultSet.getString(Constants.PICTURE_USER_COLUMN_NAME_SVG_LINK)
					);
					listPictureUserEntity.add(pictureUserEntity);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseRessourceHelper.sqlCloseResources(preparedStatement, resultSet);
			}
		}
		
		return listPictureUserEntity;
	}

	@Override
	public List<PictureUserEntity> findAllPictureByUserId(int userId) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<PictureUserEntity> listPictureUserEntity = new ArrayList<PictureUserEntity>();
		
		if(connection != null) {
			try {
				String query = 
						"SELECT * FROM " +
						Constants.PICTURE_USER_TABLE_NAME + 
						" WHERE " +
						Constants.PICTURE_USER_COLUMN_NAME_ID_USER +
						" = ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, userId);
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					PictureUserEntity pictureUserEntity = new PictureUserEntity(
							resultSet.getInt(Constants.PICTURE_USER_COLUMN_NAME_ID),
							resultSet.getInt(Constants.PICTURE_USER_COLUMN_NAME_ID_USER),
							resultSet.getDouble(Constants.PICTURE_USER_COLUMN_NAME_LATITUDE),
							resultSet.getDouble(Constants.PICTURE_USER_COLUMN_NAME_LONGITUDE),
							resultSet.getString(Constants.PICTURE_USER_COLUMN_NAME_SVG_LINK)
					);
					listPictureUserEntity.add(pictureUserEntity);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseRessourceHelper.sqlCloseResources(preparedStatement, resultSet);
			}
		}
		
		return listPictureUserEntity;
	}

	@Override
	public PictureUserEntity findById(Integer id) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		PictureUserEntity pictureUserEntity = null;
		
		if(connection != null) {
			try {
				String query = 
						"SELECT * FROM " +
						Constants.PICTURE_USER_TABLE_NAME +
						" WHERE " +
						Constants.PICTURE_USER_COLUMN_NAME_ID +
						"= ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					pictureUserEntity = new PictureUserEntity(
							resultSet.getInt(Constants.PICTURE_USER_COLUMN_NAME_ID),
							resultSet.getInt(Constants.PICTURE_USER_COLUMN_NAME_ID_USER),
							resultSet.getDouble(Constants.PICTURE_USER_COLUMN_NAME_LATITUDE),
							resultSet.getDouble(Constants.PICTURE_USER_COLUMN_NAME_LONGITUDE),
							resultSet.getString(Constants.PICTURE_USER_COLUMN_NAME_SVG_LINK)
					);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseRessourceHelper.sqlCloseResources(preparedStatement, resultSet);
			}
		}
		
		return pictureUserEntity;
	}

	@Override
	public PictureUserEntity update(PictureUserEntity pictureUserEntity) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
		PictureUserEntity pictureUserEntityFromDatabase = null;
		PreparedStatement preparedStatement = null;
		
		if(connection != null) {
			try {
				String query = 
						"UPDATE " + 
						Constants.PICTURE_USER_TABLE_NAME +
						" SET " + Constants.PICTURE_USER_COLUMN_NAME_LATITUDE + " = ?, " +
						Constants.PICTURE_USER_COLUMN_NAME_LONGITUDE + " = ?, " +
						Constants.PICTURE_USER_COLUMN_NAME_SVG_LINK + " = ?, " +
						Constants.PICTURE_USER_COLUMN_NAME_ID_USER + " = ?" + 
						" WHERE " +
						Constants.PICTURE_USER_COLUMN_NAME_ID + 
						" = ?";
				preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setDouble(1, pictureUserEntity.getLatitude());
				preparedStatement.setDouble(2, pictureUserEntity.getLongitude());
				preparedStatement.setString(3, pictureUserEntity.getSvgLink());
				preparedStatement.setInt(4, pictureUserEntity.getIdUser());
				preparedStatement.setInt(5, pictureUserEntity.getId());
				preparedStatement.executeUpdate();
				pictureUserEntityFromDatabase = this.findById(pictureUserEntity.getId());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseRessourceHelper.sqlCloseResources(preparedStatement, null);
			}
		}
		
		return pictureUserEntityFromDatabase;
	}
}
