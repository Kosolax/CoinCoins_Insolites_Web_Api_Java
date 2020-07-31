package api.dataaccess.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import api.dataaccess.PicturePlaceDataAccess;
import api.dataaccess.PictureUserDataAccess;
import api.dataaccess.PlaceDataAccess;
import api.dataaccess.UserDataAccess;
import api.entity.PicturePlaceEntity;
import api.entity.PictureUserEntity;
import api.entity.PlaceEntity;
import api.entity.UserEntity;
import api.idataaccess.IPicturePlaceDataAccess;
import api.idataaccess.IPictureUserDataAccess;
import api.idataaccess.IPlaceDataAccess;
import api.idataaccess.IUserDataAccess;
import api.utils.CloseRessourceHelper;
import api.utils.Constants;

public class SqlDataAccessFactory extends AbstractDataAccessFactory {
    private IPicturePlaceDataAccess picturePlaceDataAccess = null;
    private IPictureUserDataAccess pictureUserDataAccess = null;
    private IPlaceDataAccess placeDataAccess = null;
    private IUserDataAccess userDataAccessss = null;
    
    public SqlDataAccessFactory() {
        this.picturePlaceDataAccess = new PicturePlaceDataAccess();
        this.pictureUserDataAccess = new PictureUserDataAccess();
        this.placeDataAccess = new PlaceDataAccess();
        this.userDataAccessss = new UserDataAccess();
    }

    @Override
    public IPicturePlaceDataAccess getPicturePlaceDataAccess() {
        return this.picturePlaceDataAccess;
    }

    @Override
    public IPictureUserDataAccess getPictureUserDataAccess() {
        return this.pictureUserDataAccess;
    }

    @Override
    public IPlaceDataAccess getPlaceDataAccess() {
        return this.placeDataAccess;
    }

    @Override
    public IUserDataAccess getUserDataAccess() {
        return this.userDataAccessss;
    }
    
    @Override
    public void initData(boolean needToDelete) {
    	Connection connection = null;
    	
    	try {
			connection = DriverManager.getConnection(Constants.DATABASE_URL, "root", "");
			if(needToDelete) {
				this.dropDatabase(connection);
			}
			this.createDatabase(connection);
			this.grantPermissionToUser(connection);
			this.useDatabase(connection);
			this.createTables(connection);
			this.insertData(needToDelete);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseRessourceHelper.connectionCloseResources(connection);
		}
    }
    
    private void createDatabase(Connection connection) {
		try {
    		Statement statement = connection.createStatement();
    		statement.execute("CREATE DATABASE IF NOT EXISTS " + Constants.DATABASE_NAME);
		} catch (Exception e){
    		e.printStackTrace();
    		dropDatabase(connection);
		}
    }

    private void grantPermissionToUser(Connection connection) {
    	try {
    		Statement statement = connection.createStatement();
    		statement.execute("GRANT ALL ON " + Constants.DATABASE_NAME + ".* TO '" + Constants.DATABASE_USER + "'@'localhost' IDENTIFIED BY '" + Constants.DATABASE_PASSWORD + "';");
		} catch (Exception e){
    		e.printStackTrace();
    		dropDatabase(connection);
		}
    }
    
    private void useDatabase(Connection connection) {
    	try {
    		Statement statement = connection.createStatement();
    		statement.execute("USE " + Constants.DATABASE_NAME);
		} catch (Exception e){
    		e.printStackTrace();
    		dropDatabase(connection);
		}
    }
    
    private void createTables(Connection connection) {
    	try {
    		Statement statement = connection.createStatement();
    		statement.execute("CREATE TABLE IF NOT EXISTS " + Constants.USER_TABLE_NAME + "(" + 
    				Constants.USER_COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT," + 
    				Constants.USER_COLUMN_NAME_MAIL + " VARCHAR(100) NOT NULL UNIQUE," + 
    				Constants.USER_COLUMN_NAME_PASSWORD + " VARCHAR(100) NOT NULL," + 
    				Constants.USER_COLUMN_NAME_PSEUDONYM + " VARCHAR(100) NOT NULL" + 
    				" )");
    		statement.execute("CREATE TABLE IF NOT EXISTS " + Constants.PLACE_TABLE_NAME + "(" + 
    				Constants.PLACE_COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT," + 
    				Constants.PLACE_COLUMN_NAME_DESCRIPTION + " VARCHAR(6000) NOT NULL," +
    				Constants.PLACE_COLUMN_NAME_LATITUDE + " double NOT NULL," + 
    				Constants.PLACE_COLUMN_NAME_LONGITUDE + " double NOT NULL," + 
    				Constants.PLACE_COLUMN_NAME_TITLE + " VARCHAR(6000) NOT NULL" +
    				" )");
    		statement.execute("CREATE TABLE IF NOT EXISTS " + Constants.PICTURE_USER_TABLE_NAME + "(" + 
    				Constants.PICTURE_USER_COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT," + 
    				Constants.PICTURE_USER_COLUMN_NAME_ID_USER + " INTEGER NOT NULL," +
    				Constants.PICTURE_USER_COLUMN_NAME_LATITUDE + " double NOT NULL," + 
    				Constants.PICTURE_USER_COLUMN_NAME_LONGITUDE + " double NOT NULL," + 
    				Constants.PICTURE_USER_COLUMN_NAME_SVG_LINK + " MEDIUMTEXT NOT NULL," +
    				"FOREIGN KEY " + "(" + Constants.PICTURE_USER_COLUMN_NAME_ID_USER + ") REFERENCES " +  Constants.USER_TABLE_NAME + "(" + Constants.USER_COLUMN_NAME_ID + ")" +
    				")");
    		statement.execute("CREATE TABLE IF NOT EXISTS " + Constants.PICTURE_PLACE_TABLE_NAME + "(" + 
    				Constants.PICTURE_PLACE_COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT," + 
    				Constants.PICTURE_PLACE_COLUMN_NAME_ID_PLACE + " INTEGER NOT NULL," +
    				Constants.PICTURE_PLACE_COLUMN_NAME_LATITUDE + " double NOT NULL," + 
    				Constants.PICTURE_PLACE_COLUMN_NAME_LONGITUDE + " double NOT NULL," + 
    				Constants.PICTURE_PLACE_COLUMN_NAME_SVG_LINK + " MEDIUMTEXT NOT NULL," +
    				"FOREIGN KEY " + "(" + Constants.PICTURE_PLACE_COLUMN_NAME_ID_PLACE + ") REFERENCES " +  Constants.PLACE_TABLE_NAME + "(" + Constants.PLACE_COLUMN_NAME_ID + ")" +
    				")");
		} catch (Exception e){
    		e.printStackTrace();
    		dropDatabase(connection);
		}
    }
    
    private void insertData(boolean needToDelete) {
    	if(!needToDelete) {
        	UserEntity userEntity = this.userDataAccessss.create(new UserEntity("toto", "pass" ,"pseudonym"));
        	this.pictureUserDataAccess.create(new PictureUserEntity(userEntity.getId(), 10d, 20d, "svg"));
        	PlaceEntity placeEntity = this.placeDataAccess.create(new PlaceEntity("description", 10d, 20d, "Title"));
            this.placeDataAccess.create(new PlaceEntity("description", 10d, 20d, "Title"));
            this.placeDataAccess.create(new PlaceEntity("description", 10d, 20d, "Title"));
            this.placeDataAccess.create(new PlaceEntity("description", 10d, 20d, "Title"));
            this.placeDataAccess.create(new PlaceEntity("description", 10d, 20d, "Title"));
            this.placeDataAccess.create(new PlaceEntity("description", 10d, 20d, "Title"));
            this.placeDataAccess.create(new PlaceEntity("description", 10d, 20d, "Title"));
            this.placeDataAccess.create(new PlaceEntity("description", 10d, 20d, "Title"));
        	this.picturePlaceDataAccess.create(new PicturePlaceEntity(placeEntity.getId(), 10d, 20d, "svg"));
    	}
    }
    
    private void dropDatabase(Connection connection) {
		try {
			Statement statement = connection.createStatement();
    		statement.execute("DROP DATABASE " + Constants.DATABASE_NAME);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
