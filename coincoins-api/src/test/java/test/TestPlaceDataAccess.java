package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import api.dataaccess.factory.AbstractDataAccessFactory;
import api.dataaccess.factory.AbstractDataAccessFactory.FactoryType;
import api.entity.PlaceEntity;
import api.idataaccess.IPlaceDataAccess;
import api.utils.Constants;
import junit.framework.Assert;

public class TestPlaceDataAccess {
	private static final Log log = LogFactory.getLog(TestPlaceDataAccess.class);
	public static IPlaceDataAccess placeDataAccess;

    @BeforeClass
    public static void init() throws Exception {
        initDataBase();
        placeDataAccess = AbstractDataAccessFactory.getDataAccessFactory(FactoryType.SQL_DATA_ACCESS).getPlaceDataAccess();
    }

    public static void initDataBase() {
    	log.debug("Début init database");
    	Connection connection = null;
    	try {
    		connection = DriverManager.getConnection(Constants.DATABASE_URL, "root", "");
    		Constants.ABSTRACT_DATA_ACCESS_FACTORY.initData(true);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
    	
    	log.debug("Fin init database");
    }

    @Test
    public void testCreateUpdateDeletePlace() {
    	log.debug("Début create");
    	PlaceEntity placeEntityCreate = new PlaceEntity("Description", 11.1d, 11.1d, "Title");
    	PlaceEntity placeEntity = placeDataAccess.create(placeEntityCreate);
    	Assert.assertTrue(placeEntityCreate.getDescription().equals(placeEntity.getDescription()));
    	Assert.assertTrue(placeEntityCreate.getLatitude() == placeEntity.getLatitude());
    	Assert.assertTrue(placeEntityCreate.getLongitude() == placeEntity.getLongitude());
    	Assert.assertTrue(placeEntityCreate.getTitle().equals(placeEntity.getTitle()));
    	log.debug("Fin create");
    	log.debug("Début update");
    	placeEntity.setDescription("Description2");
    	placeEntity.setLatitude(22.2d);
    	placeEntity.setLongitude(22.2d);
    	placeEntity.setTitle("Title2");
    	PlaceEntity placeEntityFromUpdate = placeDataAccess.update(placeEntity);
    	Assert.assertTrue(placeEntityFromUpdate.getDescription().equals(placeEntity.getDescription()));
    	Assert.assertTrue(placeEntityFromUpdate.getLatitude() == placeEntity.getLatitude());
    	Assert.assertTrue(placeEntityFromUpdate.getLongitude() == placeEntity.getLongitude());
    	Assert.assertTrue(placeEntityFromUpdate.getTitle().equals(placeEntity.getTitle()));
    	log.debug("Fin update");
    	log.debug("Début delete");
    	boolean isDeleted = placeDataAccess.delete(placeEntity.getId());
    	Assert.assertTrue(isDeleted);
    	log.debug("Fin delete");
    }

    @Test
    public void testFindAllPlace() {
    	log.debug("Début find all user");
    	PlaceEntity placeEntity = placeDataAccess.create(new PlaceEntity("Description", 11.1d, 11.1d, "Title"));
    	PlaceEntity placeEntity2 = placeDataAccess.create(new PlaceEntity("Description2", 22.2d, 22.2d, "Title2"));
    	List<PlaceEntity> listPlaceEntity = placeDataAccess.findAll();
    	Assert.assertTrue(listPlaceEntity.get(1).getDescription().equals(placeEntity.getDescription()));
    	Assert.assertTrue(listPlaceEntity.get(1).getLatitude() == placeEntity.getLatitude());
    	Assert.assertTrue(listPlaceEntity.get(1).getLongitude() == placeEntity.getLongitude());
    	Assert.assertTrue(listPlaceEntity.get(1).getTitle().equals(placeEntity.getTitle()));
    	Assert.assertTrue(listPlaceEntity.get(0).getDescription().equals(placeEntity2.getDescription()));
    	Assert.assertTrue(listPlaceEntity.get(0).getLatitude() == placeEntity2.getLatitude());
    	Assert.assertTrue(listPlaceEntity.get(0).getLongitude() == placeEntity2.getLongitude());
    	Assert.assertTrue(listPlaceEntity.get(0).getTitle().equals(placeEntity2.getTitle()));
    	placeDataAccess.delete(placeEntity.getId());
    	placeDataAccess.delete(placeEntity2.getId());
    	log.debug("Fin find all user");
    }
}
