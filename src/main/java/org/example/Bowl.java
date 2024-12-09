package org.example;

public class Bowl {
    private int food;

    public Bowl(int food) {
        this.food = food > 0 ? food : 0;
    }

    public int getFood() {
        return food;
    }

    public void decreaseFood(int amount) {
        if (amount <= food) {
            food -= amount;
        }
    }

    public void addFood(int amount) {
        if (amount > 0) {
            food += amount;
        }
    }

    public void info() {
        System.out.println("В миске осталось " + food + " еды.");
    }
}