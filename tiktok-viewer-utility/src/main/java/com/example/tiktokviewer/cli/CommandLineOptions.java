package com.example.tiktokviewer.cli;

public class CommandLineOptions {
    private final boolean help;
    private final String url;
    private final int count;

    public CommandLineOptions(boolean help, String url, int count) {
        this.help = help;
        this.url = url;
        this.count = count;
    }

    public boolean isHelp() {
        return help;
    }

    public String getUrl() {
        return url;
    }

    public int getCount() {
        return count;
    }
}