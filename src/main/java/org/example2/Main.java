package org.example2;

public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle(5); // Радиус = 5
        Shape rectangle = new Rectangle(4, 7); // Длина = 4, Ширина = 7
        Shape triangle = new Triangle(3, 4, 5); // Стороны = 3, 4, 5

        System.out.println("Характеристики круга:");
        circle.printDetails("Красный", "Черный");

        System.out.println("\nХарактеристики прямоугольника:");
        rectangle.printDetails("Синий", "Зеленый");

        System.out.println("\nХарактеристики треугольника:");
        triangle.printDetails("Желтый", "Белый");
    }
}