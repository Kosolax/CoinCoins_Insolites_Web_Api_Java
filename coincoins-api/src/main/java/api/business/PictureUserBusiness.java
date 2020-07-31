package api.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.businessobject.PictureUser;
import api.entity.PictureUserEntity;
import api.ibusiness.IPictureUserBusiness;
import api.idataaccess.IPictureUserDataAccess;

public class PictureUserBusiness implements IPictureUserBusiness{
	
	private IPictureUserDataAccess pictureUserDataAccess;
	
	public PictureUserBusiness(IPictureUserDataAccess pictureUserDataAccess) {
		this.pictureUserDataAccess = pictureUserDataAccess;
	}

	@Override
	public Map<Boolean, PictureUser> create(PictureUser pictureUser) {
		Map<Boolean, PictureUser> dictionary = new HashMap<Boolean, PictureUser>();
		
		if(pictureUser != null) {
			PictureUserEntity pictureUserEntity = new PictureUserEntity(pictureUser);
			
			if(pictureUserEntity != null) {
				PictureUserEntity pictureUserEntityFromDatabase = this.pictureUserDataAccess.create(pictureUserEntity);
				
				if(pictureUserEntityFromDatabase != null) {
					pictureUser = new PictureUser(pictureUserEntityFromDatabase);
					
					if(pictureUser != null) {
						dictionary.put(true, pictureUser);
						return dictionary;
					}
				}
			}
		}
		
		dictionary.put(false, null);
		return dictionary;
	}

	@Override
	public Map<Boolean, List<PictureUser>> createFromList(List<PictureUser> listPictureUser) {
		Map<Boolean, List<PictureUser>> dictionary = new HashMap<Boolean, List<PictureUser>>();
		List<PictureUser> listPictureUserCreated = new ArrayList<PictureUser>();
		
		if(listPictureUser != null) {
			for(int i = 0; i < listPictureUser.size(); i++) {
				PictureUserEntity pictureUserEntity = new PictureUserEntity(listPictureUser.get(i));
				
				if(pictureUserEntity != null) {
					PictureUserEntity pictureUserEntityFromDatabase = this.pictureUserDataAccess.create(pictureUserEntity);
				
					if(pictureUserEntityFromDatabase != null) {
						listPictureUserCreated.add(new PictureUser(pictureUserEntityFromDatabase));
					}
				}
			}
			
			if(listPictureUserCreated != null && listPictureUserCreated.size() == listPictureUser.size()) {
				dictionary.put(true, listPictureUserCreated);
				return dictionary;
			}
		}
		
		dictionary.put(false, null);
		return dictionary;
	}

	@Override
	public Boolean delete(PictureUser pictureUser) {
		
		if(pictureUser != null) {
			return this.pictureUserDataAccess.delete(pictureUser.getId());
		}
		
		return false;
	}

	@Override
	public Boolean deleteAllFromUserId(int userId) {
		return this.pictureUserDataAccess.deleteAllFromUserId(userId);
	}

	@Override
	public Map<Boolean, List<PictureUser>> findAll() {
		Map<Boolean, List<PictureUser>> dictionary = new HashMap<Boolean, List<PictureUser>>();
		List<PictureUserEntity> listPictureUserEntity = this.pictureUserDataAccess.findAll();
		
		if(listPictureUserEntity != null) {
			List<PictureUser> listPictureUser = this.convertListPictureUserEntityToListPictureUser(listPictureUserEntity);
			
			if(listPictureUser != null) {
				dictionary.put(true, listPictureUser);
				return dictionary;
			}
		}
		
		dictionary.put(false, null);
		return dictionary;
	}

	@Override
	public Map<Boolean, List<PictureUser>> findAllFromUserId(int userId) {
		Map<Boolean, List<PictureUser>> dictionary = new HashMap<Boolean, List<PictureUser>>();
		List<PictureUserEntity> listPictureUserEntity = this.pictureUserDataAccess.findAllPictureByUserId(userId);
		
		if(listPictureUserEntity != null) {
			List<PictureUser> listPictureUser = this.convertListPictureUserEntityToListPictureUser(listPictureUserEntity);
			
			if(listPictureUser != null) {
				dictionary.put(true, listPictureUser);
				return dictionary;
			}
		}
		
		dictionary.put(false, null);
		return dictionary;
	}

	@Override
	public Map<Boolean, PictureUser> findById(Integer id) {
		Map<Boolean, PictureUser> dictionary = new HashMap<Boolean, PictureUser>();
		PictureUserEntity pictureUserEntity = this.pictureUserDataAccess.findById(id);
		
		if(pictureUserEntity != null) {
			PictureUser pictureUser = new PictureUser(pictureUserEntity);
			
			if(pictureUser != null) {
				dictionary.put(true, pictureUser);
				return dictionary;
			}
		}
		
		dictionary.put(false, null);
		return dictionary;
	}

	@Override
	public Map<Boolean, PictureUser> update(PictureUser pictureUser) {
		Map<Boolean, PictureUser> dictionary = new HashMap<Boolean, PictureUser>();
		PictureUserEntity pictureUserEntity = new PictureUserEntity(pictureUser);
		
		if(pictureUserEntity != null) {
			PictureUserEntity pictureUserEntityFromDatabase = this.pictureUserDataAccess.update(pictureUserEntity);
			
			if(pictureUserEntityFromDatabase != null) {
				pictureUser = new PictureUser(pictureUserEntityFromDatabase);
				
				if(pictureUser != null) {
					dictionary.put(true, pictureUser);
					return dictionary;
				}
			}
		}
		
		dictionary.put(false, null);
		return dictionary;
	}
	
	private List<PictureUser> convertListPictureUserEntityToListPictureUser(List<PictureUserEntity> listPictureUserEntity) {
		List<PictureUser> listPictureUser = new ArrayList<PictureUser>();
		
		if(listPictureUserEntity != null) {
			for(int i = 0; i < listPictureUserEntity.size(); i++) {
				listPictureUser.add(new PictureUser(listPictureUserEntity.get(i)));
			}
		}
		
		return listPictureUser;
	}
}
