package api.idataaccess;

import java.util.List;

import api.entity.PictureUserEntity;

public interface IPictureUserDataAccess extends IBaseDataAccess<PictureUserEntity>  {
	public List<PictureUserEntity> findAllPictureByUserId(int userId);
	public Boolean deleteAllFromUserId(int userId);
}
