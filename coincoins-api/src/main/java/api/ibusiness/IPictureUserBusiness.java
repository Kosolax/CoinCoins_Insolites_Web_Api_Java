package api.ibusiness;

import java.util.List;
import java.util.Map;

import api.businessobject.PictureUser;

public interface IPictureUserBusiness extends IBaseBusiness<PictureUser> {
	public Map<Boolean, List<PictureUser>> createFromList(List<PictureUser> listPictureUser);
	public Map<Boolean, List<PictureUser>> findAllFromUserId(int userId);
	public Boolean deleteAllFromUserId(int userId);
}
