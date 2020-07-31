package api.idataaccess;

import java.util.List;

import api.entity.PlaceEntity;

public interface IPlaceDataAccess extends IBaseDataAccess<PlaceEntity>{
	public List<PlaceEntity> findMostRecentPlaceEntity(int count);
}
