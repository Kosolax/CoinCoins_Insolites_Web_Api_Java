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
import api.entity.PictureUserEntity;
import api.entity.UserEntity;
import api.idataaccess.IPictureUserDataAccess;
import api.idataaccess.IUserDataAccess;
import api.utils.Constants;
import junit.framework.Assert;

public class TestPictureUserDataAccess {
	private static final Log log = LogFactory.getLog(TestPictureUserDataAccess.class);
	public static IPictureUserDataAccess pictureUserDataAccess;
	public static IUserDataAccess userDataAccess;

    @BeforeClass
    public static void init() throws Exception {
        initDataBase();
        pictureUserDataAccess = AbstractDataAccessFactory.getDataAccessFactory(FactoryType.SQL_DATA_ACCESS).getPictureUserDataAccess();
        userDataAccess = AbstractDataAccessFactory.getDataAccessFactory(FactoryType.SQL_DATA_ACCESS).getUserDataAccess();
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
    public void testCreateUpdateDeletePictureUser() {
    	log.debug("Début create");
    	UserEntity userEntityCreate = new UserEntity("mail@mail.mail", "password", "pseudonym");
    	UserEntity userEntity = userDataAccess.create(userEntityCreate);
    	PictureUserEntity pictureUserEntityCreate = new PictureUserEntity(userEntity.getId(), 11d, 11d, "svg");
    	PictureUserEntity pictureUserEntity = pictureUserDataAccess.create(pictureUserEntityCreate);
    	Assert.assertTrue(pictureUserEntityCreate.getSvgLink().equals(pictureUserEntity.getSvgLink()));
    	Assert.assertTrue(pictureUserEntityCreate.getLatitude() == pictureUserEntity.getLatitude());
    	Assert.assertTrue(pictureUserEntityCreate.getLongitude() == pictureUserEntity.getLongitude());
    	Assert.assertTrue(pictureUserEntityCreate.getIdUser() == pictureUserEntity.getIdUser());
    	log.debug("Fin create");
    	log.debug("Début update");
    	userEntity.setMail("mail2@mail2.mail2");
    	userEntity.setPassword("password2");
    	userEntity.setPseudonym("pseudonym2");
    	userDataAccess.update(userEntity);
    	pictureUserEntity.setSvgLink("svg2");
    	pictureUserEntity.setLatitude(22d);
    	pictureUserEntity.setLongitude(22d);
    	pictureUserEntity.setIdUser(1);
    	PictureUserEntity pictureUserEntityFromUpdate = pictureUserDataAccess.update(pictureUserEntity);
    	Assert.assertTrue(pictureUserEntityFromUpdate.getSvgLink().equals(pictureUserEntity.getSvgLink()));
    	Assert.assertTrue(pictureUserEntityFromUpdate.getLatitude() == pictureUserEntity.getLatitude());
    	Assert.assertTrue(pictureUserEntityFromUpdate.getLongitude() == pictureUserEntity.getLongitude());
    	Assert.assertTrue(pictureUserEntityFromUpdate.getIdUser() == pictureUserEntity.getIdUser());
    	log.debug("Fin update");
    	log.debug("Début delete");
    	boolean isDeleted = pictureUserDataAccess.delete(pictureUserEntity.getId());
    	userDataAccess.delete(userEntity.getId());
    	Assert.assertTrue(isDeleted);
    	log.debug("Fin delete");
    }

    @Test
    public void testFindAllPictureUser() {
    	log.debug("Début find all user");
    	UserEntity userEntity = userDataAccess.create(new UserEntity("mail@mail.mail", "password", "pseudonym"));
    	UserEntity userEntity2 = userDataAccess.create(new UserEntity("mail2@mail2.mail2", "password2", "pseudonym2"));
    	PictureUserEntity pictureUserEntity = pictureUserDataAccess.create(new PictureUserEntity(userEntity.getId(), 11d, 11d, "svg"));
    	PictureUserEntity pictureUserEntity2 = pictureUserDataAccess.create(new PictureUserEntity(userEntity2.getId(), 22d, 22d, "sv2g"));
    	List<PictureUserEntity> listPictureUserEntity = pictureUserDataAccess.findAll();
    	Assert.assertTrue(listPictureUserEntity.get(0).getSvgLink().equals(pictureUserEntity.getSvgLink()));
    	Assert.assertTrue(listPictureUserEntity.get(0).getLatitude() == pictureUserEntity.getLatitude());
    	Assert.assertTrue(listPictureUserEntity.get(0).getLongitude() == pictureUserEntity.getLongitude());
    	Assert.assertTrue(listPictureUserEntity.get(0).getIdUser() == pictureUserEntity.getIdUser());
    	Assert.assertTrue(listPictureUserEntity.get(1).getSvgLink().equals(pictureUserEntity2.getSvgLink()));
    	Assert.assertTrue(listPictureUserEntity.get(1).getLatitude() == pictureUserEntity2.getLatitude());
    	Assert.assertTrue(listPictureUserEntity.get(1).getLongitude() == pictureUserEntity2.getLongitude());
    	Assert.assertTrue(listPictureUserEntity.get(1).getIdUser() ==  pictureUserEntity2.getIdUser());
    	pictureUserDataAccess.delete(pictureUserEntity.getId());
    	pictureUserDataAccess.delete(pictureUserEntity2.getId());
    	userDataAccess.delete(userEntity.getId());
    	userDataAccess.delete(userEntity2.getId());
    	log.debug("Fin find all user");
    }
}
