package api.webapicontroller;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import api.businessobject.PictureUser;
import api.ibusiness.IPictureUserBusiness;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class PictureUserController {
	private final IPictureUserBusiness pictureUserBusiness;
	private final Gson gson = new Gson();

	public PictureUserController(IPictureUserBusiness pictureUserBusiness) {
		this.pictureUserBusiness = pictureUserBusiness;
	}
	
	public Router getSubRouter(final Vertx vertx) {
		  // Création du sous-routeur
		  final Router subRouter = Router.router(vertx);
		  // Body handler
		  subRouter.route("/*").handler(BodyHandler.create());
		  // Définition des routes
		  
		  subRouter.post("/").handler(this::createPictureUser);
		  subRouter.delete("/:id").handler(this::deletePictureUser);
		  subRouter.get("/").handler(this::findAllPictureUser);
		  subRouter.get("/:id").handler(this::findPictureUserById);
		  subRouter.put("/").handler(this::updatePictureUser);
		  return subRouter;
	}
	
	private void createPictureUser(RoutingContext routingContext) {
		final String body = routingContext.getBodyAsString();
		final PictureUser pictureUser = this.gson.fromJson(body, PictureUser.class);
	  	
	    try {
	    	final Map<Boolean, PictureUser> result = this.pictureUserBusiness.create(pictureUser);
		  	
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
	
	private void deletePictureUser(RoutingContext routingContext) {
		final int id = Integer.parseInt(routingContext.request().getParam("id"));
		final PictureUser pictureUser = new PictureUser(id, 0, null, null, null);
		
		try {
			final boolean result = this.pictureUserBusiness.delete(pictureUser);
		  	
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
	
	private void findAllPictureUser(RoutingContext routingContext) {
		try {
			final Map<Boolean, List<PictureUser>> result = this.pictureUserBusiness.findAll();
		  	
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
	
	private void findPictureUserById(RoutingContext routingContext) {
		final int id = Integer.parseInt(routingContext.request().getParam("id"));
		
		try {
			final Map<Boolean, PictureUser> result = this.pictureUserBusiness.findById(id);
		  	
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
	
	private void updatePictureUser(RoutingContext routingContext) {
		final String body = routingContext.getBodyAsString();
		final PictureUser pictureUser = this.gson.fromJson(body, PictureUser.class);
	  	
	    try {
		    Map<Boolean, PictureUser> result = this.pictureUserBusiness.update(pictureUser);
		  	
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
