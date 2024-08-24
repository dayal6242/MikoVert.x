package com.example.verte;


import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SearchHandler implements Handler<RoutingContext> {

    private final SearchService searchService;

    public SearchHandler() {
        this.searchService = new SearchService(new LevenshteinSimilarity());
    }

    @Override
    public void handle(RoutingContext context) {
        JsonObject body = context.getBodyAsJson();

        // Debugging: Print received values
        System.out.println("Received request body: " + body);

        if (body == null || body.isEmpty()) {
            context.response()
                .setStatusCode(400)
                .end(new JsonObject().put("error", "Request body is missing").encode());
            return;
        }

        String searchTerm = body.getString("search_term");
        List<String> textList = body.getJsonArray("text").getList();

        // Print extracted values
        System.out.println("Search Term: " + searchTerm);
        System.out.println("Text Array: " + textList);

        if (searchTerm == null || searchTerm.isEmpty() || textList == null || textList.isEmpty()) {
            context.response()
                .setStatusCode(400)
                .end(new JsonObject().put("error", "Invalid input: 'search_term' or 'text' is missing or empty").encode());
            return;
        }

        List<String> matches = searchService.search(searchTerm, textList);

        context.response()
            .setStatusCode(200)
            .end(new JsonObject().put("matches", matches).encode());
    }
}
