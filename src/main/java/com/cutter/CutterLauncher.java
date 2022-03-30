package com.cutter;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.util.Scanner;

public class CutterLauncher {

    @Option(name = "-w", forbids = {"-c"}, metaVar = "FlagWord", usage = "Flag [-w], count words, only one can be used")
    private boolean flagWord;

    @Option(name = "-c", forbids = {"-w"}, metaVar = "FlagChar", usage = "Flag [-c], count characters, only one can be used")
    private boolean flagChar;

    @Option(name = "-r", metaVar = "Range", required = true, usage = "Range of cut piece")
    private String range;

    @Option(name = "-o", metaVar = "OutputName", usage = "Output file name")
    private String outputFileName;

    @Argument(metaVar = "InputName", usage = "Input file name")
    private String inputFileName;

    public static void main(String[] args) {
        new CutterLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Cutter.jar [-c|-w] [-r range] [-o output file] [file]");
            parser.printUsage(System.err);
            return;
        }

        if (!flagChar && !flagWord) {
            System.out.println("No flag found");
        } else {
            char flag;

            if (flagChar) {
                flag = 'c';
            } else {
                flag = 'w';
            }

            Cutter cutter = new Cutter(range, flag);

            try {
                if (inputFileName != null && outputFileName != null) {
                    cutter.cut(inputFileName, outputFileName);
                    System.out.println("Successful cut");
                } else {
                    if (inputFileName == null){
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Enter your text (Type \"EOF\" to end it): ");
                        String line = scanner.nextLine();
                        StringBuilder text = new StringBuilder();

                        while (scanner.hasNext() && !line.contains("EOF")) {
                            text.append(line).append("\n");
                            line = scanner.nextLine();
                            if (line.contains("EOF")) break;
                        }
                        if (outputFileName != null) {
                            cutter.cutConsoleInput(text.toString(), outputFileName);
                            System.out.println("Successful cut");
                        } else {
                            cutter.cutConsoleInput(text.toString());
                        }
                    } else {
                        cutter.cut(inputFileName);
                    }
                }

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

            }
        }
    }
