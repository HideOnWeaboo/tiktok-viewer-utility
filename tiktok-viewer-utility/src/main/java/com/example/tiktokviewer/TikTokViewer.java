package com.example.tiktokviewer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public class TikTokViewer {
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) TikTokViewer/1.0";
    private final HttpClient httpClient;
    private final Random random;

    public TikTokViewer() {
        this.httpClient = HttpClient.newHttpClient();
        this.random = new Random();
    }

    public void viewVideo(String videoUrl) throws IOException, InterruptedException {
        if (!videoUrl.contains("tiktok.com")) {
            throw new IllegalArgumentException("Invalid TikTok URL");
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(videoUrl))
                .header("User-Agent", USER_AGENT)
                .header("Accept-Language", "en-US,en;q=0.9")
                .header("Referer", "https://www.tiktok.com/")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            System.out.println("Successfully viewed video: " + videoUrl);
            simulateHumanBehavior();
        } else {
            System.err.println("Failed to view video. Status code: " + response.statusCode());
        }
    }

    private void simulateHumanBehavior() throws InterruptedException {
        int delay = 2000 + random.nextInt(3000);
        Thread.sleep(delay);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java TikTokViewer <video-url>");
            System.exit(1);
        }

        TikTokViewer viewer = new TikTokViewer();
        try {
            viewer.viewVideo(args[0]);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}