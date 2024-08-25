package com.example.helloworld;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

	@Override
	public void start() {
		Router router = Router.router(vertx);

		router.route("/hello").handler(ctx -> {
			ctx.response().putHeader("content-type", "text/plain").end("Hello, World!");
		});

		vertx.createHttpServer().requestHandler(router).listen(8084)
				.onSuccess(server -> System.out.println("Server started on port " + server.actualPort()))
				.onFailure(err -> System.err.println("Failed to start server: " + err.getMessage()));
	}

	public static void main(String[] args) {
		System.out.println("Appkication started");
		Vertx.vertx().deployVerticle(new MainVerticle());
	}
}
