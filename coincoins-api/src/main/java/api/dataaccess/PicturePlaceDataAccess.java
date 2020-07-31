package api.dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import api.entity.PicturePlaceEntity;
import api.idataaccess.IPicturePlaceDataAccess;
import api.utils.CloseRessourceHelper;
import api.utils.Constants;

public class PicturePlaceDataAccess implements IPicturePlaceDataAccess {

	@Override
	public PicturePlaceEntity create(PicturePlaceEntity picturePlaceEntity) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
		PicturePlaceEntity picturePlaceEntityFromDatabase = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		if(connection != null) {
			try {
				String query = 
						"INSERT INTO " + 
						Constants.PICTURE_PLACE_TABLE_NAME +
						"(" + Constants.PICTURE_PLACE_COLUMN_NAME_LATITUDE + ", " +
						Constants.PICTURE_PLACE_COLUMN_NAME_LONGITUDE + ", " +
						Constants.PICTURE_PLACE_COLUMN_NAME_SVG_LINK + ", " +
						Constants.PICTURE_PLACE_COLUMN_NAME_ID_PLACE + ")" + 
						" VALUES (?,?,?,?)";
				preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setDouble(1, picturePlaceEntity.getLatitude());
				preparedStatement.setDouble(2, picturePlaceEntity.getLongitude());
				preparedStatement.setString(3, picturePlaceEntity.getSvgLink());
				preparedStatement.setInt(4, picturePlaceEntity.getIdPlace());
				preparedStatement.executeUpdate();
				resultSet = preparedStatement.getGeneratedKeys();
				
				if(resultSet.next()) {
					picturePlaceEntityFromDatabase = this.findById(resultSet.getInt(1));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseRessourceHelper.sqlCloseResources(preparedStatement, resultSet);
			}
		}
		
		return picturePlaceEntityFromDatabase;
	}

	@Override
	public Boolean delete(int id) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
    	PreparedStatement preparedStatement = null;
    	
		if(connection != null) {
			String query = 
					"DELETE FROM " + 
					Constants.PICTURE_PLACE_TABLE_NAME +
					" WHERE " +
					Constants.PICTURE_PLACE_COLUMN_NAME_ID + 
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
	public Boolean deleteAllFromPlaceId(int placeId) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
    	PreparedStatement preparedStatement = null;
    	
		if(connection != null) {
			String query = 
					"DELETE FROM " + 
			Constants.PICTURE_PLACE_TABLE_NAME +
			" WHERE " +
			Constants.PICTURE_PLACE_COLUMN_NAME_ID_PLACE + 
			" = ?";
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, placeId);
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
	public List<PicturePlaceEntity> findAll() {
		Connection connection = ConnectionSingleton.getConnectionInstance();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<PicturePlaceEntity> listPicturePlaceEntity = new ArrayList<PicturePlaceEntity>();
		
		if(connection != null) {
			try {
				String query = "SELECT * FROM " + Constants.PICTURE_PLACE_TABLE_NAME;
				preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					PicturePlaceEntity picturePlaceEntity = new PicturePlaceEntity(
							resultSet.getInt(Constants.PICTURE_PLACE_COLUMN_NAME_ID),
							resultSet.getInt(Constants.PICTURE_PLACE_COLUMN_NAME_ID_PLACE),
							resultSet.getDouble(Constants.PICTURE_PLACE_COLUMN_NAME_LATITUDE),
							resultSet.getDouble(Constants.PICTURE_PLACE_COLUMN_NAME_LONGITUDE),
							resultSet.getString(Constants.PICTURE_PLACE_COLUMN_NAME_SVG_LINK)
					);
					listPicturePlaceEntity.add(picturePlaceEntity);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseRessourceHelper.sqlCloseResources(preparedStatement, resultSet);
			}
		}
		
		return listPicturePlaceEntity;
	}
	
	@Override
	public List<PicturePlaceEntity> findAllPictureByPlaceId(int placeId) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<PicturePlaceEntity> listPicturePlaceEntity = new ArrayList<PicturePlaceEntity>();
		
		if(connection != null) {
			try {
				String query = 
						"SELECT * FROM " +
						Constants.PICTURE_PLACE_TABLE_NAME + 
						" WHERE " +
						Constants.PICTURE_PLACE_COLUMN_NAME_ID_PLACE +
						" = ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, placeId);
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					PicturePlaceEntity picturePlaceEntity = new PicturePlaceEntity(
							resultSet.getInt(Constants.PICTURE_PLACE_COLUMN_NAME_ID),
							resultSet.getInt(Constants.PICTURE_PLACE_COLUMN_NAME_ID_PLACE),
							resultSet.getDouble(Constants.PICTURE_PLACE_COLUMN_NAME_LATITUDE),
							resultSet.getDouble(Constants.PICTURE_PLACE_COLUMN_NAME_LONGITUDE),
							resultSet.getString(Constants.PICTURE_PLACE_COLUMN_NAME_SVG_LINK)
					);
					listPicturePlaceEntity.add(picturePlaceEntity);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseRessourceHelper.sqlCloseResources(preparedStatement, resultSet);
			}
		}
		
		return listPicturePlaceEntity;
	}


	@Override
	public PicturePlaceEntity findById(Integer id) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		PicturePlaceEntity picturePlaceEntity = null;
		
		if(connection != null) {
			try {
				String query = 
						"SELECT * FROM " +
						Constants.PICTURE_PLACE_TABLE_NAME +
						" WHERE " +
						Constants.PICTURE_PLACE_COLUMN_NAME_ID +
						"= ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					picturePlaceEntity = new PicturePlaceEntity(
							resultSet.getInt(Constants.PICTURE_PLACE_COLUMN_NAME_ID),
							resultSet.getInt(Constants.PICTURE_PLACE_COLUMN_NAME_ID_PLACE),
							resultSet.getDouble(Constants.PICTURE_PLACE_COLUMN_NAME_LATITUDE),
							resultSet.getDouble(Constants.PICTURE_PLACE_COLUMN_NAME_LONGITUDE),
							resultSet.getString(Constants.PICTURE_PLACE_COLUMN_NAME_SVG_LINK)
					);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseRessourceHelper.sqlCloseResources(preparedStatement, resultSet);
			}
		}
		
		return picturePlaceEntity;
	}

	@Override
	public PicturePlaceEntity update(PicturePlaceEntity picturePlaceEntity) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
		PicturePlaceEntity picturePlaceEntityFromDatabase = null;
		PreparedStatement preparedStatement = null;
		
		if(connection != null) {
			try {
				String query = 
						"UPDATE " + 
						Constants.PICTURE_PLACE_TABLE_NAME +
						" SET " + Constants.PICTURE_PLACE_COLUMN_NAME_LATITUDE + " = ?, " +
						Constants.PICTURE_PLACE_COLUMN_NAME_LONGITUDE + " = ?, " +
						Constants.PICTURE_PLACE_COLUMN_NAME_SVG_LINK + " = ?, " +
						Constants.PICTURE_PLACE_COLUMN_NAME_ID_PLACE + " = ?" + 
						" WHERE " +
						Constants.PICTURE_PLACE_COLUMN_NAME_ID + 
						" = ?";
				preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setDouble(1, picturePlaceEntity.getLatitude());
				preparedStatement.setDouble(2, picturePlaceEntity.getLongitude());
				preparedStatement.setString(3, picturePlaceEntity.getSvgLink());
				preparedStatement.setInt(4, picturePlaceEntity.getIdPlace());
				preparedStatement.setInt(5, picturePlaceEntity.getId());
				preparedStatement.executeUpdate();
				picturePlaceEntityFromDatabase = this.findById(picturePlaceEntity.getId());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseRessourceHelper.sqlCloseResources(preparedStatement, null);
			}
		}
		
		return picturePlaceEntityFromDatabase;
	}
}
