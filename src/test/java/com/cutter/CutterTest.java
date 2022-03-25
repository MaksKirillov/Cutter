package com.cutter;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import java.io.*;

public class CutterTest {

    @Test
    public void range(){
        Cutter cut1 = new Cutter("\\-2");
        int[] expected1 = new int[] {0, 1, 2};
        for (int i = 0; i < expected1.length; i++) {
            Assert.assertEquals(cut1.range[i], expected1[i]);
        }

        Cutter cut2 = new Cutter("-4");
        int[] expected2 = new int[] {0, 1, 4};
        for (int i = 0; i < expected1.length; i++) {
            Assert.assertEquals(cut2.range[i], expected2[i]);
        }

        Cutter cut3 = new Cutter("3-");
        int[] expected3 = new int[] {1, 3, 0};
        for (int i = 0; i < expected1.length; i++) {
            Assert.assertEquals(cut3.range[i], expected3[i]);
        }

        Cutter cut4 = new Cutter("3-7");
        int[] expected4 = new int[] {2, 3, 7};
        for (int i = 0; i < expected1.length; i++) {
            Assert.assertEquals(cut4.range[i], expected4[i]);
        }

        Cutter cut5 = new Cutter("10-3");
        int[] expected5 = new int[] {2, 3, 10};
        for (int i = 0; i < expected1.length; i++) {
            Assert.assertEquals(cut5.range[i], expected5[i]);
        }

    }

    @Test
    public void cutChar1() throws IOException {
        FileInputStream input = new FileInputStream("src\\test\\java\\com\\cutter\\input.txt");
        FileOutputStream output = new FileOutputStream("src\\test\\java\\com\\cutter\\output.txt");
        Cutter cut = new Cutter("-4");
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
        FileReader reader = new FileReader("src\\test\\java\\com\\cutter\\output.txt");
        int c;
        StringBuilder actual = new StringBuilder();
        while ((c = reader.read()) != -1) {
            actual.append((char) c);
        }
        Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void cutChar2() throws IOException {
        FileInputStream input = new FileInputStream("src\\test\\java\\com\\cutter\\input.txt");
        FileOutputStream output = new FileOutputStream("src\\test\\java\\com\\cutter\\output.txt");
        Cutter cut = new Cutter("4-8");
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
        FileReader reader = new FileReader("src\\test\\java\\com\\cutter\\output.txt");
        int c;
        StringBuilder actual = new StringBuilder();
        while ((c = reader.read()) != -1) {
            actual.append((char) c);
        }
        Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void cutWord1() throws IOException {
        FileInputStream input = new FileInputStream("src\\test\\java\\com\\cutter\\input.txt");
        FileOutputStream output = new FileOutputStream("src\\test\\java\\com\\cutter\\output.txt");
        Cutter cut = new Cutter("-2");
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
        FileReader reader = new FileReader("src\\test\\java\\com\\cutter\\output.txt");
        int c;
        StringBuilder actual = new StringBuilder();
        while ((c = reader.read()) != -1) {
            actual.append((char) c);
        }
        Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void cutWord2() throws IOException {
        FileInputStream input = new FileInputStream("src\\test\\java\\com\\cutter\\input.txt");
        FileOutputStream output = new FileOutputStream("src\\test\\java\\com\\cutter\\output.txt");
        Cutter cut = new Cutter("2-3");
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
        FileReader reader = new FileReader("src\\test\\java\\com\\cutter\\output.txt");
        int c;
        StringBuilder actual = new StringBuilder();
        while ((c = reader.read()) != -1) {
            actual.append((char) c);
        }
        Assert.assertEquals(expected, actual.toString());
    }

}
