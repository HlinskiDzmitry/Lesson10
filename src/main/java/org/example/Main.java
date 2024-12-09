package org.example;

public class Main {
    public static void main(String[] args) {
        Dog dogBobik = new Dog("Бобик");
        Cat catMurzik = new Cat("Мурзик");
        Cat catBarsik = new Cat("Барсик");

        dogBobik.run(150);
        catMurzik.run(200);
        dogBobik.swim(5);
        catBarsik.swim(1);

        System.out.println("Всего животных: " + Animal.getAnimalCount());
        System.out.println("Всего собак: " + Dog.getDogCount());
        System.out.println("Всего котов: " + Cat.getCatCount());

        Bowl bowl = new Bowl(25);
        Cat[] cats = {catMurzik, catBarsik};

        System.out.println("\nКоты пытаются поесть:");
        for (Cat cat : cats) {
            cat.eat(bowl);
        }

        bowl.info();

        System.out.println("\nСытость котов:");
        for (Cat cat : cats) {
            System.out.println(cat.name + ": " + (cat.isFull() ? "Сыт" : "Голоден"));
        }

        System.out.println("\nДобавляем еды в миску.");
        bowl.addFood(20);
        bowl.info();

        System.out.println("\nКоты снова пытаются поесть:");
        for (Cat cat : cats) {
            cat.eat(bowl);
        }

        bowl.info();

        System.out.println("\nСытость котов:");
        for (Cat cat : cats) {
            System.out.println(cat.name + ": " + (cat.isFull() ? "Сыт" : "Голоден"));
        }
    }
}