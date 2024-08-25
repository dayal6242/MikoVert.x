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
                .flatMap(text -> List.of(text.split("\\s+")).stream()) 
                .map(word -> word.toLowerCase()) 
                .distinct() 
                .filter(word -> distance.apply(searchTerm.toLowerCase(), word) >= threshold) 
                .collect(Collectors.toList()); 
    }
}

