package com.example.tiktokviewer.cli;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommandLineParserTest {
    @Test
    void testHelpFlag() {
        CommandLineOptions options = CommandLineParser.parse(new String[]{"--help"});
        assertTrue(options.isHelp());
        assertNull(options.getUrl());
    }

    @Test
    void testUrlAndCount() {
        CommandLineOptions options = CommandLineParser.parse(new String[]{"--url", "https://tiktok.com/video", "--count", "5"});
        assertEquals("https://tiktok.com/video", options.getUrl());
        assertEquals(5, options.getCount());
    }

    @Test
    void testDefaultCount() {
        CommandLineOptions options = CommandLineParser.parse(new String[]{"--url", "https://tiktok.com/video"});
        assertEquals(1, options.getCount());
    }
}