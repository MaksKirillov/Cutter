package com.cutter;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static java.lang.Math.min;

@SuppressWarnings("WeakerAccess")
public class Cutter {

    public final int[] range;
    public final char flag;

    public Cutter(String range, char flag){
        Type type = new Type(range);
        this.range = type.range;
        this.flag = flag;
    }

    private String[] reader(InputStreamReader reader) throws IOException {
        int c;
        StringBuilder text = new StringBuilder();
        while ((c = reader.read()) != -1) {
            text.append((char) c);
        }
        return text.toString().split("\n");
    }

    public void cutChar(InputStream in, OutputStream out) throws IOException{
        try (InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
            try (OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8)) {
                StringBuilder newText = new StringBuilder();
                String[] lines = reader(reader);
                for (String line : lines){
                    StringBuilder newLine = new StringBuilder();
                    switch (range[0]) {
                        case 0 -> newLine.append(line, 0, min(range[2], line.length()));
                        case 1 -> newLine.append(line.substring(range[1] - 1));
                        default -> newLine.append(line, range[1] - 1, min(range[2], line.length()));
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
                StringBuilder newText = new StringBuilder();
                String[] lines = reader(reader);
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

    public void cut(String inputName, String outputName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                if (flag == 'c'){
                    cutChar(inputStream, outputStream);
                }
                if (flag == 'w'){
                    cutWord(inputStream, outputStream);
                }
            }
        }
    }

    public void cut(String inputName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            if (flag == 'c'){
                cutChar(inputStream, System.out);
            }
            if (flag == 'w'){
                cutWord(inputStream, System.out);
            }
        }
    }

    public void cutConsoleInput(String input, String outputName) throws IOException {
        try (InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8))) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                if (flag == 'c'){
                    cutChar(inputStream, outputStream);
                }
                if (flag == 'w'){
                    cutWord(inputStream, outputStream);
                }
            }
        }
    }

    public void cutConsoleInput(String input) throws IOException {
        try (InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8))) {
            if (flag == 'c'){
                cutChar(inputStream, System.out);
            }
            if (flag == 'w'){
                cutWord(inputStream, System.out);
            }
        }
    }
}
