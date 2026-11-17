package ua.opnu;

import java.util.Arrays;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        // Завдання 1
        MyOptional<String> empty = new MyOptional<>();
        MyOptional<String> username = new MyOptional<>("admin");
        System.out.println(empty);
        System.out.println(empty.isPresent());
        System.out.println(empty.isEmpty());
        System.out.println(empty.orElse("guest"));
        System.out.println(username);
        System.out.println(username.isPresent());
        System.out.println(username.isEmpty());
        System.out.println(username.get());
        System.out.println(username.orElse("guest"));
        try {
            empty.get();
        } catch (IllegalStateException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            new MyOptional<String>(null);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        // Завдання 2
        BookData[] books = {
                new BookData("Java", "Author A", 2, 8),
                new BookData("Python", "Author B", 2, 10),
                new BookData("Algorithms", "Author C", 3, 15)
        };
        Arrays.sort(books);
        System.out.println(Arrays.toString(books));

        // Завдання 3
        Printer myPrinter = new Printer();
        Integer[] intArray = {1, 2, 3};
        String[] stringArray = {"Hello", "World"};
        myPrinter.printArray(intArray);
        myPrinter.printArray(stringArray);

        // Завдання 4
        System.out.println(Arrays.toString(filter(intArray, n -> n % 2 != 0)));
        System.out.println(Arrays.toString(filter(stringArray, s -> s.startsWith("H"))));

        // Завдання 5
        System.out.println(contains(intArray, 2));
        System.out.println(contains(stringArray, "Java"));

        // Завдання 6
        GenericTwoTuple<String, Integer> pair = new GenericTwoTuple<>("Іван", 95);
        GenericThreeTuple<String, Integer, Boolean> triple =
                new GenericThreeTuple<>("Іван", 95, true);
        System.out.println(pair);
        System.out.println(triple);
    }

    public static <T> T[] filter(T[] input, Predicate<T> predicate) {
        T[] result = Arrays.copyOf(input, input.length);
        int count = 0;
        for (T element : input) {
            if (predicate.test(element)) {
                result[count++] = element;
            }
        }
        return Arrays.copyOf(result, count);
    }

    public static <T extends Comparable<T>, V extends T> boolean contains(T[] array, V element) {
        for (T item : array) {
            if (item.equals(element)) {
                return true;
            }
        }
        return false;
    }
}
