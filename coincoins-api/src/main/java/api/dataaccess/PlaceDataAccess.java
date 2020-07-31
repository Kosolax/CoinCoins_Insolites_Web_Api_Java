package api.dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import api.entity.PlaceEntity;
import api.idataaccess.IPlaceDataAccess;
import api.utils.CloseRessourceHelper;
import api.utils.Constants;

public class PlaceDataAccess implements IPlaceDataAccess {

	@Override
	public PlaceEntity create(PlaceEntity placeEntity) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
		PlaceEntity placeEntityFromDatabase = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		if(connection != null) {
			try {
				String query = 
						"INSERT INTO " + 
						Constants.PLACE_TABLE_NAME +
						" (" + Constants.PLACE_COLUMN_NAME_DESCRIPTION + ", " +
						Constants.PLACE_COLUMN_NAME_LATITUDE + ", " +
						Constants.PLACE_COLUMN_NAME_LONGITUDE + ", " +
						Constants.PLACE_COLUMN_NAME_TITLE + ")" + 
						" VALUES (?,?,?,?)";
				preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, placeEntity.getDescription());
				preparedStatement.setDouble(2, placeEntity.getLatitude());
				preparedStatement.setDouble(3, placeEntity.getLongitude());
				preparedStatement.setString(4, placeEntity.getTitle());
				preparedStatement.executeUpdate();
				resultSet = preparedStatement.getGeneratedKeys();
				
				if(resultSet.next()) {
					placeEntityFromDatabase = this.findById(resultSet.getInt(1));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseRessourceHelper.sqlCloseResources(preparedStatement, resultSet);
			}
		}
		
		return placeEntityFromDatabase;
	}

	@Override
	public Boolean delete(int id) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
    	PreparedStatement preparedStatement = null;
    	
		if(connection != null) {
			String query = 
					"DELETE FROM " + 
			Constants.PLACE_TABLE_NAME +
			" WHERE " +
			Constants.PLACE_COLUMN_NAME_ID + 
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
	public List<PlaceEntity> findAll() {
		Connection connection = ConnectionSingleton.getConnectionInstance();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<PlaceEntity> listPlaceEntity = new ArrayList<PlaceEntity>();
		
		if(connection != null) {
			try {
				String query = "SELECT * FROM " + 
						Constants.PLACE_TABLE_NAME +
						" ORDER BY " + 
						Constants.PLACE_COLUMN_NAME_ID +
						" DESC ";
				preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					PlaceEntity placeEntity = new PlaceEntity(
							resultSet.getInt(Constants.PLACE_COLUMN_NAME_ID),
							resultSet.getString(Constants.PLACE_COLUMN_NAME_DESCRIPTION),
							resultSet.getDouble(Constants.PLACE_COLUMN_NAME_LATITUDE),
							resultSet.getDouble(Constants.PLACE_COLUMN_NAME_LONGITUDE),
							resultSet.getString(Constants.PLACE_COLUMN_NAME_TITLE)
					);
					listPlaceEntity.add(placeEntity);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseRessourceHelper.sqlCloseResources(preparedStatement, resultSet);
			}
		}
		
		return listPlaceEntity;
	}

	@Override
	public PlaceEntity findById(Integer id) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		PlaceEntity placeEntity = null;
		
		if(connection != null) {
			try {
				String query = 
						"SELECT * FROM " +
						Constants.PLACE_TABLE_NAME +
						" WHERE " +
						Constants.PLACE_COLUMN_NAME_ID +
						"= ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					 placeEntity = new PlaceEntity(
							resultSet.getInt(Constants.PLACE_COLUMN_NAME_ID),
							resultSet.getString(Constants.PLACE_COLUMN_NAME_DESCRIPTION),
							resultSet.getDouble(Constants.PLACE_COLUMN_NAME_LATITUDE),
							resultSet.getDouble(Constants.PLACE_COLUMN_NAME_LONGITUDE),
							resultSet.getString(Constants.PLACE_COLUMN_NAME_TITLE)
					);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseRessourceHelper.sqlCloseResources(preparedStatement, resultSet);
			}
		}
		
		return placeEntity;
	}

	@Override
	public List<PlaceEntity> findMostRecentPlaceEntity(int count) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<PlaceEntity> listPlaceEntity = new ArrayList<PlaceEntity>();
		
		if(connection != null) {
			try {
				String query = "SELECT * FROM " + 
						Constants.PLACE_TABLE_NAME +
						" ORDER BY " + 
						Constants.PLACE_COLUMN_NAME_ID +
						" DESC LIMIT ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, count);
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					PlaceEntity placeEntity = new PlaceEntity(
							resultSet.getInt(Constants.PLACE_COLUMN_NAME_ID),
							resultSet.getString(Constants.PLACE_COLUMN_NAME_DESCRIPTION),
							resultSet.getDouble(Constants.PLACE_COLUMN_NAME_LATITUDE),
							resultSet.getDouble(Constants.PLACE_COLUMN_NAME_LONGITUDE),
							resultSet.getString(Constants.PLACE_COLUMN_NAME_TITLE)
					);
					listPlaceEntity.add(placeEntity);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseRessourceHelper.sqlCloseResources(preparedStatement, resultSet);
			}
		}
		
		return listPlaceEntity;
	}

	@Override
	public PlaceEntity update(PlaceEntity placeEntity) {
		Connection connection = ConnectionSingleton.getConnectionInstance();
		PlaceEntity placeEntityFromDatabase = null;
		PreparedStatement preparedStatement = null;
		
		if(connection != null) {
			try {
				String query = 
						"UPDATE " + 
						Constants.PLACE_TABLE_NAME +
						" SET " + Constants.PLACE_COLUMN_NAME_DESCRIPTION + " = ?, " +
						Constants.PLACE_COLUMN_NAME_LATITUDE + " = ?, " +
						Constants.PLACE_COLUMN_NAME_LONGITUDE + " = ?, " +
						Constants.PLACE_COLUMN_NAME_TITLE + " = ?" + 
						" WHERE " +
						Constants.PLACE_COLUMN_NAME_ID +
						" = ?";
				preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, placeEntity.getDescription());
				preparedStatement.setDouble(2, placeEntity.getLatitude());
				preparedStatement.setDouble(3, placeEntity.getLongitude());
				preparedStatement.setString(4, placeEntity.getTitle());
				preparedStatement.setInt(5, placeEntity.getId());
				preparedStatement.executeUpdate();
				placeEntityFromDatabase = this.findById(placeEntity.getId());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseRessourceHelper.sqlCloseResources(preparedStatement, null);
			}
		}
		
		return placeEntityFromDatabase;
	}
}
