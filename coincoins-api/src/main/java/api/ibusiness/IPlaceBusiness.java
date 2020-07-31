package api.ibusiness;

import java.util.List;
import java.util.Map;

import api.businessobject.Place;

public interface IPlaceBusiness extends IBaseBusiness<Place> {
	Map<Boolean, List<Place>> findMostRecentPlace(int count);
}
