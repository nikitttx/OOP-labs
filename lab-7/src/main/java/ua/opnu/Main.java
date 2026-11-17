package ua.opnu;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        // Завдання 1
        Predicate<Integer> isPrime = number -> {
            if (number < 2) {
                return false;
            }
            for (int divisor = 2; divisor <= number / divisor; divisor++) {
                if (number % divisor == 0) {
                    return false;
                }
            }
            return true;
        };
        int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("Завдання 1: " + Arrays.toString(filter(numbers, isPrime)));

        // Завдання 2
        Student[] students = {
                new Student("Шевченко Анна", "КН-241", new int[]{90, 75, 60}),
                new Student("Коваленко Іван", "КН-241", new int[]{85, 59, 70}),
                new Student("Бондар Олена", "КН-242", new int[]{60, 80, 95})
        };
        Predicate<Student> withoutDebts = student -> {
            for (int mark : student.getMarks()) {
                if (mark < 60) {
                    return false;
                }
            }
            return true;
        };
        Student[] filteredStudents = filter(students, withoutDebts);
        System.out.println("Завдання 2:");
        forEach(filteredStudents, student -> System.out.println(student.getName()));

        // Завдання 3
        Predicate<Integer> isEven = number -> number % 2 == 0;
        Predicate<Integer> greaterThanThree = number -> number > 3;
        System.out.println("Завдання 3: "
                + Arrays.toString(filter(numbers, isEven, greaterThanThree)));

        // Завдання 4
        Consumer printName = student -> System.out.println(student.getName());
        System.out.println("Завдання 4:");
        forEach(students, printName);

        // Завдання 5
        Predicate<Integer> divisibleByThree = number -> number % 3 == 0;
        java.util.function.Consumer<Integer> printNumber = number -> System.out.println(number);
        System.out.println("Завдання 5:");
        forEach(numbers, divisibleByThree, printNumber);

        // Завдання 6
        Function<Integer, Integer> powerOfTwo = n -> (int) Math.pow(2, n);
        System.out.println("Завдання 6: " + Arrays.toString(processArray(numbers, powerOfTwo)));

        // Завдання 7
        String[] digitNames = {"нуль", "один", "два", "три", "чотири",
                "п'ять", "шість", "сім", "вісім", "дев'ять"};
        Function<Integer, String> digitToString = digit -> digitNames[digit];
        System.out.println("Завдання 7: " + Arrays.toString(stringify(numbers, digitToString)));
    }

    public static int[] filter(int[] input, Predicate<Integer> predicate) {
        int[] result = new int[input.length];
        int count = 0;
        for (int number : input) {
            if (predicate.test(number)) {
                result[count++] = number;
            }
        }
        return Arrays.copyOf(result, count);
    }

    public static Student[] filter(Student[] input, Predicate<Student> predicate) {
        Student[] result = new Student[input.length];
        int count = 0;
        for (Student student : input) {
            if (predicate.test(student)) {
                result[count++] = student;
            }
        }
        return Arrays.copyOf(result, count);
    }

    public static int[] filter(int[] input, Predicate<Integer> first, Predicate<Integer> second) {
        return filter(input, first.and(second));
    }

    public static void forEach(Student[] input, Consumer action) {
        for (Student student : input) {
            action.accept(student);
        }
    }

    public static void forEach(int[] input, Predicate<Integer> predicate,
                               java.util.function.Consumer<Integer> action) {
        for (int number : input) {
            if (predicate.test(number)) {
                action.accept(number);
            }
        }
    }

    public static int[] processArray(int[] input, Function<Integer, Integer> function) {
        int[] result = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = function.apply(input[i]);
        }
        return result;
    }

    public static String[] stringify(int[] input, Function<Integer, String> function) {
        String[] result = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = function.apply(input[i]);
        }
        return result;
    }
}
