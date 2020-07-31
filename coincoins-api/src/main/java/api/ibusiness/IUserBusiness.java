package api.ibusiness;

import java.util.Map;

import api.businessobject.User;

public interface IUserBusiness extends IBaseBusiness<User> {
	public Map<Boolean, User> authenticate(User user);
}
