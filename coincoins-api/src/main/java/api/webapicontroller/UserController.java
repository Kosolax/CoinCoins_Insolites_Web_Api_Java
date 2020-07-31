package api.webapicontroller;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import api.businessobject.User;
import api.ibusiness.IUserBusiness;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class UserController {
	private final IUserBusiness userBusiness;
	private final Gson gson = new Gson();
	
	public UserController(IUserBusiness userBusiness) {
		this.userBusiness = userBusiness;
	}

	//


	public Router getSubRouter(final Vertx vertx) {
		  // Création du sous-routeur
		  final Router subRouter = Router.router(vertx);
		  // Body handler
		  subRouter.route("/*").handler(BodyHandler.create());
		  // Définition des routes
		  
		  subRouter.post("/authenticate").handler(this::authenticateUser);
		  subRouter.post("/").handler(this::createUser);
		  subRouter.delete("/:id").handler(this::deleteUser);
		  subRouter.get("/").handler(this::findAllUser);
		  subRouter.get("/:id").handler(this::findUserById);
		  subRouter.put("/").handler(this::updateUser);
		  return subRouter;
	}
	
	private void authenticateUser(RoutingContext routingContext) {
		final String body = routingContext.getBodyAsString();
		final User user = this.gson.fromJson(body, User.class);
	  	
	  	try {
		  	final Map<Boolean, User> result = this.userBusiness.authenticate(user);
		  	
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
	
	private void createUser(RoutingContext routingContext) {
		final String body = routingContext.getBodyAsString();
		final User user = this.gson.fromJson(body, User.class);
	  	
	    try {
	    	final Map<Boolean, User> result = this.userBusiness.create(user);
		  	
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
	
	private void deleteUser(RoutingContext routingContext) {
		final int id = Integer.parseInt(routingContext.request().getParam("id"));
		final User user = new User(id, null , null, null, null);
		
		try {
			final boolean result = this.userBusiness.delete(user);
		  	
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
	
	private void findAllUser(RoutingContext routingContext) {
		try {
			final Map<Boolean, List<User>> result = this.userBusiness.findAll();
		  	
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
	
	private void findUserById(RoutingContext routingContext) {
		final int id = Integer.parseInt(routingContext.request().getParam("id"));
		
		try {
			final Map<Boolean, User> result = this.userBusiness.findById(id);
		  	
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
	
	private void updateUser(RoutingContext routingContext) {
		final String body = routingContext.getBodyAsString();
		final User user = this.gson.fromJson(body, User.class);
	  	
	    try {
		    final Map<Boolean, User> result = this.userBusiness.update(user);
		  	
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
