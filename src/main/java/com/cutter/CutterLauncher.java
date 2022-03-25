package com.cutter;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class CutterLauncher {

    @Option(name = "-w", metaVar = "FlagWord", usage = "Flag [-w], count words, only one can be used")
    private boolean flagWord;

    @Option(name = "-c", metaVar = "FlagChar", usage = "Flag [-c], count characters, only one can be used")
    private boolean flagChar;

    @Option(name = "-o", metaVar = "OutputName", usage = "Output file name")
    private String outputFileName;

    @Argument(metaVar = "InputName",required = true, usage = "Input file name")
    private String inputFileName;

    @Argument(metaVar = "Range", required = true, index = 1, usage = "Range of cut piece")
    private String range;

    public static void main(String[] args) {
        new CutterLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Cutter.jar [-c|-w] [-o ofile] [file] range");
            parser.printUsage(System.err);
            return;
        }

        if (!flagChar && !flagWord) {
            System.out.println("No flag found");
        } else if (flagChar && flagWord) {
            System.out.println("Two flags found");
        }
        else {
            char flag;

            if (flagChar) {
                flag = 'c';
            } else {
                flag = 'w';
            }

            Cutter cutter = new Cutter(range);

            try {
                if (!Objects.equals(inputFileName, "console") && outputFileName != null) {
                    if (Objects.equals(flag, 'c')){
                        cutter.cutChar(inputFileName, outputFileName);
                    }
                    if (Objects.equals(flag, 'w')){
                        cutter.cutWord(inputFileName, outputFileName);
                    }
                    System.out.println("Successful cut");
                } else {
                    if (Objects.equals(inputFileName, "console") && outputFileName != null){
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("Enter path to your input file: ");
                        String text = scanner.nextLine();
                        if (Objects.equals(flag, 'c')){
                            cutter.cutChar(text, outputFileName);
                        }
                        if (Objects.equals(flag, 'w')){
                            cutter.cutWord(text, outputFileName);
                        }
                        System.out.println("Successful cut");
                    } else if (!Objects.equals(inputFileName, "console")){
                        if (Objects.equals(flag, 'c')){
                            cutter.cutChar(inputFileName);
                        }
                        if (Objects.equals(flag, 'w')){
                            cutter.cutWord(inputFileName);
                        }
                    } else {
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("Enter path to your input file: ");
                        String text = scanner.nextLine();
                        if (Objects.equals(flag, 'c')){
                            cutter.cutChar(text);
                        }
                        if (Objects.equals(flag, 'w')){
                            cutter.cutWord(text);
                        }
                    }
                }

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

            }
        }
    }
