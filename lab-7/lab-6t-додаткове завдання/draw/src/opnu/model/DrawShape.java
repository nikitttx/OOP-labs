package opnu.model;

import java.awt.*;

/**
 * Абстрактний клас "Фігура для малювання".
 * Містить початкову та кінцеву точку малювання, а також абстрактний метод
 * для отримання об'єкта Shape конкретної фігури.
 */
public abstract class DrawShape {

    // Фабричний метод для створення об'єктів підкласів
    public static DrawShape newInstance(int shapeType) {
        DrawShape shape = null;
        if (shapeType == DrawShape.SHAPE_RECTANGLE) {
            shape = new Rectangle();
        } else if (shapeType == DrawShape.SHAPE_ROUNDED_RECT) {
            shape = new RoundedRectangle();
        } else if (shapeType == DrawShape.SHAPE_ELLIPSE) {
            shape = new Ellipse();
        }
        return shape;
    }

    // Константи для типів фігур
    public static final int SHAPE_RECTANGLE = 0;
    public static final int SHAPE_ROUNDED_RECT = 1;
    public static final int SHAPE_ELLIPSE = 2;

    // Початкова та кінцева точки
    private Point startPoint;
    private Point endPoint;

    // Конструктор без параметрів
    public DrawShape() {
        this(new Point(0, 0), new Point(0, 0));
    }

    // Конструктор з початковими координатами
    public DrawShape(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    // Метод повертає фігуру, засновану на поточних точках startPoint та endPoint
    public Shape getShape() {
        return this.getShape(startPoint, endPoint);
    }

    // Абстрактний метод, який ПОВИНЕН бути перевизначений у кожному конкретному підкласі
    public abstract Shape getShape(Point startPoint, Point endPoint);

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }
}