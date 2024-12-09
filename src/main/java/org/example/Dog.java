package org.example;

public class Dog extends Animal {
    private static int dogCount = 0;

    public Dog(String name) {
        super(name);
        this.maxRunDistance = 500;
        this.maxSwimDistance = 10;
        dogCount++;
    }

    public static int getDogCount() {
        return dogCount;
    }
}
