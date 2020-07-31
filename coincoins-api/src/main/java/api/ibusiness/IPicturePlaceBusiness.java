package api.ibusiness;

import java.util.List;
import java.util.Map;

import api.businessobject.PicturePlace;

public interface IPicturePlaceBusiness extends IBaseBusiness<PicturePlace> {
	public Map<Boolean, List<PicturePlace>> createFromList(List<PicturePlace> listPicturePlace);
	public Map<Boolean, List<PicturePlace>> findAllFromPlaceId(int placeId);
	public Boolean deleteAllFromPlaceId(int placeId);
}
