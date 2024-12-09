package org.example;

public class Cat extends Animal {
    private static int catCount = 0;
    private boolean isFull;

    public Cat(String name) {
        super(name);
        this.maxRunDistance = 200;
        this.maxSwimDistance = 0; // Коты не умеют плавать
        this.isFull = false;
        catCount++;
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать.");
    }

    public boolean isFull() {
        return isFull;
    }

    public void eat(Bowl bowl) {
        if (bowl.getFood() >= 10) {
            bowl.decreaseFood(10);
            isFull = true;
            System.out.println(name + " поел и теперь сыт.");
        } else {
            System.out.println(name + " не смог поесть, недостаточно еды.");
        }
    }

    public static int getCatCount() {
        return catCount;
    }
}