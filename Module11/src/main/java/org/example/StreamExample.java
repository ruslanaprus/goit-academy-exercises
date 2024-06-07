package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.regex.MatchResult;
import java.math.BigInteger;

public class StreamExample {
    public static List<String> searchFor(List<String> list, String target) {
        list.replaceAll((item) -> item.toLowerCase());
        Stream<String> listStream = list.stream()
                .filter((item) -> item.contains(target.toLowerCase()));
        return listStream.collect(Collectors.toList());
    }

    public static List<Integer> multiply(List<Integer> list, Integer target) {
        Stream<Integer> listStream = list
                .stream()
                .map(number -> number * target);
        return listStream.collect(Collectors.toList());
    }

    public static boolean isPalindrome(int number) {
        String numberString = String.valueOf(number);
        int length = numberString.length();
        int halfLength = length / 2;

        return IntStream.range(0, halfLength)
                .noneMatch(i -> numberString.charAt(i) != numberString.charAt(length - i - 1));
    }

    public static List<String> sortDecreasingOrderToUppercaseOne(List<String> list) {
        return list.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public static List<String> sortDecreasingOrderToUppercaseTwo(List<String> list) {
        return list.stream()
                .map(s -> {
                    return s.toUpperCase();
                })
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public static String collectAndSortLargeNumbers(List<String> input) {
        Pattern pattern = Pattern.compile("\\d+");

        return input.stream()
                .flatMap(str -> pattern.matcher(str).results().map(MatchResult::group).map(BigInteger::new))
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

}
