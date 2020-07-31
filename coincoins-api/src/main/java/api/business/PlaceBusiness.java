package api.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.businessobject.PicturePlace;
import api.businessobject.Place;
import api.entity.PlaceEntity;
import api.ibusiness.IPicturePlaceBusiness;
import api.ibusiness.IPlaceBusiness;
import api.idataaccess.IPlaceDataAccess;

public class PlaceBusiness implements IPlaceBusiness{

	private IPlaceDataAccess placeDataAccess;
	private IPicturePlaceBusiness picturePlaceBusiness;
	
	public PlaceBusiness(IPlaceDataAccess placeDataAccess, IPicturePlaceBusiness picturePlaceBusiness) {
		this.placeDataAccess = placeDataAccess;
		this.picturePlaceBusiness = picturePlaceBusiness;
	}

	@Override
	public Map<Boolean, Place> create(Place place) {
		Map<Boolean, Place> dictionary = new HashMap<Boolean, Place>();
		
		if(place != null) {
			PlaceEntity placeEntity = new PlaceEntity(place);
			
			if(placeEntity != null) {
				placeEntity = this.placeDataAccess.create(placeEntity);
				
				if(placeEntity != null) {
					place = new Place(placeEntity, null);
					
					if(place != null) {
						dictionary.put(true, place);
						return dictionary;
					}
				}
			}
		}
		
		dictionary.put(false, null);
		return dictionary;
	}

	@Override
	public Boolean delete(Place place) {
		
		if(place != null) {
			boolean listPictureDeleted = this.picturePlaceBusiness.deleteAllFromPlaceId(place.getId());
			boolean placeDeleted = this.placeDataAccess.delete(place.getId());
			
			if(listPictureDeleted && placeDeleted) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Map<Boolean, List<Place>> findAll() {
		Map<Boolean, List<Place>> dictionary = new HashMap<Boolean, List<Place>>();
		List<PlaceEntity> listPlaceEntity = this.placeDataAccess.findAll();
		
		if(listPlaceEntity != null) {
			List<Place> listPlace = this.convertListPlaceEntityToListPlace(listPlaceEntity);
			
			if(listPlace != null) {
				dictionary.put(true, listPlace);
				return dictionary;
			}
		}
		
		dictionary.put(false, null);
		return dictionary;
	}

	@Override
	public Map<Boolean, Place> findById(Integer id) {
		Map<Boolean, Place> dictionary = new HashMap<Boolean, Place>();
		PlaceEntity placeEntity = this.placeDataAccess.findById(id);
		Map<Boolean, List<PicturePlace>> result = this.picturePlaceBusiness.findAllFromPlaceId(placeEntity.getId());
		
		if(placeEntity != null && result.keySet().iterator().next()) {
			Place place = new  Place(placeEntity, 
					result.values().iterator().next()
			);
			
			if(place != null) {
				dictionary.put(true, place);
				return dictionary;
			}
		}

		dictionary.put(false, null);
		return dictionary;
	}

	@Override
	public Map<Boolean, List<Place>> findMostRecentPlace(int count) {
		Map<Boolean, List<Place>> dictionary = new HashMap<Boolean, List<Place>>();
		List<PlaceEntity> listPlaceEntity = this.placeDataAccess.findMostRecentPlaceEntity(count);
		
		if(listPlaceEntity != null) {
			List<Place> listPlace = this.convertListPlaceEntityToListPlace(listPlaceEntity);
			
			if(listPlace != null) {
				dictionary.put(true, listPlace);
				return dictionary;
			}
		}
		
		dictionary.put(false, null);
		return dictionary;
	}

	@Override
	public Map<Boolean, Place> update(Place place) {
		Map<Boolean, Place> dictionary = new HashMap<Boolean, Place>();
		List<PicturePlace> listPicturePlace = new ArrayList<PicturePlace>();
		
		if(place != null) {
			PlaceEntity placeEntity = this.placeDataAccess.update(new PlaceEntity(place));
			
			if(place.getListPicturePlace() != null) {
				for(int i = 0; i < place.getListPicturePlace().size(); i++) {
					Map<Boolean, PicturePlace> result = this.picturePlaceBusiness.update(place.getListPicturePlace().get(i));
					
					if(result.keySet().iterator().next()) {
						listPicturePlace.add(result.values().iterator().next());
					} else {
						dictionary.put(false, null);
						return dictionary;
					}
				}
			}
			
			if(placeEntity != null) {
				place = new Place(placeEntity, listPicturePlace);
				
				if(place != null) {
					dictionary.put(true, place);
					return dictionary;
				}
			}
		}
		
		dictionary.put(false, null);
		return dictionary;
	}
	
	private List<Place> convertListPlaceEntityToListPlace(List<PlaceEntity> listPlaceEntity) {
		List<Place> listPlace = new ArrayList<Place>();
		
		if(listPlaceEntity != null) {
			for(int i = 0; i < listPlaceEntity.size(); i++) {
				Map<Boolean, List<PicturePlace>> result = this.picturePlaceBusiness.findAllFromPlaceId(listPlaceEntity.get(i).getId());
				
				if(result.keySet().iterator().next()) {
					listPlace.add(new Place(listPlaceEntity.get(i), result.values().iterator().next()));
				}				
			}
		}
		
		return listPlace;
	}
}
