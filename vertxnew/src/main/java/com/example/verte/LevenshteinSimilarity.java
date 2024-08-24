package com.example.verte;

public class LevenshteinSimilarity implements SimilarityFunction {
    @Override
    public double apply(String a, String b) {
        int distance = org.apache.commons.text.similarity.LevenshteinDistance.getDefaultInstance().apply(a, b);
        int maxLength = Math.max(a.length(), b.length());
        return 1.0 - (double) distance / maxLength;
    }
}
