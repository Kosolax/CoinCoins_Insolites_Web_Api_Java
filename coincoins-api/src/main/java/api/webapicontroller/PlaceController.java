package api.webapicontroller;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import api.businessobject.Place;
import api.ibusiness.IPlaceBusiness;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class PlaceController {
	private final IPlaceBusiness placeBusiness;
	private final Gson gson = new Gson();

	public PlaceController(IPlaceBusiness placeBusiness) {
		this.placeBusiness = placeBusiness;
	}
	
	public Router getSubRouter(final Vertx vertx) {
		  // Création du sous-routeur
		  final Router subRouter = Router.router(vertx);
		  // Body handler
		  subRouter.route("/*").handler(BodyHandler.create());
		  // Définition des routes
		  
		  subRouter.post("/").handler(this::createPlace);
		  subRouter.delete("/:id").handler(this::deletePlace);
		  subRouter.get("/").handler(this::findAllPlace);
		  subRouter.get("/:id").handler(this::findPlaceById);
		  subRouter.get("/top/:id").handler(this::findMostRecentPlace);
		  subRouter.put("/").handler(this::updatePlace);
		  return subRouter;
	}
	
	private void createPlace(RoutingContext routingContext) {
		final String body = routingContext.getBodyAsString();
		final Place place = gson.fromJson(body, Place.class);
	  	
	    try {
	    	final Map<Boolean, Place> result = this.placeBusiness.create(place);
		  	
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
	
	private void deletePlace(RoutingContext routingContext) {
		final int id = Integer.parseInt(routingContext.request().getParam("id"));
		final Place place = new Place(id, null, null, null, null, null);
		
		try {
			final boolean result = this.placeBusiness.delete(place);
		  	
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
	
	private void findAllPlace(RoutingContext routingContext) {
		try {
			final Map<Boolean, List<Place>> result = this.placeBusiness.findAll();
		  	
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
	
	private void findPlaceById(RoutingContext routingContext) {
		final int id = Integer.parseInt(routingContext.request().getParam("id"));
		
		try {
			final Map<Boolean, Place> result = this.placeBusiness.findById(id);
		  	
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
	
	private void findMostRecentPlace(RoutingContext routingContext) {
		final int count = Integer.parseInt(routingContext.request().getParam("id"));
		
		try {
			final Map<Boolean, List<Place>> result = this.placeBusiness.findMostRecentPlace(count);
		  	
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
	
	private void updatePlace(RoutingContext routingContext) {
		final String body = routingContext.getBodyAsString();
		final Place place = this.gson.fromJson(body, Place.class);
	  	
	    try {
		    Map<Boolean, Place> result = this.placeBusiness.update(place);
		  	
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
