package com.cutter;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;

public class TypeTest {
    @Test
    public void range(){
        Type actual1 = new Type("0-0");
        int[] expected1 = new int[] {2, 1, 0};
        for (int i = 0; i < expected1.length; i++) {
            Assert.assertEquals(actual1.range[i], expected1[i]);
        }

        Type actual2 = new Type("-4");
        int[] expected2 = new int[] {0, 1, 4};
        for (int i = 0; i < expected2.length; i++) {
            Assert.assertEquals(actual2.range[i], expected2[i]);
        }

        Type actual3 = new Type("3-");
        int[] expected3 = new int[] {1, 3, 0};
        for (int i = 0; i < expected3.length; i++) {
            Assert.assertEquals(actual3.range[i], expected3[i]);
        }

        Type actual4 = new Type("3-7");
        int[] expected4 = new int[] {2, 3, 7};
        for (int i = 0; i < expected4.length; i++) {
            Assert.assertEquals(actual4.range[i], expected4[i]);
        }

        Type actual5 = new Type("10-3");
        int[] expected5 = new int[] {2, 3, 10};
        for (int i = 0; i < expected5.length; i++) {
            Assert.assertEquals(actual5.range[i], expected5[i]);
        }

    }
}
