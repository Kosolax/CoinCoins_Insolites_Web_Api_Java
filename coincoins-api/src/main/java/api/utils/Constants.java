package api.utils;

import api.dataaccess.factory.AbstractDataAccessFactory;
import api.dataaccess.factory.AbstractDataAccessFactory.FactoryType;

public class Constants {
	public static AbstractDataAccessFactory ABSTRACT_DATA_ACCESS_FACTORY = AbstractDataAccessFactory.getDataAccessFactory(FactoryType.SQL_DATA_ACCESS);

    public static String DATABASE_URL = "jdbc:mysql://localhost:3306/";
    public static String DATABASE_NAME = "coincoins_insolites";
    
    public static String DATABASE_USER = "application";
    public static String DATABASE_PASSWORD = "passw0rd";

	public static String USER_TABLE_NAME = "User";
	public static String USER_COLUMN_NAME_ID = "user_id";
	public static String USER_COLUMN_NAME_MAIL = "user_mail";
	public static String USER_COLUMN_NAME_PASSWORD = "user_password";
	public static String USER_COLUMN_NAME_PSEUDONYM = "user_pseudonym";
	

	public static String PLACE_TABLE_NAME = "Place";
	public static String PLACE_COLUMN_NAME_ID = "place_id";
	public static String PLACE_COLUMN_NAME_DESCRIPTION = "place_description";
	public static String PLACE_COLUMN_NAME_LATITUDE = "place_latitude";
	public static String PLACE_COLUMN_NAME_LONGITUDE = "place_longitude";
	public static String PLACE_COLUMN_NAME_TITLE = "place_title";
	
	public static String PICTURE_USER_TABLE_NAME = "Picture_User";
	public static String PICTURE_USER_COLUMN_NAME_ID = "picture_user_id";
	public static String PICTURE_USER_COLUMN_NAME_SVG_LINK = "picture_user_svg_link";
	public static String PICTURE_USER_COLUMN_NAME_LATITUDE = "picture_user_latitude";
	public static String PICTURE_USER_COLUMN_NAME_LONGITUDE = "picture_user_longitude";
	public static String PICTURE_USER_COLUMN_NAME_ID_USER = "picture_user_id_user";
	
	public static String PICTURE_PLACE_TABLE_NAME = "Picture_Place";
	public static String PICTURE_PLACE_COLUMN_NAME_ID = "picture_place_id";
	public static String PICTURE_PLACE_COLUMN_NAME_SVG_LINK = "picture_place_svg_link";
	public static String PICTURE_PLACE_COLUMN_NAME_LATITUDE = "picture_place_latitude";
	public static String PICTURE_PLACE_COLUMN_NAME_LONGITUDE = "picture_place_longitude";
	public static String PICTURE_PLACE_COLUMN_NAME_ID_PLACE = "picture_place_id_place";
}
