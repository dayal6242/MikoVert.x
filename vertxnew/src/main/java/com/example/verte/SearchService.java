package com.example.verte;


import java.util.List;
import java.util.stream.Collectors;

public class SearchService {

    private final SimilarityFunction distance;

    public SearchService(SimilarityFunction distance) {
        this.distance = distance;
    }

    public List<String> search(String searchTerm, List<String> textList) {
        double threshold = 0.8;

        return textList.stream()
                .flatMap(text -> List.of(text.split("\\s+")).stream()) // Split text into words
                .map(word -> word.toLowerCase()) // Convert to lower case
                .distinct() // Remove duplicates
                .filter(word -> distance.apply(searchTerm.toLowerCase(), word) >= threshold) // Filter based on similarity
                .collect(Collectors.toList()); // Collect and return the matches
    }
}

