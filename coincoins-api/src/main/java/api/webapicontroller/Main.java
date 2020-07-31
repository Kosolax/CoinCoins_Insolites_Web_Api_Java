package api.webapicontroller;

import api.utils.Constants;
import io.vertx.core.Vertx;

public class Main {
	
    public static void main(String[] args) {
    	Constants.ABSTRACT_DATA_ACCESS_FACTORY.initData(true);
    	System.out.println("App...");
        final Vertx vertx = Vertx.factory.vertx();
        vertx.deployVerticle(new Controller());
    }
}
