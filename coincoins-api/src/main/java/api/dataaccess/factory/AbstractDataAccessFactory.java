package api.dataaccess.factory;

import api.idataaccess.IPicturePlaceDataAccess;
import api.idataaccess.IPictureUserDataAccess;
import api.idataaccess.IPlaceDataAccess;
import api.idataaccess.IUserDataAccess;

public abstract class AbstractDataAccessFactory {
	public abstract IPicturePlaceDataAccess getPicturePlaceDataAccess();
	public abstract IPictureUserDataAccess getPictureUserDataAccess();
	public abstract IPlaceDataAccess getPlaceDataAccess();
	public abstract IUserDataAccess getUserDataAccess();
	public abstract void initData(boolean needToDelete);
	
	public enum FactoryType {
    	SQL_DATA_ACCESS
    }

    public static AbstractDataAccessFactory getDataAccessFactory(FactoryType type) {
    	AbstractDataAccessFactory abstractDataAccess = null;
        switch (type) {
            case SQL_DATA_ACCESS:
            	abstractDataAccess = new SqlDataAccessFactory();
                break;
        }
        
        return abstractDataAccess;
    }
}
