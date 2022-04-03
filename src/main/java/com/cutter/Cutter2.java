package com.cutter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static java.lang.Math.min;

public class Cutter2 {

    public final int[] range;
    public final char flag;
    public boolean inputType = true; // false - file, true - console
    public boolean outputType = true; // false - file, true - console
    public String inputName;
    public String outputName;

    public Cutter2(String range, char flag) {
        Type type = new Type(range);
        this.range = type.range;
        this.flag = flag;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
        this.inputType = false;
    }

    public void setOutputName(String outputName) {
        this.outputName = outputName;
        this.outputType = false;
    }

    public void setConsoleInput() {
        this.inputType = true;
    }

    public void setConsoleOutput() {
        this.outputType = true;
    }

    public void launch() throws IOException {
        if (!inputType) {
            try (FileInputStream reader = new FileInputStream(inputName)) {
                int c;
                StringBuilder text = new StringBuilder();
                while ((c = reader.read()) != -1) {
                    text.append((char) c);
                }
                if (flag == 'c') {
                    writer(cutChar(text.toString().split("\n")));
                }
                if (flag == 'w') {
                    writer(cutWord(text.toString().split("\n")));
                }
            }
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your text (Type \"EOF\" to end it): ");
            String line = scanner.nextLine();
            StringBuilder text = new StringBuilder();
            while (scanner.hasNext() && !line.contains("EOF")) {
                text.append(line).append("\n");
                line = scanner.nextLine();
                if (line.contains("EOF")) break;
            }
            if (flag == 'c') {
                writer(cutChar(text.toString().split("\n")));
            }
            if (flag == 'w') {
                writer(cutWord(text.toString().split("\n")));
            }
        }
    }

    private String cutChar(String[] lines) throws IOException {
        StringBuilder newText = new StringBuilder();
        for (String line : lines) {
            StringBuilder newLine = new StringBuilder();
            switch (range[0]) {
                case 0 -> newLine.append(line, 0, min(range[2], line.length()));
                case 1 -> newLine.append(line.substring(range[1] - 1));
                default -> newLine.append(line, range[1] - 1, min(range[2], line.length()));
            }
            newText.append(newLine).append("\n");
        }
        return newText.toString().trim();
    }


    private String cutWord(String[] lines) throws IOException {
        StringBuilder newText = new StringBuilder();
        for (String line : lines) {
            StringBuilder newLine = new StringBuilder();
            String[] words = (" " + line).split(" ");
            switch (range[0]) {
                case 0 -> {
                    for (int i = 0; i < words.length; i++) {
                        newLine.append(words[i]);
                        newLine.append(" ");
                        if (i == range[2]) break;
                    }
                }
                case 1 -> {
                    if (range[1] > words.length) break;
                    for (int i = range[1] - 1; i < words.length; i++) {
                        newLine.append(words[i]);
                        newLine.append(" ");
                    }
                }
                default -> {
                    if (range[1] >= words.length) break;
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
        return newText.toString().trim();
    }

    private void writer(String output) throws IOException {
        if (!outputType) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                writer.write(output);
            }
        } else {
            System.out.println(output);
        }
    }

}
