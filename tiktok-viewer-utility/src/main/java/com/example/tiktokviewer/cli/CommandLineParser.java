package com.example.tiktokviewer.cli;

import java.util.Arrays;

public class CommandLineParser {
    public static CommandLineOptions parse(String[] args) {
        if (args.length == 0) {
            return new CommandLineOptions(false, null, 1);
        }

        boolean help = Arrays.asList(args).contains("--help");
        String url = null;
        int count = 1;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--url") && i + 1 < args.length) {
                url = args[i + 1];
            } else if (args[i].equals("--count") && i + 1 < args.length) {
                try {
                    count = Integer.parseInt(args[i + 1]);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid count value. Using default: 1");
                }
            }
        }

        return new CommandLineOptions(help, url, count);
    }
}