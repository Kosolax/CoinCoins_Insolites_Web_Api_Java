package test;

import api.dataaccess.factory.AbstractDataAccessFactory;
import api.dataaccess.factory.AbstractDataAccessFactory.FactoryType;
import api.entity.UserEntity;
import api.idataaccess.IUserDataAccess;
import api.utils.Constants;
import junit.framework.Assert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestUserDataAccess {
    private static final Log log = LogFactory.getLog(TestUserDataAccess.class);
	public static IUserDataAccess userDataAccess;

    @BeforeClass
    public static void init() throws Exception {
        initDataBase();
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
    public void testCreateUpdateDeleteUser() {
    	log.debug("Début create");
    	UserEntity userEntityCreate = new UserEntity("mail@mail.mail", "password", "pseudonym");
    	UserEntity userEntity = userDataAccess.create(userEntityCreate);
    	Assert.assertTrue(userEntityCreate.getMail().equals(userEntity.getMail()));
    	Assert.assertTrue(userEntityCreate.getPassword().equals(userEntity.getPassword()));
    	Assert.assertTrue(userEntityCreate.getPseudonym().equals(userEntity.getPseudonym()));
    	log.debug("Fin create");
    	log.debug("Début update");
    	userEntity.setMail("mail2@mail2.mail2");
    	userEntity.setPassword("password2");
    	userEntity.setPseudonym("pseudonym2");
    	UserEntity userEntityFromUpdate = userDataAccess.update(userEntity);
    	Assert.assertTrue(userEntityFromUpdate.getMail().equals(userEntity.getMail()));
    	Assert.assertTrue(userEntityFromUpdate.getPassword().equals(userEntity.getPassword()));
    	Assert.assertTrue(userEntityFromUpdate.getPseudonym().equals(userEntity.getPseudonym()));
    	log.debug("Fin update");
    	log.debug("Début delete");
    	boolean isDeleted = userDataAccess.delete(userEntity.getId());
    	Assert.assertTrue(isDeleted);
    	log.debug("Fin delete");
    }

    @Test
    public void testFindAllUser() {
    	log.debug("Début find all user");
    	UserEntity userEntity = userDataAccess.create(new UserEntity("mail@mail.mail", "password", "pseudonym"));
    	UserEntity userEntity2 = userDataAccess.create(new UserEntity("mail2@mail2.mail2", "password2", "pseudonym2"));
    	List<UserEntity> listUserEntity = userDataAccess.findAll();
    	Assert.assertTrue(listUserEntity.get(0).getMail().equals(userEntity.getMail()));
    	Assert.assertTrue(listUserEntity.get(0).getPassword().equals(userEntity.getPassword()));
    	Assert.assertTrue(listUserEntity.get(0).getPseudonym().equals(userEntity.getPseudonym()));
    	Assert.assertTrue(listUserEntity.get(1).getMail().equals(userEntity2.getMail()));
    	Assert.assertTrue(listUserEntity.get(1).getPassword().equals(userEntity2.getPassword()));
    	Assert.assertTrue(listUserEntity.get(1).getPseudonym().equals(userEntity2.getPseudonym()));
    	userDataAccess.delete(userEntity.getId());
    	userDataAccess.delete(userEntity2.getId());
    	log.debug("Fin find all user");
    }
    
    @Test
    public void testFindByMailAndPassword() {
    	log.debug("Début find by mail and password");
    	UserEntity userEntity = userDataAccess.create(new UserEntity("mail@mail.mail", "password", "pseudonym"));
    	UserEntity userEntityFromFindByMailAndPassword = userDataAccess.findByMailAndPassword(userEntity);
    	Assert.assertTrue(userEntityFromFindByMailAndPassword.getMail().equals(userEntity.getMail()));
    	Assert.assertTrue(userEntityFromFindByMailAndPassword.getPassword().equals(userEntity.getPassword()));
    	Assert.assertTrue(userEntityFromFindByMailAndPassword.getPseudonym().equals(userEntity.getPseudonym()));
    	userDataAccess.delete(userEntity.getId());
    	log.debug("Fin find by mail and password");
    }
}
