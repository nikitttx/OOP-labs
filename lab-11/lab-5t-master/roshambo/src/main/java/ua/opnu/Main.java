package ua.opnu;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Шевченко", "Тарас", 40);

        // Висхідне перетворення (Upcasting): об'єкти Student та Lecturer збережені у змінних типу Person
        Person student1 = new Student("Іваненко", "Іван", 19, "КН-21", "KB12345678");
        Person student2 = new Student("Петренко", "Олена", 20, "КН-22", "KB87654321");

        Person lecturer1 = new Lecturer("Сидоренко", "Петро", 45, "Інформаційних систем", 25000.0);
        Person lecturer2 = new Lecturer("Коваленко", "Марія", 38, "Програмної інженерії", 23000.0);

        // Масив типу Person, який містить об'єкти Person, Student та Lecturer
        Person[] people = { person, student1, student2, lecturer1, lecturer2 };

        // Вивід інформації у циклі за допомогою методу toString()
        for (Person p : people) {
            System.out.println(p.toString());
        }
        SwingUtilities.invokeLater(() -> new MainFrame("RoShamBo"));
    }
}
