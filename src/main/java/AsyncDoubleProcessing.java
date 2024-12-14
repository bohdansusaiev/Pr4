package com.bodya;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class AsyncDoubleProcessing {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        CompletableFuture<List<Double>> generateNumbers = CompletableFuture.supplyAsync(() -> {
            Random random = new Random();
            return random.doubles(20, 1.0, 100.0).boxed().collect(Collectors.toList());
        }).thenApplyAsync(numbers -> {
            System.out.println("Generated sequence: " + numbers);
            return numbers;
        });

        CompletableFuture<Double> sum = generateNumbers.thenApplyAsync(numbers -> {
            double totalSum = numbers.stream().mapToDouble(Double::doubleValue).sum();
            System.out.println("Sum of numbers: " + totalSum);
            return totalSum;
        });

        CompletableFuture<Double> sumOfSquares = generateNumbers.thenApplyAsync(numbers -> {
            double totalSumOfSquares = numbers.stream().mapToDouble(num -> num * num).sum();
            System.out.println("Sum of squares: " + totalSumOfSquares);
            return totalSumOfSquares;
        });

        CompletableFuture.allOf(sum, sumOfSquares).thenRunAsync(() -> {
            long end = System.currentTimeMillis();
            System.out.println("Total execution time: " + (end - start) + " ms");
        }).join();
    }
}
