package com.example.helloworld;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() {
        Router router = Router.router(vertx);

        // Handle HTTP GET request to "/hello"
        router.route("/hello").handler(ctx -> {
            ctx.response()
               .putHeader("content-type", "text/plain")
               .end("Hello, World!");
        });

        vertx.createHttpServer()
             .requestHandler(router)
             .listen(8084, http -> {
                 if (http.succeeded()) {
                     System.out.println("HTTP server started on port " + http.result().actualPort());
                 } else {
                     System.out.println("Failed to start HTTP server: " + http.cause().getMessage());
                 }
             });
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        System.out.println("Application Strated");
        vertx.deployVerticle(new MainVerticle());
    }
}

