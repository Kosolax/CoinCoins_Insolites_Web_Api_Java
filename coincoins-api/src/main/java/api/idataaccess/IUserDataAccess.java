package api.idataaccess;

import api.entity.UserEntity;

public interface IUserDataAccess extends IBaseDataAccess<UserEntity>{
	public UserEntity findByMailAndPassword(UserEntity userEntity);
}
