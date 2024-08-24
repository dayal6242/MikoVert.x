package com.example.verte;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        System.out.println("start");
        // Add your routes here
        router.post("/api/search").handler(new SearchHandler());

        vertx.createHttpServer()
            .requestHandler(router)
            .listen(8083)
            .onSuccess(server -> {
                System.out.println("HTTP server started on port " + server.actualPort());
                startPromise.complete();
            })
            .onFailure(startPromise::fail);
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        System.out.println("Hello");
        MainVerticle mv=new MainVerticle();
        vertx.deployVerticle(mv);
    }
}
