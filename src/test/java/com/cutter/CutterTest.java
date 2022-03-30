package com.cutter;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import java.io.*;

public class CutterTest {

    @Test
    public void cutChar1() throws IOException {
        FileInputStream input = new FileInputStream("files\\input.txt");
        FileOutputStream output = new FileOutputStream("files\\output.txt");
        Cutter cut = new Cutter("-4", 'c');
        cut.cutChar(input, output);
        String expected = """
                1234
                1234
                1234
                1234
                1234
                1234
                123\s
                12 1
                1 12""";
        FileReader reader = new FileReader("files\\output.txt");
        int c;
        StringBuilder actual = new StringBuilder();
        while ((c = reader.read()) != -1) {
            actual.append((char) c);
        }
        Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void cutChar2() throws IOException {
        FileInputStream input = new FileInputStream("files\\input.txt");
        FileOutputStream output = new FileOutputStream("files\\output.txt");
        Cutter cut = new Cutter("4-8", 'c');
        cut.cutChar(input, output);
        String expected = """
                45678
                45678
                4567\s
                456 1
                45 12
                4 123
                 1234
                12345
                23456""";
        FileReader reader = new FileReader("files\\output.txt");
        int c;
        StringBuilder actual = new StringBuilder();
        while ((c = reader.read()) != -1) {
            actual.append((char) c);
        }
        Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void cutWord1() throws IOException {
        FileInputStream input = new FileInputStream("files\\input.txt");
        FileOutputStream output = new FileOutputStream("files\\output.txt");
        Cutter cut = new Cutter("-2", 'w');
        cut.cutWord(input, output);
        String expected = """
                123456789 1
                12345678 12
                1234567 123
                123456 1234
                12345 12345
                1234 123456
                123 1234567
                12 12345678
                1 123456789""";
        FileReader reader = new FileReader("files\\output.txt");
        int c;
        StringBuilder actual = new StringBuilder();
        while ((c = reader.read()) != -1) {
            actual.append((char) c);
        }
        Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void cutWord2() throws IOException {
        FileInputStream input = new FileInputStream("files\\input.txt");
        FileOutputStream output = new FileOutputStream("files\\output.txt");
        Cutter cut = new Cutter("2-3", 'w');
        cut.cutWord(input, output);
        String expected = """
                123456789 1 12
                12345678 12 12
                1234567 123 12
                123456 1234 12
                12345 12345 12
                1234 123456 12
                123 1234567 12
                12 12345678 12
                1 123456789""";
        FileReader reader = new FileReader("files\\output.txt");
        int c;
        StringBuilder actual = new StringBuilder();
        while ((c = reader.read()) != -1) {
            actual.append((char) c);
        }
        Assert.assertEquals(expected, actual.toString());
    }

}
