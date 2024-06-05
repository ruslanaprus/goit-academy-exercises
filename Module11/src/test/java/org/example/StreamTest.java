package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class StreamTest {

    @Test
    void testSearchForItemIsPresent(){
        List<String> catsTest = Arrays.asList("Wiskers, Pawster", "Buscuit", "Pounce", "Catsby", "Purrlock Holmes", "Catpernicus", "Picatso");

        List<String> result = StreamExample.searchFor(catsTest,"cat");

        System.out.println("result = " + result);
        assertEquals(3, result.size());
        assertTrue(result.contains("catsby"));
        assertTrue(result.contains("catpernicus"));
        assertTrue(result.contains("picatso"));
    }

    @Test
    void testMyltiply(){
        List<Integer> testList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> result = StreamExample.multiply(testList, 5);
        List<Integer> expected = List.of(5, 10, 15, 20, 25, 30, 35, 40, 45, 50);

        assertEquals(expected, result);
    }

    @Test
    void testIsPalindromeTrue(){
        int testValue = 123454321;
        boolean expected = true;

        boolean result = StreamExample.isPalindrome(testValue);
        assertEquals(expected, result);
    }

    @Test
    public void testIsPalindromeFalse(){
        int testValue = 1234567890;
        boolean expected = false;

        boolean result = StreamExample.isPalindrome(testValue);
        assertEquals(expected, result);
    }

    @Test
    void testSort(){
        List<String> catsTest = Arrays.asList("Wiskers", "Pawster", "Buscuit", "Pounce", "Catsby", "Purrlock Holmes", "Catpernicus", "Picatso");
        List<String> expected = Arrays.asList("WISKERS", "PURRLOCK HOLMES", "POUNCE", "PICATSO", "PAWSTER", "CATSBY", "CATPERNICUS", "BUSCUIT");

        List<String> sortedCats = StreamExample.sortDecreasingOrderToUppercaseTwo(catsTest);
        assertEquals(expected, sortedCats);
    }

}