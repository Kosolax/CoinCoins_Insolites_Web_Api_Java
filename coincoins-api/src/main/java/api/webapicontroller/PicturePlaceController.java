package api.webapicontroller;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import api.businessobject.PicturePlace;
import api.ibusiness.IPicturePlaceBusiness;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class PicturePlaceController {
	private final IPicturePlaceBusiness picturePlaceBusiness;
	private final Gson gson = new Gson();

	public PicturePlaceController(IPicturePlaceBusiness picturePlaceBusiness) {
		this.picturePlaceBusiness = picturePlaceBusiness;
	}
	
	public Router getSubRouter(final Vertx vertx) {
		  // Création du sous-routeur
		  final Router subRouter = Router.router(vertx);
		  // Body handler
		  subRouter.route("/*").handler(BodyHandler.create());
		  // Définition des routes
		  
		  subRouter.post("/").handler(this::createPicturePlace);
		  subRouter.delete("/:id").handler(this::deletePicturePlace);
		  subRouter.get("/").handler(this::findAllPicturePlace);
		  subRouter.get("/:id").handler(this::findPicturePlaceById);
		  subRouter.put("/").handler(this::updatePicturePlace);
		  return subRouter;
	}
	
	private void createPicturePlace(RoutingContext routingContext) {
		final String body = routingContext.getBodyAsString();		
		final PicturePlace picturePlace = this.gson.fromJson(body, PicturePlace.class);
	  	
	    try {
	    	final Map<Boolean, PicturePlace> result = this.picturePlaceBusiness.create(picturePlace);
		  	
		  	if(result.keySet().iterator().next()) {
		  		routingContext.response()
			        .setStatusCode(200)
			        .putHeader("content-type", "application/json")
			        .end(Json.encodePrettily(result.values().iterator().next()));
		  	} else {
		  		routingContext.response()
			        .setStatusCode(400)
			        .putHeader("content-type", "application/json")
			        .end(Json.encodePrettily(result.values().iterator().next()));
		  	}
	  	} catch(Exception e) {
	  		routingContext.response()
		        .setStatusCode(500)
		        .putHeader("content-type", "application/json")
		        .end(Json.encodePrettily(null));
	  	}
	}
	
	private void deletePicturePlace(RoutingContext routingContext) {
		final int id = Integer.parseInt(routingContext.request().getParam("id"));
		final PicturePlace picturePlace = new PicturePlace(id, 0, null, null, null);
		
		try {
			final boolean result = this.picturePlaceBusiness.delete(picturePlace);
		  	
		  	if(result) {
		  		routingContext.response()
			        .setStatusCode(200)
			        .putHeader("content-type", "application/json")
			        .end(Json.encodePrettily(result));
		  	} else {
		  		routingContext.response()
			        .setStatusCode(400)
			        .putHeader("content-type", "application/json")
			        .end(Json.encodePrettily(result));
		  	}
	  	} catch(Exception e) {
	  		routingContext.response()
		        .setStatusCode(500)
		        .putHeader("content-type", "application/json")
		        .end(Json.encodePrettily(null));
	  	}
	}
	
	private void findAllPicturePlace(RoutingContext routingContext) {
		try {
			final Map<Boolean, List<PicturePlace>> result = this.picturePlaceBusiness.findAll();
		  	
			if(result.keySet().iterator().next()) {
		  		routingContext.response()
			        .setStatusCode(200)
			        .putHeader("content-type", "application/json")
			        .end(Json.encodePrettily(result.values().iterator().next()));
		  	} else {
		  		routingContext.response()
			        .setStatusCode(400)
			        .putHeader("content-type", "application/json")
			        .end(Json.encodePrettily(result.values().iterator().next()));
		  	}
	  	} catch(Exception e) {
	  		routingContext.response()
		        .setStatusCode(500)
		        .putHeader("content-type", "application/json")
		        .end(Json.encodePrettily(null));
	  	}
	}
	
	private void findPicturePlaceById(RoutingContext routingContext) {
		final int id = Integer.parseInt(routingContext.request().getParam("id"));
		
		try {
			final Map<Boolean, PicturePlace> result = this.picturePlaceBusiness.findById(id);
		  	
			if(result.keySet().iterator().next()) {
		  		routingContext.response()
			        .setStatusCode(200)
			        .putHeader("content-type", "application/json")
			        .end(Json.encodePrettily(result.values().iterator().next()));
		  	} else {
		  		routingContext.response()
			        .setStatusCode(400)
			        .putHeader("content-type", "application/json")
			        .end(Json.encodePrettily(result.values().iterator().next()));
		  	}
	  	} catch(Exception e) {
	  		routingContext.response()
		        .setStatusCode(500)
		        .putHeader("content-type", "application/json")
		        .end(Json.encodePrettily(null));
	  	}
	}
	
	private void updatePicturePlace(RoutingContext routingContext) {
		final String body = routingContext.getBodyAsString();
		final PicturePlace picturePlace = this.gson.fromJson(body, PicturePlace.class);
	  	
	    try {
		    Map<Boolean, PicturePlace> result = this.picturePlaceBusiness.update(picturePlace);
		  	
			if(result.keySet().iterator().next()) {
		  		routingContext.response()
			        .setStatusCode(200)
			        .putHeader("content-type", "application/json")
			        .end(Json.encodePrettily(result.values().iterator().next()));
		  	} else {
		  		routingContext.response()
			        .setStatusCode(400)
			        .putHeader("content-type", "application/json")
			        .end(Json.encodePrettily(result.values().iterator().next()));
		  	}
	  	} catch(Exception e) {
	  		routingContext.response()
		        .setStatusCode(500)
		        .putHeader("content-type", "application/json")
		        .end(Json.encodePrettily(null));
	  	}
	}
}
