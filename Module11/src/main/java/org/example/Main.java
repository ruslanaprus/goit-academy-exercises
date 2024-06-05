package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Stream<Integer> evenNumbers = Arrays.asList(1, 2, 3, 4 ,5 ,6 ,7, 8 ,9, 0).stream().filter((num) -> num%2==0);
        System.out.println("evenNumbers.toList() = " + evenNumbers.toList());

        Stream<Integer> numbers =
                Arrays.asList(2, 2, 3)
                        .stream()
                        .distinct();
        System.out.println("numbers = " + numbers.toList());

        Stream<Integer> sortedNumbers =
                Arrays.asList(123, 323, 5662, 42, 5436)
                        .stream()
                        .sorted();
        System.out.println("sortedNumbers = " + sortedNumbers.toList());

        Stream<String> names =
                Arrays.asList("Alice", "Bob", "Charlie", "Eve")
                        .stream()
                        .filter(name -> name.length() <= 3)
                        .map(name -> "Mr/Ms " + name)
                        .sorted()
                        .limit(2);

        List<String> filteredNames = names.collect(Collectors.toList());
        System.out.println("filteredNames = " + filteredNames);
    }

}