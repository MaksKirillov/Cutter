package com.cutter;

import java.io.*;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("WeakerAccess")
public class Cutter {

    public final int[] range;

    public Cutter(String range){
        this.range = range(range);
    }

    private int[] range(String range){
        int type;
        String newRange = range.replace("\\","").replace("/","");
        if (newRange.charAt(0) == '-') {type = 0;}
        else if (newRange.charAt(newRange.length() - 1) == '-') {type = 1;}
        else {type = 2;}

        int N = 0;
        int K = 0;

        switch (type) {
            case 0 -> K = Integer.parseInt(newRange.substring(1));
            case 1 -> N = Integer.parseInt(newRange.substring(0, newRange.length() - 1));
            default -> {
                String[] nums = newRange.split("-");
                N = Integer.parseInt(nums[0]);
                K = Integer.parseInt(nums[1]);
                if (N > K) {
                    int a = N;
                    N = K;
                    K = a;
                }
            }
        }

        if (N == 0) N = 1;

        return new int[] {type, N, K};
    }

        public void cutChar(InputStream in, OutputStream out) throws IOException{
        try (InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
            try (OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8)) {
                int c;
                StringBuilder text = new StringBuilder();
                while ((c = reader.read()) != -1) {
                    text.append((char) c);
                }
                StringBuilder newText = new StringBuilder();
                String[] lines = text.toString().split("\n");
                for (String line : lines){
                    StringBuilder newLine = new StringBuilder();
                    char[] chars = line.toCharArray();
                    switch (range[0]) {
                        case 0 -> {for (int i = 0; i < chars.length; i++) {
                            newLine.append(chars[i]);
                            if (i == range[2] - 1) break;
                            }
                        }
                        case 1 -> {if (range[1] > chars.length) break;
                            for (int i = range[1] - 1; i < chars.length; i++) {
                                newLine.append(chars[i]);
                            }
                        }
                        default -> {if (range[1] >= chars.length) break;
                            for (int i = range[1] - 1; i < chars.length; i++) {
                                newLine.append(chars[i]);
                                if (i == range[2] - 1) break;
                            }
                        }
                    }
                    newText.append(newLine).append("\n");
                }
                writer.write(newText.toString().trim());
            }
        }
    }

    public void cutWord(InputStream in, OutputStream out) throws IOException{
        try (InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
            try (OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8)) {
                int c;
                StringBuilder text = new StringBuilder();
                while ((c = reader.read()) != -1) {
                    text.append((char) c);
                }
                StringBuilder newText = new StringBuilder();
                String[] lines = text.toString().split("\n");
                for (String line : lines){
                    StringBuilder newLine = new StringBuilder();
                    String[] words = (" " + line).split(" ");
                    switch (range[0]) {
                        case 0 -> {for (int i = 0; i < words.length; i++) {
                            newLine.append(words[i]);
                            newLine.append(" ");
                            if (i == range[2]) break;
                        }
                        }
                        case 1 -> {if (range[1] > words.length) break;
                            for (int i = range[1] - 1; i < words.length; i++) {
                                newLine.append(words[i]);
                                newLine.append(" ");
                            }
                        }
                        default -> {if (range[1] >= words.length) break;
                            for (int i = range[1] - 1; i < words.length; i++) {
                                newLine.append(words[i]);
                                newLine.append(" ");
                                if (i == range[2]) break;
                            }
                        }
                    }
                    newText.append(newLine.toString().trim());
                    newText.append("\n");
                }
                writer.write(newText.toString().trim());
            }
        }
    }

    public void cutChar(String inputName, String outputName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                cutChar(inputStream, outputStream);
            }
        }
    }

    public void cutWord(String inputName, String outputName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                cutWord(inputStream, outputStream);
            }
        }
    }

    public void cutChar(String inputName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
                cutChar(inputStream, System.out);
        }
    }

    public void cutWord(String inputName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
                cutWord(inputStream, System.out);
        }
    }
}
