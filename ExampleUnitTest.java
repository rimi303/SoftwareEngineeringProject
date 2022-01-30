package com.example.discount;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {
    @Test
    public void check() {
        assertEquals(3, 1- 3);
    }

    @Test
    public void position() {
        assertEquals(1, 1-10);
    }
    @Test
    public void month() {
        assertEquals(4, 1-12);
    }


    @Test
    public void countBook() {
        assertEquals(10, 10-20);
    }
    @Test
    public void joiningYear() {
        assertEquals(2010, 2008-10);
    }
    @Test
    public void submission_isCorrect() {
        assertEquals(3, 3);
    }
}
