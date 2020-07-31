package api.webapicontroller;

import api.business.PicturePlaceBusiness;
import api.business.PictureUserBusiness;
import api.business.PlaceBusiness;
import api.business.UserBusiness;
import api.ibusiness.IPicturePlaceBusiness;
import api.ibusiness.IPictureUserBusiness;
import api.ibusiness.IPlaceBusiness;
import api.ibusiness.IUserBusiness;
import api.idataaccess.IPicturePlaceDataAccess;
import api.idataaccess.IPictureUserDataAccess;
import api.idataaccess.IPlaceDataAccess;
import api.idataaccess.IUserDataAccess;
import api.utils.Constants;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;

public class Controller extends AbstractVerticle{
	
	@Override
	public void start() throws Exception {
		final Router router = Router.router(vertx);
		
		final IPictureUserDataAccess pictureUserDataAccess = Constants.ABSTRACT_DATA_ACCESS_FACTORY.getPictureUserDataAccess();
		final IPicturePlaceDataAccess picturePlaceDataAccess = Constants.ABSTRACT_DATA_ACCESS_FACTORY.getPicturePlaceDataAccess();
		final IUserDataAccess userDataAccess = Constants.ABSTRACT_DATA_ACCESS_FACTORY.getUserDataAccess();
		final IPlaceDataAccess placeDataAccess = Constants.ABSTRACT_DATA_ACCESS_FACTORY.getPlaceDataAccess();
		
		final IPictureUserBusiness pictureUserBusiness = new PictureUserBusiness(pictureUserDataAccess);
		final IPicturePlaceBusiness picturePlaceBusiness = new PicturePlaceBusiness(picturePlaceDataAccess);
		final IUserBusiness userBusiness = new UserBusiness(userDataAccess, pictureUserBusiness);
		final IPlaceBusiness placeBusiness = new PlaceBusiness(placeDataAccess, picturePlaceBusiness);
		
		final UserController userController = new UserController(userBusiness);
		final Router userRouter = userController.getSubRouter(vertx);
		router.mountSubRouter("/api/v1/users", userRouter);		
		
		final PlaceController placeController = new PlaceController(placeBusiness);
		final Router placeRouter = placeController.getSubRouter(vertx);
		router.mountSubRouter("/api/v1/places", placeRouter);
		
		final PictureUserController pictureUserController = new PictureUserController(pictureUserBusiness);
		final Router pictureUserRouter = pictureUserController.getSubRouter(vertx);
		router.mountSubRouter("/api/v1/pictureUsers", pictureUserRouter);
		
		final PicturePlaceController picturePlaceController = new PicturePlaceController(picturePlaceBusiness);
		final Router picturePlaceRouter = picturePlaceController.getSubRouter(vertx);
		router.mountSubRouter("/api/v1/picturePlaces", picturePlaceRouter);
		
		vertx.createHttpServer()
			.requestHandler(router)
			.listen(8080);
	}

	@Override
	public void stop() throws Exception {
		System.out.println("stop");
	}
}
