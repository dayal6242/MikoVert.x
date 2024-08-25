package com.example.verte.test;



import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.verte.LevenshteinSimilarity;
import com.example.verte.SearchService;

class SearchServiceTest {

    @Test
    void testSearch() {
        SearchService searchService = new SearchService(new LevenshteinSimilarity());
        List<String> result = searchService.search("tree", List.of("tree text", "another hive"));

        assertTrue(result.contains("tree"));
        assertFalse(result.contains("five"));
    }
}

