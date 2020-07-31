package api.idataaccess;

import java.util.List;

import api.entity.PicturePlaceEntity;

public interface IPicturePlaceDataAccess extends IBaseDataAccess<PicturePlaceEntity> {
	public List<PicturePlaceEntity> findAllPictureByPlaceId(int placeId);
	public Boolean deleteAllFromPlaceId(int placeId);
}
