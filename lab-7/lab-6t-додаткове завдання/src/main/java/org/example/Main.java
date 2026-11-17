package org.example;

abstract class Animal {

    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    abstract String makeSound();
}

class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return "Гав-гав!";
    }
}

class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return "Мяу!";
    }
}

interface Movable {
    void setX(double x);

    void setY(double y);

    double getX();

    double getY();

    void moveTo(double x, double y);
}

class Point implements Movable {
    private double x;
    private double y;

    public Point() {
        this.x = 0.0;
        this.y = 0.0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

interface GPS {
    double[] getCoordinates();
}

interface Cellular {
    void makeCall();

    void receiveCall();
}

class Smartphone implements GPS, Cellular {
    private double latitude;
    private double longitude;

    public Smartphone(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public double[] getCoordinates() {
        return new double[]{latitude, longitude};
    }

    @Override
    public void makeCall() {
        System.out.println("Здійснюється вихідний виклики...");
    }

    @Override
    public void receiveCall() {
        System.out.println("Вхідний виклики прийнято.");
    }
}

class Car implements Comparable {

    private int price;
    private int year; // рік виготовлення
    private int horsePower;

    public Car(int price, int year, int horsePower) {
        this.price = price;
        this.year = year;
        this.horsePower = horsePower;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    @Override
    public int compareTo(Object o) {
        Car other = (Car) o;

        // 1. Порівняння за ціною
        if (this.price != other.price) {
            if (this.price < other.price) {
                return 1;
            } else {
                return -1;
            }
        }

        // 2. Порівняння за роком випуску
        if (this.year != other.year) {
            if (this.year > other.year) {
                return 1;
            } else {
                return -1;
            }
        }

        // 3. Порівняння за кінськими силами
        if (this.horsePower != other.horsePower) {
            if (this.horsePower > other.horsePower) {
                return 1;
            } else {
                return -1;
            }
        }

        return 0;
    }
}

public class Main {
    public static void main(String[] args) {

        Animal dog = new Dog("Dog");
        Animal cat = new Cat("Cat");

        System.out.println(dog.getName() + " каже: " + dog.makeSound());
        System.out.println(cat.getName() + " каже: " + cat.makeSound());

        System.out.println("=== Завдання 2: Point ===");
        Point point = new Point(10.5, 20.0);
        System.out.println("Початкові координати: X = " + point.getX() + ", Y = " + point.getY());

        point.moveTo(50.0, 100.5);
        System.out.println("Нові координати після moveTo: X = " + point.getX() + ", Y = " + point.getY());
        System.out.println();


        System.out.println("=== Завдання 3: Smartphone ===");
        Smartphone phone = new Smartphone(50.4501, 30.5234); // Координати Києва

        phone.makeCall();
        phone.receiveCall();

        double[] coords = phone.getCoordinates();
        System.out.println("Координати GPS: Широта = " + coords[0] + ", Довгота = " + coords[1]);
        System.out.println();


        System.out.println("=== Завдання 4: Car (Comparable) ===");

        Car car1 = new Car(20000, 2020, 150);
        Car car2 = new Car(25000, 2022, 200);

        System.out.println("car1 vs car2 (за ціною): " + car1.compareTo(car2));

        Car car3 = new Car(20000, 2018, 150);
        System.out.println("car1 vs car3 (за роком): " + car1.compareTo(car3));

        Car car4 = new Car(20000, 2020, 180);
        // car1 має менше к.с. (150 < 180), тому car1 "менша"
        System.out.println("car1 vs car4 (за к.с.): " + car1.compareTo(car4));

        Car car5 = new Car(20000, 2020, 150);
        System.out.println("car1 vs car5 (однакові): " + car1.compareTo(car5));
    }
}

