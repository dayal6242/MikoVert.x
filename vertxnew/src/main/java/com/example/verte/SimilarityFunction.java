package com.example.verte;

@FunctionalInterface
public interface SimilarityFunction {
    double apply(String a, String b);
}