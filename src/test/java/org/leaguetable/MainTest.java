package org.leaguetable;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void stringSplitTest() {
        String s = "FC Awesome 1, Groaches 2";
        String[] parts = s.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        System.out.println(Arrays.toString(parts));
    }

}