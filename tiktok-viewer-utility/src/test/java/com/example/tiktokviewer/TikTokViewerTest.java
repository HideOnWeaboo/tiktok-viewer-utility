package com.example.tiktokviewer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TikTokViewerTest {
    @Test
    void testInvalidUrl() {
        TikTokViewer viewer = new TikTokViewer();
        assertThrows(IllegalArgumentException.class, () -> {
            viewer.viewVideo("https://example.com/video");
        });
    }

    @Test
    void testValidUrlFormat() {
        TikTokViewer viewer = new TikTokViewer();
        assertDoesNotThrow(() -> {
            viewer.viewVideo("https://www.tiktok.com/@user/video/123456789");
        });
    }
}