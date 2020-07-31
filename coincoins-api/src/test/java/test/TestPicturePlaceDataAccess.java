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
import api.entity.PicturePlaceEntity;
import api.entity.PlaceEntity;
import api.idataaccess.IPicturePlaceDataAccess;
import api.idataaccess.IPlaceDataAccess;
import api.utils.Constants;
import junit.framework.Assert;

public class TestPicturePlaceDataAccess {
	private static final Log log = LogFactory.getLog(TestPicturePlaceDataAccess.class);
	public static IPicturePlaceDataAccess picturePlaceDataAccess;
	public static IPlaceDataAccess placeDataAccess;

    @BeforeClass
    public static void init() throws Exception {
        initDataBase();
        picturePlaceDataAccess = AbstractDataAccessFactory.getDataAccessFactory(FactoryType.SQL_DATA_ACCESS).getPicturePlaceDataAccess();
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
    public void testCreateUpdateDeletePicturePlace() {
    	log.debug("Début create");
    	PlaceEntity placeEntityCreate = new PlaceEntity("description", 11d, 11d, "title");
    	PlaceEntity placeEntity = placeDataAccess.create(placeEntityCreate);
    	PicturePlaceEntity picturePlaceEntityCreate = new PicturePlaceEntity(placeEntity.getId(), 11d, 11d, "svg");
    	PicturePlaceEntity picturePlaceEntity = picturePlaceDataAccess.create(picturePlaceEntityCreate);
    	Assert.assertTrue(picturePlaceEntityCreate.getSvgLink().equals(picturePlaceEntity.getSvgLink()));
    	Assert.assertTrue(picturePlaceEntityCreate.getLatitude() == picturePlaceEntity.getLatitude());
    	Assert.assertTrue(picturePlaceEntityCreate.getLongitude() == picturePlaceEntity.getLongitude());
    	Assert.assertTrue(picturePlaceEntityCreate.getIdPlace() == picturePlaceEntity.getIdPlace());
    	log.debug("Fin create");
    	log.debug("Début update");
    	placeEntity.setDescription("description2");
    	placeEntity.setLatitude(22d);
    	placeEntity.setLongitude(22d);
    	placeEntity.setTitle("title2");
    	placeDataAccess.update(placeEntity);
    	picturePlaceEntity.setSvgLink("svg2");
    	picturePlaceEntity.setLatitude(22d);
    	picturePlaceEntity.setLongitude(22d);
    	picturePlaceEntity.setIdPlace(1);
    	PicturePlaceEntity picturePlaceEntityFromUpdate = picturePlaceDataAccess.update(picturePlaceEntity);
    	Assert.assertTrue(picturePlaceEntityFromUpdate.getSvgLink().equals(picturePlaceEntity.getSvgLink()));
    	Assert.assertTrue(picturePlaceEntityFromUpdate.getLatitude() == picturePlaceEntity.getLatitude());
    	Assert.assertTrue(picturePlaceEntityFromUpdate.getLongitude() == picturePlaceEntity.getLongitude());
    	Assert.assertTrue(picturePlaceEntityFromUpdate.getIdPlace() == picturePlaceEntity.getIdPlace());
    	log.debug("Fin update");
    	log.debug("Début delete");
    	boolean isDeleted = picturePlaceDataAccess.delete(picturePlaceEntity.getId());
    	placeDataAccess.delete(placeEntity.getId());
    	Assert.assertTrue(isDeleted);
    	log.debug("Fin delete");
    }

    @Test
    public void testFindAllPicturePlace() {
    	log.debug("Début find all place");
    	PlaceEntity placeEntity = placeDataAccess.create(new PlaceEntity("description", 11d, 11d, "title"));
    	PlaceEntity placeEntity2 = placeDataAccess.create(new PlaceEntity("description2", 22d, 22d, "title2"));
    	PicturePlaceEntity picturePlaceEntity = picturePlaceDataAccess.create(new PicturePlaceEntity(placeEntity.getId(), 11d, 11d, "svg"));
    	PicturePlaceEntity picturePlaceEntity2 = picturePlaceDataAccess.create(new PicturePlaceEntity(placeEntity2.getId(), 22d, 22d, "sv2g"));
    	List<PicturePlaceEntity> listPicturePlaceEntity = picturePlaceDataAccess.findAll();
    	Assert.assertTrue(listPicturePlaceEntity.get(0).getSvgLink().equals(picturePlaceEntity.getSvgLink()));
    	Assert.assertTrue(listPicturePlaceEntity.get(0).getLatitude() == picturePlaceEntity.getLatitude());
    	Assert.assertTrue(listPicturePlaceEntity.get(0).getLongitude() == picturePlaceEntity.getLongitude());
    	Assert.assertTrue(listPicturePlaceEntity.get(0).getIdPlace() == picturePlaceEntity.getIdPlace());
    	Assert.assertTrue(listPicturePlaceEntity.get(1).getSvgLink().equals(picturePlaceEntity2.getSvgLink()));
    	Assert.assertTrue(listPicturePlaceEntity.get(1).getLatitude() == picturePlaceEntity2.getLatitude());
    	Assert.assertTrue(listPicturePlaceEntity.get(1).getLongitude() == picturePlaceEntity2.getLongitude());
    	Assert.assertTrue(listPicturePlaceEntity.get(1).getIdPlace() ==  picturePlaceEntity2.getIdPlace());
    	picturePlaceDataAccess.delete(picturePlaceEntity.getId());
    	picturePlaceDataAccess.delete(picturePlaceEntity2.getId());
    	placeDataAccess.delete(placeEntity.getId());
    	placeDataAccess.delete(placeEntity2.getId());
    	log.debug("Fin find all place");
    }
}
