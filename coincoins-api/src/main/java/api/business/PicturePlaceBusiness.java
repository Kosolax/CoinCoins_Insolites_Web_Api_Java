package api.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.businessobject.PicturePlace;
import api.entity.PicturePlaceEntity;
import api.ibusiness.IPicturePlaceBusiness;
import api.idataaccess.IPicturePlaceDataAccess;

public class PicturePlaceBusiness implements IPicturePlaceBusiness{

private IPicturePlaceDataAccess picturePlaceDataAccess;
	
	public PicturePlaceBusiness(IPicturePlaceDataAccess picturePlaceDataAccess) {
		this.picturePlaceDataAccess = picturePlaceDataAccess;
	}

	@Override
	public Map<Boolean, PicturePlace> create(PicturePlace picturePlace) {
		Map<Boolean, PicturePlace> dictionary = new HashMap<Boolean, PicturePlace>();
		
		if(picturePlace != null) {
			PicturePlaceEntity picturePlaceEntity = new PicturePlaceEntity(picturePlace);
			
			if(picturePlaceEntity != null) {
				PicturePlaceEntity picturePlaceEntityFromDatabase = this.picturePlaceDataAccess.create(picturePlaceEntity);
				
				if(picturePlaceEntityFromDatabase != null) {
					picturePlace = new PicturePlace(picturePlaceEntityFromDatabase);
					
					if(picturePlace != null) {
						dictionary.put(true, picturePlace);
						return dictionary;
					}
				}
			}
		}
		
		dictionary.put(false, null);
		return dictionary;
	}

	@Override
	public Map<Boolean, List<PicturePlace>> createFromList(List<PicturePlace> listPicturePlace) {
		Map<Boolean, List<PicturePlace>> dictionary = new HashMap<Boolean, List<PicturePlace>>();
		List<PicturePlace> listPicturePlaceCreated = new ArrayList<PicturePlace>();
		
		if(listPicturePlace != null) {
			for(int i = 0; i < listPicturePlace.size(); i++) {
				PicturePlaceEntity picturePlaceEntity = new PicturePlaceEntity(listPicturePlace.get(i));
				
				if(picturePlaceEntity != null) {
					PicturePlaceEntity picturePlaceEntityFromDatabase = this.picturePlaceDataAccess.create(picturePlaceEntity);
				
					if(picturePlaceEntityFromDatabase != null) {
						listPicturePlaceCreated.add(new PicturePlace(picturePlaceEntityFromDatabase));
					}
				}
			}
			
			if(listPicturePlaceCreated != null && listPicturePlaceCreated.size() == listPicturePlace.size()) {
				dictionary.put(true, listPicturePlaceCreated);
				return dictionary;
			}
		}
		
		dictionary.put(false, null);
		return dictionary;
	}

	@Override
	public Boolean delete(PicturePlace picturePlace) {
		
		if(picturePlace != null) {
			return this.picturePlaceDataAccess.delete(picturePlace.getId());
		}
		
		return false;
	}

	@Override
	public Boolean deleteAllFromPlaceId(int placeId) {
		return this.picturePlaceDataAccess.deleteAllFromPlaceId(placeId);
	}

	@Override
	public Map<Boolean, List<PicturePlace>> findAll() {
		Map<Boolean, List<PicturePlace>> dictionary = new HashMap<Boolean, List<PicturePlace>>();
		List<PicturePlaceEntity> listPicturePlaceEntity = this.picturePlaceDataAccess.findAll();
		
		if(listPicturePlaceEntity != null) {
			List<PicturePlace> listPicturePlace = this.convertListPicturePlaceEntityToListPicturePlace(listPicturePlaceEntity);
			
			if(listPicturePlace != null) {
				dictionary.put(true, listPicturePlace);
				return dictionary;
			}
		}
		
		dictionary.put(false, null);
		return dictionary;
	}

	@Override
	public Map<Boolean, List<PicturePlace>> findAllFromPlaceId(int placeId) {
		Map<Boolean, List<PicturePlace>> dictionary = new HashMap<Boolean, List<PicturePlace>>();
		List<PicturePlaceEntity> listPicturePlaceEntity = this.picturePlaceDataAccess.findAllPictureByPlaceId(placeId);
		
		if(listPicturePlaceEntity != null) {
			List<PicturePlace> listPicturePlace = this.convertListPicturePlaceEntityToListPicturePlace(listPicturePlaceEntity);
			
			if(listPicturePlace != null) {
				dictionary.put(true, listPicturePlace);
				return dictionary;
			}
		}
		
		dictionary.put(false, null);
		return dictionary;
	}

	@Override
	public Map<Boolean, PicturePlace> findById(Integer id) {
		Map<Boolean, PicturePlace> dictionary = new HashMap<Boolean, PicturePlace>();
		PicturePlaceEntity picturePlaceEntity = this.picturePlaceDataAccess.findById(id);
		
		if(picturePlaceEntity != null) {
			PicturePlace picturePlace = new PicturePlace(picturePlaceEntity);
			
			if(picturePlace != null) {
				dictionary.put(true, picturePlace);
				return dictionary;
			}
		}
		
		dictionary.put(false, null);
		return dictionary;
	}

	@Override
	public Map<Boolean, PicturePlace> update(PicturePlace picturePlace) {
		Map<Boolean, PicturePlace> dictionary = new HashMap<Boolean, PicturePlace>();
		PicturePlaceEntity picturePlaceEntity = new PicturePlaceEntity(picturePlace);
		
		if(picturePlaceEntity != null) {
			PicturePlaceEntity picturePlaceEntityFromDatabase = this.picturePlaceDataAccess.update(picturePlaceEntity);
			
			if(picturePlaceEntityFromDatabase != null) {
				picturePlace = new PicturePlace(picturePlaceEntityFromDatabase);
				
				if(picturePlace != null) {
					dictionary.put(true, picturePlace);
					return dictionary;
				}
			}
		}
		
		dictionary.put(false, null);
		return dictionary;
	}
	
	private List<PicturePlace> convertListPicturePlaceEntityToListPicturePlace(List<PicturePlaceEntity> listPicturePlaceEntity) {
		List<PicturePlace> listPicturePlace = new ArrayList<PicturePlace>();
		
		if(listPicturePlaceEntity != null) {
			for(int i = 0; i < listPicturePlaceEntity.size(); i++) {
				listPicturePlace.add(new PicturePlace(listPicturePlaceEntity.get(i)));
			}
		}
		
		return listPicturePlace;
	}
}
