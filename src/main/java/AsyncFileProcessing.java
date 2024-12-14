package com.bodya;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AsyncFileProcessing {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        List<Path> files = List.of(
                Path.of("file1.txt"),
                Path.of("file2.txt"),
                Path.of("file3.txt")
        );

        CompletableFuture<List<String>> loadTexts = CompletableFuture.supplyAsync(() -> {
            try {
                return files.stream()
                        .map(file -> {
                            try {
                                return Files.readString(file);
                            } catch (IOException e) {
                                throw new RuntimeException("File read error: " + file, e);
                            }
                        }).toList();
            } catch (Exception e) {
                throw new RuntimeException("File upload error", e);
            }
        }).thenApplyAsync(texts -> {
            System.out.println("Texts loaded: " + texts);
            return texts;
        });

        CompletableFuture<List<String>> processTexts = loadTexts.thenApplyAsync(texts -> {
            return texts.stream()
                    .map(text -> text.replaceAll("[a-zA-Z]", ""))
                    .toList();
        }).thenApplyAsync(processedTexts -> {
            System.out.println("Processed texts: " + processedTexts);
            return processedTexts;
        });

        CompletableFuture<Void> finalTask = processTexts.thenAcceptAsync(processedTexts -> {
            String result = String.join("", processedTexts);
            System.out.println("Resulting text: " + result);
        }).thenRunAsync(() -> {
            long end = System.currentTimeMillis();
            System.out.println("Execution time: " + (end - start) + " ms");
        });

        finalTask.join();
    }
}
