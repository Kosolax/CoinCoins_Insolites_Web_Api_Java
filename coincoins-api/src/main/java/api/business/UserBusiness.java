package api.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.businessobject.PictureUser;
import api.businessobject.User;
import api.entity.UserEntity;
import api.ibusiness.IPictureUserBusiness;
import api.ibusiness.IUserBusiness;
import api.idataaccess.IUserDataAccess;

public class UserBusiness implements IUserBusiness {

	private IUserDataAccess userDataAccess;
	private IPictureUserBusiness pictureUserBusiness;
	
	public UserBusiness(IUserDataAccess userDataAccess, IPictureUserBusiness pictureUserBusiness) {
		this.userDataAccess = userDataAccess;
		this.pictureUserBusiness = pictureUserBusiness;
	}

	@Override
	public Map<Boolean, User> authenticate(User user) {
		//TODO CHANGER CA POUR FAIRE UNE GESTION DE TOKEN JWT QUI PASSE LE USER DEDANS
		Map<Boolean, User> dictionary = new HashMap<Boolean, User>();
		
		if(user != null 
				&& user.getMail() != null
				&& user.getPassword() != null) {
			UserEntity userEntity = new UserEntity(user);
			if(userEntity != null) {
				if(this.userDataAccess != null) {
					userEntity = this.userDataAccess.findByMailAndPassword(userEntity);
					Map<Boolean, List<PictureUser>> dictionaryListPictureUser = this.pictureUserBusiness.findAllFromUserId(user.getId());
					if(dictionaryListPictureUser != null && dictionaryListPictureUser.keySet().iterator().next()) {
						if(userEntity != null) {
							user = new User(userEntity, dictionaryListPictureUser.values().iterator().next());
							dictionary.put(true, user);
							return dictionary;
						}
					}
				}
			}
		}
		
		dictionary.put(false, null);
		return dictionary;
	}

	@Override
	public Map<Boolean, User> create(User user) {
		Map<Boolean, User> dictionary = new HashMap<Boolean, User>();
		
		if(user != null) {
			UserEntity userEntity = new UserEntity(user);
			
			if(userEntity != null) {
				userEntity = this.userDataAccess.create(userEntity);
				
				if(userEntity != null ) {
					user = new User(userEntity, null);
					
					if(user != null) {
						dictionary.put(true, user);
						return dictionary;
					}
				}
			}
		}
		
		dictionary.put(false, null);
		return dictionary;
	}

	@Override
	public Boolean delete(User user) {
		
		if(user != null) {
			boolean listPictureDeleted = this.pictureUserBusiness.deleteAllFromUserId(user.getId());
			boolean userDeleted = this.userDataAccess.delete(user.getId());
			
			if(listPictureDeleted && userDeleted) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Map<Boolean, List<User>> findAll() {
		Map<Boolean, List<User>> dictionary = new HashMap<Boolean, List<User>>();
		List<UserEntity> listUserEntity = this.userDataAccess.findAll();
		
		if(listUserEntity != null) {
			List<User> listUser = this.convertListUserEntityToListUser(listUserEntity);
			
			if(listUser != null) {
				dictionary.put(true, listUser);
				return dictionary;
			}
		}
		
		dictionary.put(false, null);
		return dictionary;
	}

	@Override
	public Map<Boolean, User> findById(Integer id) {
		Map<Boolean, User> dictionary = new HashMap<Boolean, User>();
		UserEntity userEntity = this.userDataAccess.findById(id);
		Map<Boolean, List<PictureUser>> result = this.pictureUserBusiness.findAllFromUserId(userEntity.getId());
		
		if(userEntity != null && result.keySet().iterator().next()) {
			User user = new  User(userEntity, 
					result.values().iterator().next()
			);
			
			if(user != null) {
				dictionary.put(true, user);
				return dictionary;
			}
		}

		dictionary.put(false, null);
		return dictionary;
	}

	@Override
	public Map<Boolean, User> update(User user) {
		Map<Boolean, User> dictionary = new HashMap<Boolean, User>();
		List<PictureUser> listPictureUser = new ArrayList<PictureUser>();
		
		if(user != null) {
			UserEntity userEntity = this.userDataAccess.update(new UserEntity(user));
			for(int i = 0; i < user.getListPictureUser().size(); i++) {
				Map<Boolean, PictureUser> result = this.pictureUserBusiness.update(user.getListPictureUser().get(i));
				
				if(result.keySet().iterator().next()) {
					listPictureUser.add(result.values().iterator().next());
				} else {
					dictionary.put(false, null);
					return dictionary;
				}
			}
			
			if(userEntity != null) {
				user = new User(userEntity, listPictureUser);
				
				if(user != null) {
					dictionary.put(true, user);
					return dictionary;
				}
			}
		}
		
		dictionary.put(false, null);
		return dictionary;
	}
	
	private List<User> convertListUserEntityToListUser(List<UserEntity> listUserEntity) {
		List<User> listUser = new ArrayList<User>();
		
		if(listUserEntity != null) {
			for(int i = 0; i < listUserEntity.size(); i++) {
				Map<Boolean, List<PictureUser>> result = this.pictureUserBusiness.findAllFromUserId(listUserEntity.get(i).getId());
				
				if(result.keySet().iterator().next()) {
					listUser.add(new User(listUserEntity.get(i), result.values().iterator().next()));
				}				
			}
		}
		
		return listUser;
	}
}
