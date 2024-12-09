package org.example2;

public interface Shape {
    double calculatePerimeter();

    double calculateArea();

    default void printDetails(String fillColor, String borderColor) {
        System.out.println("Площадь: " + calculateArea());
        System.out.println("Периметр: " + calculatePerimeter());
        System.out.println("Цвет заливки: " + fillColor);
        System.out.println("Цвет границы: " + borderColor);
    }
}